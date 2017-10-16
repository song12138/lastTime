package common.cache;

/** 缓存操作接口
 * Created by paul on 2017/10/10.
 */
public interface ICache {
    /**
     * @description
     * @methodName put
     * @param key key
     * @param value value
     * @return void
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 15:20
     */
    void  put(String cacheName, String key, Object value);



    /**
     * @description
     * @methodName get
     * @param key key
     * @return java.lang.Object
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 14:03
     */
    Object get(String cacheName, String key);

    /**
     * @description
     * @methodName get
     * @param key key
     * @param defaultValue defaultValue
     * @return java.lang.Object
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 15:26
     */
    Object get(String cacheName, String key, Object defaultValue);

    /**
     * @description
     * @methodName remove
     * @param key key
     * @return void
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 14:04
     */
    void remove(String cacheName, String key);

    /**
     * @description
     * @methodName update
     * @param key key
     * @param value value
     * @return void
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 14:04
     */
    void update(String cacheName, String key, Object value);

    /**
     * @description 清除缓存
     * @methodName clear
     * @param
     * @return void
     * @author songxingfu [2274466718@qq.com]
     * @time 2017/10/10 14:05
     */
    void clear(String cacheName);


}
