package common.shiro.filter;

import org.apache.shiro.web.filter.authc.LogoutFilter;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Created by paul on 2017/8/10.
 */
@Component(value = "systemLogoutFilter")
public class SystemLogoutFilter extends LogoutFilter{
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {

        return super.preHandle(request, response);
    }
}
