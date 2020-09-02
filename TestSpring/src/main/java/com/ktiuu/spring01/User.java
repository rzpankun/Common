package com.ktiuu.spring01;

/**
 * @Create by pankun
 * @DATE 2020/6/28
 */
public class User {
    private String id;
    private String userName;
    private String email;

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }



    public void setId(String id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public void tet(){

    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
