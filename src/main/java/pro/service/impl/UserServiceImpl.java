package pro.service.impl;

import common.annotation.JDataSource;
import common.dataSource.DataSourceType;
import common.metaData.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import pro.dao.UserDao;
import pro.entity.User;

import java.util.List;

/**
 * Created by paul on 2017/8/4.
 */
@Service
@JDataSource(value = DataSourceType.DATASOURCE1)
public class UserServiceImpl {
    @Autowired
    private UserDao userDao;



//   @Async
   @Scheduled(cron = "0 29 10 * * ? *")
    public List<User> findAll(){
        return userDao.findAll();
    }

    public List<User> findAll(Page<User> page){
        return userDao.findAll(page);
    }

    public List<User> findUserByAttribute(User user){
        return userDao.findUserByAttribute(user);
    }
}
