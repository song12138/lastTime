package pro.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

/**
 * sse消息推送
 * Created by paul on 2017/12/27.
 */
@Controller
public class SseController {

    @RequestMapping(value = "/push", produces = "text/event-stream")//输出的媒体类型，这是服务端sse的支持
    @ResponseBody
    public String push() {
        Random r = new Random();

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "sse推送：" + r.nextInt() + "\n\n";
    }

    @RequestMapping(value = "sse")
    public String ssee(){
        return "sse";
    }

}
