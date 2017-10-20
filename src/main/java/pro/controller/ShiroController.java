package pro.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by paul on 2017/10/20.
 */
@Controller
@RequestMapping(value = "/shiro")
public class ShiroController {

    private static Logger logger = LoggerFactory.getLogger(ShiroController.class);
    @Autowired
    private DefaultWebSecurityManager securityManager;


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginDo(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        SecurityUtils.setSecurityManager(securityManager);
        Subject currentUser = SecurityUtils.getSubject();

        try {
            currentUser.login(token);
        } catch (UnknownAccountException e) {
            logger.error("未知账户");
        } catch (IncorrectCredentialsException e) {
            logger.error("密码错误");
        } catch (LockedAccountException e) {
            logger.error("用户已被锁定");
        }
//        String principal= (String) currentUser.getPrincipal();
//        if (StringUtils.isNotBlank(principal)) {
         if(currentUser.isAuthenticated()){
            return "loginSuccess";
        }else {
            token.clear();
            return "loginFail";
        }


    }

    @RequestMapping(value = "/success")
    public String success(){
        return "loginSuccess";
    }

    @RequestMapping(value = "/fail")
    public String fail(){
        return "loginFail";
    }
}
