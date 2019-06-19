package ru.asd.gradletest.entity;

public class UserRole  {

    @Override
    public String toString() {
        return role;
    }

    private Long id;


    private String role;

   /* public Set<User> getUsers() {
        return users;
    }*/

  /*  public void setUsers(Set<User> users) {
        this.users = users;
    }*/

 /*   @ManyToMany (fetch = FetchType.EAGER,mappedBy = "roles",cascade = CascadeType.PERSIST )
    private Set<User> users;
*/
    public Long getId() {
        return id;
    }

    public UserRole(String role) {
        this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public UserRole() {
    }

    @Override
    public boolean equals(Object obj) {
        boolean result=false;
        if (obj == null || obj.getClass() != getClass()) {
            result = false;
        } else {
            UserRole userRole = (UserRole) obj;
            if (this.role == userRole.getRole()) {
                result = true;
            }
        }
        return result;
    }


}
