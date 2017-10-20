package common.cache;


import common.constant.Global;

/**
 * Created by hasee on 2017-10-14.
 */
public class SysCache extends BaseCache implements ICache {
    public SysCache() {
        super();
        super.setCacheName(Global.SYS_CACHE);
    }

    private static SysCache instance;

    public static SysCache getInstance(){
        if (null == instance) {
            instance = new SysCache();
        }

        return instance;
    }
}
