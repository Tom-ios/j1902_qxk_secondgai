package com.qf.j1902.pojo;

import lombok.Data;

/**
 * Created by 86181 on 2019/5/27.
 */
@Data
public class User {
    private int id;
    private String name;
    private String password;
    private String email;
    private String sort;
    private String other;
    private String renzheng;

    public User(int id, String name, String password, String email, String sort, String other, String renzheng) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.sort = sort;
        this.other = other;
        this.renzheng = renzheng;
    }

    public User() {
    }

    public User(String name, String password, String sort) {
        this.name = name;
        this.password = password;
        this.sort = sort;
    }


    public User(String name, String password, String email, String sort) {

        this.name = name;
        this.password = password;
        this.email = email;
        this.sort = sort;
    }

    public User(String name, String password, String email, String sort, String other) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.sort = sort;
        this.other = other;
    }
}
