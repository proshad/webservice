package com.generic.dao;


import com.generic.entity.User;

import java.util.List;

/**
 * User: proshad
 * Date: 12/1/13
 */
public interface UserDao {
    public List<User> listOfUsers();

    public void saveOrUpdate(User user);

    public void removeUser(String emailID);

    public User detailsOfUser(String emailID);
}
