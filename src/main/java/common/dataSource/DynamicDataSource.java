package common.dataSource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**动态路由数据库
 * Created by paul on 2017/8/10.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        //当前的数据源
        return DataSourceContextHolder.getDsType();
    }
}
