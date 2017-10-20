package common.cache;


import common.constant.Global;

/**
 * Created by hasee on 2017-10-14.
 */
public class UserCache extends BaseCache implements ICache {
    public UserCache() {
        super();
        super.setCacheName(Global.USER_CACHE);
    }

    private static UserCache instance;

    public static UserCache getInstance(){
        if (null == instance) {
            instance = new UserCache();
        }
        return instance;
    }

}
