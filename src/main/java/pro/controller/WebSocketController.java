package pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by paul on 2017/12/7.
 */
@Controller
public class WebSocketController {

    @RequestMapping("/ws")
    public String wsocket() {
        return "websocket";
    }
}
