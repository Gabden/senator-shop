package ru.gabdulindv.senatorshop.model.account;

public class FioModel {
    private String fiofirst;
    private String fiomiddle;
    private String fiolast;
    private String phone;
    private String username;
    private String oldPassword;
    private String newPassword;

    public FioModel() {
    }

    public FioModel(String fiofirst, String fiomiddle, String fiolast, String phone, String username, String oldPassword, String newPassword) {
        this.fiofirst = fiofirst;
        this.fiomiddle = fiomiddle;
        this.fiolast = fiolast;
        this.phone = phone;
        this.username = username;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public String getFiofirst() {
        return fiofirst;
    }

    public void setFiofirst(String fiofirst) {
        this.fiofirst = fiofirst;
    }

    public String getFiomiddle() {
        return fiomiddle;
    }

    public void setFiomiddle(String fiomiddle) {
        this.fiomiddle = fiomiddle;
    }

    public String getFiolast() {
        return fiolast;
    }

    public void setFiolast(String fiolast) {
        this.fiolast = fiolast;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "FioModel{" +
                "fiofirst='" + fiofirst + '\'' +
                ", fiomiddle='" + fiomiddle + '\'' +
                ", fiolast='" + fiolast + '\'' +
                ", phone='" + phone + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
