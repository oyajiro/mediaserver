package com.oyajiro.entity;

/**
 * Created by user on 30.07.16.
 */
public class User extends BaseEntity {

    private String login;

    public static User createFromLogin(String login) {
        return new User(login);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    private User(String login) {
        this.login = login;
    }
}
