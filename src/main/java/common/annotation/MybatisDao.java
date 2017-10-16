package common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by paul on 2017/8/4.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MybatisDao {
     String value()default "";
}
