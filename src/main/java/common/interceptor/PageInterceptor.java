package common.interceptor;

import common.metaData.Page;
import common.util.Reflections;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**数据库分页插件，只拦截查询语句.
 * Created by paul on 2017/8/15.
 */

@Intercepts({@Signature(
        type =Executor.class,
        method = "query",
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class}
)})
public class PageInterceptor implements Interceptor {

    private static Log log = LogFactory.getLog(PageInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
        Page<Object> page = convertParameterPage(boundSql.getParameterObject());
        if (page != null && page.getPageSize() > -1) {
            String originSql = boundSql.getSql().trim();
            if (StringUtils.isEmpty(originSql)) {
                return null;
            }
            page.setCount(SQLHelper.getCount(originSql, null, mappedStatement, boundSql.getParameterObject(), boundSql, log));
            String pageSql = SQLHelper.generatePageMySql(originSql, page);
            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
            //解决MyBatis 分页foreach 参数失效 start
            if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
                MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
                Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
            }
            //解决MyBatis 分页foreach 参数失效 end
            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));

            invocation.getArgs()[0] = newMs;
        }

        return invocation.proceed();


    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }


    /**
     * @Description parameterObject转换成Page
     * @MethodName convertParameterPage
     * @param parameterObject
     * @return com.champion.framework.metadata.Page<java.lang.Object>
     * @author william [yeemin_shon@163.com]
     * @Date 2017/6/9 0009 11:51
     */
    @SuppressWarnings("unchecked")
    public Page<Object> convertParameterPage(Object parameterObject) {
        if (parameterObject == null) {
            return null;
        } else if (parameterObject instanceof Page) {
            return (Page<Object>) parameterObject;
        } else {
            return (Page<Object>) Reflections.getFieldValue(parameterObject, "page");
        }
    }

    private MappedStatement copyFromMappedStatement(MappedStatement ms,
                                                    SqlSource newSqlSource) {
        MappedStatement.Builder builder = new MappedStatement.Builder(ms.getConfiguration(),
                ms.getId(), newSqlSource, ms.getSqlCommandType());
        builder.resource(ms.getResource());
        builder.fetchSize(ms.getFetchSize());
        builder.statementType(ms.getStatementType());
        builder.keyGenerator(ms.getKeyGenerator());
        if (ms.getKeyProperties() != null) {
            for (String keyProperty : ms.getKeyProperties()) {
                builder.keyProperty(keyProperty);
            }
        }
        builder.timeout(ms.getTimeout());
        builder.parameterMap(ms.getParameterMap());
        builder.resultMaps(ms.getResultMaps());
        builder.cache(ms.getCache());
        builder.useCache(ms.isUseCache());
        return builder.build();
    }




    public static class BoundSqlSqlSource implements SqlSource {
        BoundSql boundSql;

        public BoundSqlSqlSource(BoundSql boundSql) {
            this.boundSql = boundSql;
        }

        public BoundSql getBoundSql(Object parameterObject) {
            return boundSql;
        }
    }

}
