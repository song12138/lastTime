package pro.controller;

import common.metaData.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by paul on 2017/9/27.
 */
@Controller
public class PageController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/page")
    public String testUserPage(HttpServletRequest request, HttpServletResponse response, Model model) {
        model.addAttribute("userPage", userService.findAll());
        return "userPage";
    }

    @RequestMapping(value = "/restPage")
    @ResponseBody
    public Page testRestUserPage(HttpServletRequest request, HttpServletResponse response,Page page) {
//        AjaxPage ajaxPage = new AjaxPage();
//        ajaxPage.setCells(userService.findAll());
//        Page page = new Page();
        page.setData(userService.findAll(page));
        return  page;
    }
}
