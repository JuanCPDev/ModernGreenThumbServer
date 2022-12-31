package com.jcpdev.ModernGreenThumb.Model;

public class User {
    private String name;
    private String userId;

    public User(String name, String userId){
        this.userId=userId;
        this.name=name;
    }
    public User(){}

    public String getToken() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
