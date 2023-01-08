package com.jcpdev.ModernGreenThumb.Model;

public class User {
    private String name;
    private String userId;
    private String token;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    private String email;
    public String getUserId() {
        return userId;
    }


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }



    public User(String name, String userId, String email){
        this.userId=userId;
        this.name=name;
        this.email=email;

    }
    public User(){}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
