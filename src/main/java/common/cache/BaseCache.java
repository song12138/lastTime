package common.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**Cache操作基类
 * Created by paul on 2017/8/14.
 */
public class BaseCache implements ICache {

    private static final Logger logger = LoggerFactory.getLogger(BaseCache.class);


    /**
     * cache名称
     */
    private String cacheName;



    @Override
    public Object get(String key) {
        return null;
    }

    @Override
    public void put(String key, Object value) {

    }

    @Override
    public void update(String key, Object value) {

    }

    @Override
    public void remove(String key) {

    }

    @Override
    public void clear() {

    }
}
