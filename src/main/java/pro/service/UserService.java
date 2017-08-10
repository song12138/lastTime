package pro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pro.dao.UserDao;
import pro.entity.User;

import java.util.List;

/**
 * Created by paul on 2017/8/4.
 */
@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public List<User> findAll(){
        return userDao.findAll();
    }

    public List<User> findUserByAttribute(User user){
        return userDao.findUserByAttribute(user);
    }
}
