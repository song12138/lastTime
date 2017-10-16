package pro.controller;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pro.entity.User;
import pro.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by paul on 2017/8/10.
 */
@Aspect
@Controller
public class loginController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    //真正登录的POST请求由Filter完成
    @RequestMapping(value={"/","/login"})
    public String login(HttpServletRequest request, Model model){
        //获取subject
        Subject subject = SecurityUtils.getSubject();
        //获取principal
        String principal= (String) subject.getPrincipal();

        if(StringUtils.isNotBlank(principal)){
            //若已登录，则直接跳转至内部首页
            return "redirect:/success";
        }
        return "index";
    }

    @RequestMapping(value = "/success")
    public String loginSuccess(Model model){

        List<User> users= userServiceImpl.findAll();
        model.addAttribute("users", users);
        return "user";
    }
}
