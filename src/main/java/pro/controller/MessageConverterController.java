package pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pro.entity.User;

/**
 * Created by paul on 2017/12/27.
 */
@Controller
public class MessageConverterController {

    @RequestMapping(value = "/convert", produces = "application/x-wisely")
    @ResponseBody
    public User convert(@RequestBody User user) {
        return user;
    }

    @RequestMapping("/converter")
    public String converter() {
        return "converter";
    }
}
