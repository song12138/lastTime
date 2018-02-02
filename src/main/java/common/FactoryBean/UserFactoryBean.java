package common.FactoryBean;

import org.springframework.beans.factory.FactoryBean;
import pro.entity.User;

/**
 * 用来装配复杂的bean的，并注入容器
 *
 * 有了这个UserFactoryBean后，就可以在配置文件中使用下面这种自定义的配置方式配置User Bean了
 * Created by paul on 2018/1/4.
 */
public class UserFactoryBean implements FactoryBean<User> {

    private String userInfo;

    public void setUserInfo(String userInfo) {
        this.userInfo = userInfo;
    }

    @Override
    public User getObject() throws Exception {
        User user = new User();
        String[] infos = userInfo.split(",");
        user.setUsername(infos[0]);
        user.setRealname(infos[1]);
        return user;
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
