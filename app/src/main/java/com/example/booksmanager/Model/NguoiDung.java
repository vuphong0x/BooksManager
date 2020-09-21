package com.example.booksmanager.Model;

public class NguoiDung {
    private String name;
    private String numberPhone;
    private String userName;
    private String password;

    public NguoiDung() {
    }

    public NguoiDung(String userName, String password, String numberPhone, String name) {
        this.name = name;
        this.numberPhone = numberPhone;
        this.userName = userName;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumberPhone() {
        return numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
