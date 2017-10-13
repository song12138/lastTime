package pro.dao;

import common.annotation.JDataSource;
import common.annotation.MybatisDao;
import common.dataSource.DataSourceType;
import common.metaData.Page;
import pro.entity.User;

import java.util.List;

/**
 * Created by paul on 2017/8/4.
 */
@MybatisDao
public interface UserDao {
    List<User> findAll();
    List<User> findAll(Page<User> page);

    List<User> findUserByAttribute(User user);
}
