package common.interceptor;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.Properties;

/**数据库分页插件，只拦截查询语句.
 * Created by paul on 2017/8/15.
 */
//@Intercepts({@Signature()})


//        Executor (update, query, flushStatements, commit, rollback, getTransaction, close, isClosed) 拦截执行器的方法
//        ParameterHandler (getParameterObject, setParameters) 拦截参数的处理
//        ResultSetHandler (handleResultSets, handleOutputParameters) 拦截结果集的处理
//        StatementHandler (prepare, parameterize, batch, update, query) 拦截Sql语法构建的处理
@Intercepts({@Signature(
        type = Executor.class,
        method = "query",   //<E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException;
        args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class MybatisInterceptor implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        final MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];
        Object parameter = invocation.getArgs()[1];
        BoundSql boundSql = mappedStatement.getBoundSql(parameter);
//        Page<Object> page = convertParameterPage(boundSql.getParameterObject());
//        if (page != null && page.getPageSize() > -1) {
//            String originSql = boundSql.getSql().trim();
//            if (StringUtil.isEmpty(originSql)) {
//                return null;
//            }
//            page.setCount(SQLHelper.getCount(originSql, null, mappedStatement, boundSql.getParameterObject(), boundSql, log));
//            String pageSql = SQLHelper.generatePageMySql(originSql, page);
//            invocation.getArgs()[2] = new RowBounds(RowBounds.NO_ROW_OFFSET, RowBounds.NO_ROW_LIMIT);
//            BoundSql newBoundSql = new BoundSql(mappedStatement.getConfiguration(), pageSql, boundSql.getParameterMappings(), boundSql.getParameterObject());
//            //解决MyBatis 分页foreach 参数失效 start
//            if (Reflections.getFieldValue(boundSql, "metaParameters") != null) {
//                MetaObject mo = (MetaObject) Reflections.getFieldValue(boundSql, "metaParameters");
//                Reflections.setFieldValue(newBoundSql, "metaParameters", mo);
//            }
//            //解决MyBatis 分页foreach 参数失效 end
//            MappedStatement newMs = copyFromMappedStatement(mappedStatement, new BoundSqlSqlSource(newBoundSql));
//
//            invocation.getArgs()[0] = newMs;
//        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {

    }
}
