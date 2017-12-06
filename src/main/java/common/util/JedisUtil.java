package common.util;

import common.context.ApplicationContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.util.Map;
import java.util.Set;

/**
 * Created by paul on 2017/11/17.
 */
public class JedisUtil {

    private static Logger logger = LoggerFactory.getLogger(JedisUtil.class);

    private static Jedis jedis = ApplicationContextHolder.newInstance().getBean(Jedis.class);

    /**
     * 释放资源
     */
    public static void closeJedis() {
        try {
            jedis.close();
        } catch (Exception e) {
            logger.error("");
        }
    }

    /**
     * 更新缓存
     * @param key
     * @param value
     */
    public static void putCache(String key, String value) {
        try {
            jedis.set(key, value);
        } finally {
            closeJedis();
        }
    }

    /**
     * 更新缓存
     * @param key
     * @param value
     */
    public static void putCache(byte[] key, byte[] value) {
        try {
            jedis.set(key, value);
        } finally {
            closeJedis();
        }
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public static String getCache(String key) {
        String value = null;
        try {
            value = jedis.get(key);
        } finally {
            closeJedis();
        }
        return value;
    }

    /**
     * 获取值
     * @param key
     * @return
     */
    public static byte[] getCache(byte[] key) {
        byte[] value = null;
        try {
            value = jedis.get(key);
        } finally {
            closeJedis();
        }
        return value;
    }

    /**
     * 根据key 获取hash对象
     * @param key
     * @return
     */
    public static Map<String, String> getHashCache(String key) {
        Map<String, String> map = null;
        try {
            map = jedis.hgetAll(key);
        } finally {
            closeJedis();
        }
        return map;
    }

    /**
     * 根据key 更新hash对象
     * @param key
     * @param hash
     */
    public static void putHashCache(String key, Map<String, String> hash) {
        try {
            jedis.hmset(key, hash);
            jedis.expire(key, 10 * 86400);    //生命有效期10天
        } finally {
            closeJedis();
        }
    }

    /**
     * 根据KEY 删除缓存
     * @param key
     */
    public static void deleteCache(String key) {
        try {
            jedis.del(key);
        } finally {
            closeJedis();
        }
    }

    /**
     *  删除多个
     * @param keys
     */
    public static void deleteCaches(String keys) {
        try {
            Set<String> keySet = jedis.keys(keys);
            keySet.forEach(k->{
                jedis.del(k);
            });
        }finally {
            closeJedis();
        }
    }

}
