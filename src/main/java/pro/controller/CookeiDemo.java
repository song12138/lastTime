package pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;


/**
 * Created by paul on 2017/8/14.
 */
@Controller
public class CookeiDemo {

    @RequestMapping(value = "cookie")
    public String cookie(HttpServletRequest request, HttpServletResponse response,Model model){
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        Cookie[] cookies = request.getCookies();
        StringBuffer msg = new StringBuffer();
        if(null != cookies){
            for (Cookie c:cookies){
                if("lastAccessTime".equals(c.getName())){
                    Long lastTime =Long.parseLong(c.getValue()) ;
                    Date data=new Date(lastTime);
                    msg =msg.append("您上次访问的时间是:") ;
                    msg.append(data.toString());
                }else{
                    msg.delete(0, msg.length());
                    msg.append("这是您第一次访问本站！");
                }
            }
        }

        Cookie cookie = new Cookie("lastAccessTime", System.currentTimeMillis()+"");
        response.addCookie(cookie);
        model.addAttribute("msg", msg);
        return "user";
    }

}
