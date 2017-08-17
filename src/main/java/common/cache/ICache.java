package common.cache;

/**
 * Created by paul on 2017/8/14.
 */
public interface ICache {

    Object get(String key);

    void put(String key, Object value);

    void update(String key, Object value);

    void remove(String key);
    /**
     * @Description 清空cache
     * @MethodName
     * @param
     * @return
     * @author songxiangfu [2274466718@qq.com]
     * @Date 2017/8/14 16:38
     */
    void clear();

}
