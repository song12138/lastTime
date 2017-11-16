package pro.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.entity.SysUser;
import pro.service.ShiroService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

/**
 * Created by paul on 2017/10/20.
 */
@Controller
public class ShiroController {

    private static Logger logger = LoggerFactory.getLogger(ShiroController.class);
    @Autowired
    private DefaultWebSecurityManager securityManager;

    @Autowired
    private ShiroService shiroService;
    @Autowired
    private EnterpriseCacheSessionDAO sessionDAO;


    @RequestMapping(value = {"/login"})
    public String loginDo(HttpServletRequest request, Model model) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

//        Subject currentUser = SecurityUtils.getSubject();
//
//        if (currentUser.isAuthenticated()) {
//            return "redirect:/success";
//            }

        String error = null;

        String exceptionClassName= (String) request.getAttribute("shiroLoginFailure");

        if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名";
        } else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "密码错误";
        }  else if (exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        model.addAttribute("username", username);
        model.addAttribute("password", password);
        model.addAttribute("error", error);

//        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
//        Subject currentUser = SecurityUtils.getSubject();

//        try {
//            if (!currentUser.isAuthenticated()) {
//                token.setRememberMe(true);
//                currentUser.login(token);
//            }
//        } catch (UnknownAccountException e) {
//            logger.error("未知账户");
//            error = "未知账户";
//        } catch (IncorrectCredentialsException e) {
//            logger.error("密码错误");
//            error = "密码错误";
//        } catch (LockedAccountException e) {
//            logger.error("用户已被锁定");
//            error = "用户已被锁定";
//        } catch (ExcessiveAttemptsException e) {
//            logger.error("提示：登陆失败错误次数过多，请五分钟后重新登陆");
//            error = "提示：登陆失败错误次数过多，请五分钟后重新登陆";
//        }finally {
//            model.addAttribute("username", username);
//            model.addAttribute("password", password);
//
//            if (currentUser.isAuthenticated()) {
//                return "redirect:/success";
//            } else {
//                token.clear();
//                model.addAttribute("error", error);
//                return "login";
//            }
//        }

        return "/login";


    }


    @RequestMapping(value = "/success")
    public String success(Model model) {
        Collection<Session> sessions = sessionDAO.getActiveSessions();
        sessions.forEach(session -> {
            session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY);
        });
        return "loginSuccess";
    }

//    @RequestMapping(value = "/fail")
//    public String fail(){
//        return "loginFail";
//    }

    @RequestMapping(value = "/logout")
    public String logout(){
        return "login";
    }

    @RequiresPermissions(value = "one")
    @RequestMapping(value = "/one")
    public String one(){
        return "permission/one";
    }

    @RequiresPermissions(value = "two")
    @RequestMapping(value = "/two")
    public String two(){
        return "permission/two";
    }

    @RequiresPermissions(value = "three")
    @RequestMapping(value = "/three")
    public String three(){
        return "permission/three";
    }

    @RequestMapping(value="/three/add")
    public void  addUser(HttpServletRequest request){
        SysUser sysUser = new SysUser();
        sysUser.setUsername(request.getParameter("username"));
        sysUser.setPassword(request.getParameter("password"));
        shiroService.addUser(sysUser);

    }

}
