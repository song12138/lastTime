package common.dataSource;

/**切换数据库工具类
 * Created by paul on 2017/8/11.
 */
public class DataSourceContextHolder {

    private static final ThreadLocal<String> contextHolder = new ThreadLocal<>();

    public static void setDsType(String dsType){
            contextHolder.set(dsType);
    }

    public static String getDsType(){
        return contextHolder.get();
    }

    public static void removeDsType(){
        contextHolder.remove();
    }
}
