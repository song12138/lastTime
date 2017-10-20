package common.dataSource;

import common.annotation.JDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.util.Arrays;

/**  functtion拦截器，切换datasource
 * 注解实现aspect
 * Created by paul on 2017/10/18.
 */
@Component
@Aspect
public class DataSourceExchangeAspect {

    @Pointcut("execution(* pro.service.impl..*(..))")
   public void aspect(){};

    @Before("aspect()")
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

    @After("aspect()")
    public void after(){
        DataSourceContextHolder.setDsType(DataSourceType.DATASOURCE1);
    }

}
