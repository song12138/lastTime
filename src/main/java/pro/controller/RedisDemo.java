package pro.controller;

import common.Redis.RedisCGlibProxy;
import common.Redis.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import redis.clients.jedis.ShardedJedisPool;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by paul on 2017/8/15.
 */
@Controller
public class RedisDemo {

    @Autowired
    private ShardedJedisPool shardedJedisPool;


    @Autowired
    private  RedisCGlibProxy redisCGlibProxy;

    @Autowired
    private RedisCache redisCache;





    @RequestMapping(value = "redis")
    public String redis(HttpServletRequest request, HttpServletResponse response, Model model){
//        ShardedJedis jedis = shardedJedisPool.getResource();
//        jedis.set("age", "18");
//        String age=jedis.get("age");
//        model.addAttribute("age", age);

        redisCache.set("height", "180");
        String height = redisCache.get("height");
        model.addAttribute("height", height);
        return "user";
    }

}
