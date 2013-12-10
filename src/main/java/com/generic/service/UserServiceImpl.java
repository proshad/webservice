package com.generic.service;

import com.generic.dao.UserDao;
import com.generic.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * User: proshad
 * Date: 12/1/13
 */
@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> listOfUsers() {
        return userDao.listOfUsers();
    }

    @Override
    public void saveOrUpdate(User user) {
        userDao.saveOrUpdate(user);
    }

    @Override
    public void removeUser(String emailID) {
        userDao.removeUser(emailID);
    }

    @Override
    public User detailsOfUser(String emailID) {
        return userDao.detailsOfUser(emailID);
    }
}
