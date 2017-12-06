package common.Listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.RequestHandledEvent;

/**
 * Created by paul on 2017/11/30.
 */
@Component
public class AllApplicationListener implements ApplicationListener<ApplicationEvent> {
    private static Logger logger = LoggerFactory.getLogger(AllApplicationListener.class);
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        if (event instanceof ContextStartedEvent) {
            logger.info("上下文开始");
        } else if (event instanceof ContextRefreshedEvent) {
            logger.info("上下文更新");
        }else if (event instanceof ContextStoppedEvent) {
            logger.info("上下文停止");
        }else if (event instanceof ContextClosedEvent) {
            logger.info("上下文关闭");
        }else if (event instanceof RequestHandledEvent) {
            logger.info("请求处理事件"+((RequestHandledEvent) event).getDescription());
        }

    }
}
