package common.cache;

/** Cache工厂
 * Created by hasee on 2017-10-14.
 */
public class CacheFactory {
    public static ICache creatSysCache(){
        return SysCache.getInstance();
    }

    public static ICache creatUserCache(){
        return UserCache.getInstance();
    }


}
