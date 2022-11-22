package com.example.ql_cayxanh;

public class User {
    private int UserID;
    private String Account,  Gmail, Password;


    public User() {
    }

    public User(int userID, String account, String gmail, String password) {
        UserID = userID;
        Account = account;

        Gmail = gmail;
        Password = password;

    }

    public User(String account, String gmail, String password) {
        Account = account;

        Gmail = gmail;
        Password = password;

    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }



    public String getGmail() {
        return Gmail;
    }

    public void setGmail(String gmail) {
        Gmail = gmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }



    public String getAccount() {
        return Account;
    }

    public void setAccount(String account) {
        Account = account;
    }

    //create to commit, nothing to do
    public void JustTest(){};
}
