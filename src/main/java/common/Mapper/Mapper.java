package common.Mapper;

import tk.mybatis.mapper.common.MySqlMapper;

/**
 * Created by paul on 2017/12/15.
 */
public interface Mapper<T> extends tk.mybatis.mapper.common.Mapper<T>,MySqlMapper<T> {

}
