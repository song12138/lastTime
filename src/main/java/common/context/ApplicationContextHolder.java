/**
 * @Description:
 * @info-yangpu.winchampion.info.yangpu.common.utils
 * @FileName:ApplicationContextHolder.java
 * @Author: William
 * @CreateTime: 2017-05-17 12:56:51
 */
package common.context;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Description: get ApplicationContext
 * @ClassName: ApplicationContextHolder
 * @Auther: William
 * @CreateTime: 2017-05-17 12:56:51
 */
public final class ApplicationContextHolder implements ApplicationContextAware, DisposableBean {

    private static Logger logger = LoggerFactory.getLogger(ApplicationContextHolder.class);

    private static ApplicationContextHolder instance = null;

    private ApplicationContextHolder() {
    }

    /**
     * @description
     * @methodName newInstance
     * @param
     * @return com.sevh.orly.framework.context.ApplicationContextHolder
     * @author william [yeemin_shon@163.com]
     * @time 2017/10/9 11:11
     */
    public static ApplicationContextHolder newInstance() {
        if (instance == null) {
            instance = new ApplicationContextHolder();
        }
        return instance;
    }

    private ApplicationContext applicationContext = null;

    /**
     *
     * @Description: remove ApplicationContext in ApplicationContextHolder
     * 				 set applicationContext null
     * @ReturnType void
     * @Author: William
     * @CreateTime: 2017-05-17 04:32:48
     */
    public void clearHolder() {
        if (logger.isDebugEnabled()) {
            logger.debug("remove ApplicationContext in ApplicationContextHolder :" + applicationContext);
        }
        applicationContext = null;
    }

    /**
     * @description get bean from applicationContext by bean name and cast it to required type
     * @methodName getBean
     * @param name name
     * @param <T> T
     * @return T
     * @author william [yeemin_shon@163.com]
     * @time 2017/10/9 11:11
     */
    @SuppressWarnings("unchecked")
    public <T> T getBean(String name) {
        assertContextInjected();
        return (T) applicationContext.getBean(name);
    }

    /**
     * @description get bean from applicationContext by class of type and cast it to required type
     * @methodName getBean
     * @param requiredType requiredType
     * @param <T> T
     * @return T
     * @author william [yeemin_shon@163.com]
     * @time 2017/10/9 11:12
     */
    public <T> T getBean(Class<T> requiredType) {
        assertContextInjected();
        return applicationContext.getBean(requiredType);
    }

    /**
     *
     * @Description: check weather zhe applicationContext is null;
     * @ReturnType void
     * @Author: William
     * @CreateTime: 2017-05-17 04:34:26
     */
    private void assertContextInjected() {
        if (instance == null) {
            logger.warn("applicationContext属性未注入， 请配置SpringContextHolder");
        }
    }

    @Override
    public void destroy() throws Exception {
        clearHolder();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ApplicationContextHolder.newInstance().applicationContext = applicationContext;
    }

    /**
     * @description
     * @methodName getApplicationContext
     * @param
     * @return org.springframework.context.ApplicationContext
     * @author william [yeemin_shon@163.com]
     * @time 2017/10/9 11:12
     */
    public ApplicationContext getApplicationContext() {
        assertContextInjected();
        return applicationContext;
    }
}
