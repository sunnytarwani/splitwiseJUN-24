package com.sunny.Model;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class User extends BaseModel {

    private String name;
    private String email;
    private String password;
    @ManyToMany
    private List<User> friends;
    @ManyToMany
    private List<Group> groups;
}
