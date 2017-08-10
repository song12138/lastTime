package pro.dao;

import common.annotation.MybatisDao;
import pro.entity.User;

import java.util.List;

/**
 * Created by paul on 2017/8/4.
 */
@MybatisDao
public interface UserDao {
    List<User> findAll();

    List<User> findUserByAttribute(User user);
}
