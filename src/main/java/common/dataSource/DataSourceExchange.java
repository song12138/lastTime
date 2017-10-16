package common.dataSource;


import common.annotation.JDataSource;
import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.annotation.Annotation;
import java.util.Arrays;

/** functtion拦截器，切换datasource
 * Created by paul on 2017/10/13.
 */
public class DataSourceExchange {

    private static Logger logger = LoggerFactory.getLogger(DataSourceExchange.class);

    public void before(JoinPoint joinPoint) {
        //获取目标对象类型
        Class<? extends Object> cls = joinPoint.getTarget().getClass();
        //切换到默认datasource
        DataSourceContextHolder.setDsType(DataSourceType.DATASOURCE1);

        //获得所有注解
        Annotation[] anos = cls.getAnnotations();

        Arrays.asList(anos).forEach(an ->{
            if (an instanceof JDataSource) {
                //根据注解切换datasource
               DataSourceContextHolder.setDsType(((JDataSource) an).value());
            }
        });
    }

    public void after(){
        DataSourceContextHolder.setDsType(DataSourceType.DATASOURCE1);
    }
}
