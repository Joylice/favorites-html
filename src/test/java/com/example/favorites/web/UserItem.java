package com.example.favorites.web;

import java.util.List;

/**
 * Created by cuiyy on 2017/11/13.
 */
public class UserItem {
    private String Id;
    private String UserName;
    private String PassWord;
    private List<String> roles;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getPassWord() {
        return PassWord;
    }

    public void setPassWord(String passWord) {
        PassWord = passWord;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }


    @Override
    public String toString() {
        return "UserItem{" +
                "Id='" + Id + '\'' +
                ", UserName='" + UserName + '\'' +
                ", PassWord='" + PassWord + '\'' +
                ", roles=" + roles +
                '}';
    }
}
