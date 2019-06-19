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
    private final String URL_USERS_LIST = "http://localhost:8081/rest/userlist";
    private final String NEW_USER_URL="http://localhost:8081/rest/user/new";
    private final String DELETE_USER_URL="http://localhost:8081/rest/user/";
    private final String UPDATE_USER_URL="http://localhost:8081/rest/user/update/";
    private RestTemplate restTemplate = new RestTemplate();

    public List<User> getUserList() {
     //   RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<User[]> responseEntity = restTemplate.getForEntity(URL_USERS_LIST, User[].class);
        List<User> user = Arrays.asList(responseEntity.getBody());
        return user;
    }

    public void newUser(String login,String password){

        User user= new User(login,password);
        User complUser= restTemplate.postForObject(NEW_USER_URL,user,User.class);
    }

    public void deleteUser(Long id){
        restTemplate.delete(DELETE_USER_URL+id);

    }

    public void updateUser(User user){
        restTemplate.put(UPDATE_USER_URL,user,User.class);
    }
}
