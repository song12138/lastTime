package common.shiro.filter;

import common.util.CookieUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.web.filter.AccessControlFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by paul on 2017/10/27.
 */
public class KickoutFilter extends AccessControlFilter{
    private static Logger logger = LoggerFactory.getLogger(KickoutFilter.class);

    private SessionDAO sessionDAO;

    public void setSessionDAO(SessionDAO sessionDAO) {
        this.sessionDAO = sessionDAO;
    }

    //即是否允许访问，返回true表示允许
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        String sid=CookieUtils.getCookie((HttpServletRequest) request, "sid");

        Collection<Session> sessions = sessionDAO.getActiveSessions();

        if(null != sessions && StringUtils.isNotBlank(sid)){
            for (Session s:sessions) {
                if (s.getId().equals(sid)) {
                    return true;
                }
            }
        }
        return false;
    }


    //表示访问拒绝时是否自己处理，如果返回true表示自己不处理且继续拦截器链执行，返回false表示自己已经处理了（比如重定向到另一个页面）。
    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        (request).getRequestDispatcher("/login").forward(request,response);
        return false;
    }
}
