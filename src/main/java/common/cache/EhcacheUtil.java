package common.cache;

import common.context.ApplicationContextHolder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by paul on 2017/10/11.
 */
public class EhcacheUtil implements ICache{

    private static Logger logger = LoggerFactory.getLogger(EhcacheUtil.class);

//    private static final String path = "/cache/ehcache-local.xml";
//
//    private URL url;
//
//    private CacheManager cacheManager;
//
//    private static EhcacheUtil ehCache;
//
//    private EhcacheUtil(String path) {
//        url = getClass().getResource(path);
//        cacheManager = CacheManager.create(url);
//    }
//
//    public static EhcacheUtil getInstance() {
//        if (ehCache== null) {
//            ehCache= new EhcacheUtil(path);
//        }
//        return ehCache;
//    }

//    private static EhCacheCacheManager ehCacheCacheManager = ApplicationContextHolder.newInstance().getBean(EhCacheCacheManager.class);
//    private static CacheManager cacheManager = ehCacheCacheManager.getCacheManager();

    private CacheManager cacheManager = ApplicationContextHolder.newInstance().getBean("ehcacheManagerFactory");

    private  static EhcacheUtil ehCache;

    public static EhcacheUtil getInstance() {
        if (ehCache== null) {
            ehCache = new EhcacheUtil();
        }
        return ehCache;
    }






    @Override
    public void put(String cacheName, String key, Object value) {
        Element element = new Element(key, value);
        cacheManager.getCache(cacheName).put(element);
    }


    @Override
    public Object get(String cacheName, String key) {
        Element element = cacheManager.getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    @Override
    public Object get(String cacheName, String key, Object defaultValue) {
        Object obj = get(cacheName,key);
        return obj == null ? defaultValue : obj;
    }

    @Override
    public void remove(String cacheName, String key) {
        cacheManager.getCache(cacheName).remove(key);
    }

    @Override
    public void update(String cacheName, String key, Object value) {
        remove(cacheName,key);
        put(cacheName,key, value);
    }

    /**
     * @description 清空缓存
     * @methodName clear
     * @param
     * @return void
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 17:11
     */
    @Override
    public void clear(String cacheName) {
        Cache cache = cacheManager.getCache(cacheName);
        if (null != cache) {
            cacheManager.removeCache(cacheName);
        }
    }



}
