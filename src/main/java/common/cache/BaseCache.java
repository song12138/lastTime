package common.cache;


import common.context.ApplicationContextHolder;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by paul on 2017/10/11.
 */
public class BaseCache implements ICache {

    private static Logger logger = LoggerFactory.getLogger(BaseCache.class);

    private CacheManager cacheManager = ApplicationContextHolder.newInstance().getBean("ehcacheManagerFactory");

    private String cacheName;

    public String getCacheName() {
        return cacheName;
    }

    /**
     * @description
     * @methodName setCacheName
     * @param cacheName cacheName
     * @author william [yeemin_shon@163.com]
     * @time 2017/10/16 16:55
     */
    public void setCacheName(String cacheName) {
        this.cacheName = cacheName;

    }

    @Override
    public void put(String key, Object value) {
        Element element = new Element(key, value);
        cacheManager.getCache(cacheName).put(element);
    }


    @Override
    public Object get(String key) {
        Element element = cacheManager.getCache(cacheName).get(key);
        return element == null ? null : element.getObjectValue();
    }

    @Override
    public Object get(String key, Object defaultValue) {
        Object obj = get(key);
        return obj == null ? defaultValue : obj;
    }

    @Override
    public void remove(String key) {
        cacheManager.getCache(cacheName).remove(key);
    }

    @Override
    public void update(String key, Object value) {
        remove(key);
        put(key, value);
    }

    /**
     * @param
     * @return void
     * @description 清空缓存
     * @methodName clear
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 17:11
     */
    @Override
    public void clear() {
        if (cacheManager.cacheExists(cacheName)) {
            cacheManager.removeCache(cacheName);
        }

    }


}
