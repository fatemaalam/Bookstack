package com.cse.mist.bookstack;

/**
 * Created by User on 8/18/2017.
 */

public class User {

    public String uname;
    public String uni;
    public String roll;
    public String email;
    public String password;
    public String number;

    public User() {

    }

    public User(String uname, String uni, String roll, String email, String password, String number) {
        this.uname = uname;
        this.uni = uni;
        this.roll = roll;
        this.email = email;
        this.password = password;
        this.number = number;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return uname;
    }

    public String getPhone() {
        return number;
    }

    public String getRoll() {
        return roll;
    }

    public String getUni() {
        return uni;
    }

}

