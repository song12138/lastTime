package common.util;

import common.context.ApplicationContextHolder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * cacheUtil
 * Created by paul on 2017/8/14.
 */
public class CacheUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CacheUtil.class);

    private static CacheManager cacheManager= ApplicationContextHolder.newInstance().getBean("cacheManager");

    private static CacheManager getCacheManager(){
        return cacheManager;
    }

    /**
     * @Description
     * @MethodName getcCache
     * @param cacheName
     * @return net.sf.ehcache.Cache
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/14 16:58
     */
    private static Cache getcCache(String cacheName){
        Cache cache;
        cache = cacheManager.getCache(cacheName);
        if(null == cache){
           cacheManager.addCache(cacheName);
            cache = cacheManager.getCache(cacheName);
            cache.getCacheConfiguration().setEternal(true);
        }
        return cache;
    }





    /**
     * @Description获取缓存
     * @MethodName get
     * @param cacheName
     * @param key
     * @return java.lang.Object
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/14 17:03
     */
    public static Object get(String cacheName,String key){
        Element element = getcCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    /**
     * @Description 写入缓存
     * @MethodName put
     * @param cacheName
     * @param key
     * @param value
     * @return void
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/14 17:06
     */
    public static void put(String cacheName,String key,Object value){
        Element element = new Element(key, value);
        getcCache(cacheName).put(element);
    }

    /**
     * @Description 从缓存中移除
     * @MethodName remove
     * @param cacheName
     * @param key
     * @return void
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/14 17:07
     */
    public static void remove(String cacheName,String key){
        getcCache(cacheName).remove(key);
    }




}
