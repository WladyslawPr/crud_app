package com.example.crud_app.controller.rest;

public class LoginCredentials {
    private String name;
    private String password;

    public LoginCredentials (String name, String password) {
        this.name = name;
        this.password = password;
    }

    public String getName () {
        return name;
    }

    public String getPassword () {
        return password;
    }

}
