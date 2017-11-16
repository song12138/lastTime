package common.shiro.filter;


import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.util.Collection;

/**
 * Created by paul on 2017/10/27.
 */
public class LoginControllerFilter extends FormAuthenticationFilter {
    private static Logger logger = LoggerFactory.getLogger(LoginControllerFilter.class);

    @Autowired
    private SessionDAO sessionDAO;



    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {

//        Subject subject = getSubject(request, response);
//        String username= (String) subject.getPrincipal();
        String username = request.getParameter("username");

        Collection<Session> sessions = sessionDAO.getActiveSessions();

        if(null != sessions && StringUtils.isNotBlank(username)){
            sessions.stream().forEach(session -> {
                String ss= String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY));
                if(username.equals(ss)){
                    sessionDAO.delete(session);
                    logger.error("踢出"+username);
                }
            });
        }
        return super.onAccessDenied(request, response);
    }
}
