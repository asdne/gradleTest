package ru.asd.gradletest.entity;

import java.util.Objects;
import java.util.Set;


public class User  {


    private Long id;

    private String login;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return getId().equals(user.getId()) &&
                getLogin().equals(user.getLogin()) &&
                getPassword().equals(user.getPassword()) &&
                getRoles().equals(user.getRoles());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), getRoles());
    }

    private String password;


    private Set<UserRole> roles;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public User(String login, String password, Set<UserRole> roles) {
        this.login = login;
        this.password = password;
        this.roles = roles;
    }

    public User() {

    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getPassword() {
        return password;
    }


    public String getUsername() {
        return login;
    }


    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }



    public void setPassword(String password) {

        this.password=password;
      /*  BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        this.password= bCryptPasswordEncoder.encode(password);*/
        //       this.password = passwordEncoder.encode(password);
    }
}
