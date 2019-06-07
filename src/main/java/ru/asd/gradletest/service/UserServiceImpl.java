package ru.asd.gradletest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import ru.asd.gradletest.entity.User;
import ru.asd.gradletest.rest.UserRest;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRest userRest;

    @Override
    @Bean
    public List<User> getUserList() {
        User user = new User("admin", "password");
        User user2 = new User("admin1", "password1");
        User user1 = new User("admin15", "password1555");
        List<User> userList = new ArrayList<>();
        userList.add(user);
        userList.add(user1);
        userList.add(user2);
       // return userList;
    return userRest.getUserList();
    }

    @Override
    public void saveUser(User user) {

    }
}
