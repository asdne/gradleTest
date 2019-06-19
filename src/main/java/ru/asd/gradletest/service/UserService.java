package ru.asd.gradletest.service;

import org.springframework.stereotype.Service;
import ru.asd.gradletest.entity.User;

import java.util.List;

@Service
public interface UserService {
    List<User> getUserList();

    void saveUser(User user);

    void deleteUser(Long id);

    void addNewUser(String login, String password);

    void updateUser(User user);
}
