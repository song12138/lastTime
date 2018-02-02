package pro.controller;

import common.rest.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import pro.entity.User;
import pro.service.impl.UserServiceImpl;

/**
 * Created by paul on 2017/12/13.
 */
@org.springframework.web.bind.annotation.RestController
public class RestController {

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private User user;


    @GetMapping(value = "user")
    public Response getAllUser() {
        Response response = new Response();
        response.success();
//        response.setData(userService.findAll());
        response.setData(user);
        return response;
    }




}
