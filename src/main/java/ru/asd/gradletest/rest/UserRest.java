package ru.asd.gradletest.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.asd.gradletest.entity.User;

import java.util.Arrays;
import java.util.List;

@Service
public class UserRest {
    private final String URL_USERS = "http://localhost:8080/employees";
    private final String URL_USERS_JSON = "http://localhost:8081/rest/userlist";

    public List<User> getUserList() {

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URL_USERS_JSON, User[].class);
        List<User> user = Arrays.asList(responseEntity.getBody());
        return user;
    }
}
