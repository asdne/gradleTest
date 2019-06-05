package ru.asd.gradletest.service;

import org.springframework.stereotype.Service;
import ru.asd.gradletest.entity.User;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Override
    public List<User> getUserList() {
        return null;
    }
}
