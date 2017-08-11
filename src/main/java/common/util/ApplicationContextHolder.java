package common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 *  保存Spring的 ApplicationContext, 可在任何代码任何地方任何时候取出ApplicaitonContext.
 * Created by paul on 2017/8/11.
 */
public class ApplicationContextHolder implements ApplicationContextAware,DisposableBean {

    private static final Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

    private static ApplicationContextHolder instance;
    private static  ApplicationContext applicationContext = null;

    private static ApplicationContextHolder newInstance(){
        if (null == instance) {
            instance = new ApplicationContextHolder();
        }
        return instance;
    }

    /**
     * @Description 清空contextHolder
     * @MethodName clearHolder
     * @param
     * @return void
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/11 14:17
     */
    public void clearHolder(){
        if(logger.isDebugEnabled()){
            logger.debug("清除ApplicationContextHolder中的ApplicationContext:" + applicationContext);
        }
        applicationContext = null;
    }


    /**
     * @Description 判断application注入
     * @MethodName assertApplictionContextInjected
     * @param
     * @return void
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/11 14:39
     */
    public void assertApplictionContextInjected() {
        if (ApplicationContextHolder.applicationContext == null) {
            if (logger.isDebugEnabled()) {
                logger.debug("applicaitonContext属性未注入");
            }
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }



    @Override
    public void destroy() throws Exception {
        clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.applicationContext = applicationContext;

    }

    @SuppressWarnings("unchecked")
    public <T> T getBean(String name){
        assertApplictionContextInjected();
        return (T) applicationContext.getBean(name);
    }

    public <T> T getBean(Class<T> requiredType) {
        assertApplictionContextInjected();
        return applicationContext.getBean(requiredType);
    }

}
