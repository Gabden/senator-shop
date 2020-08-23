package ru.gabdulindv.senatorshop.model.account;

public class FioModel {
    private String fiofirst;
    private String fiomiddle;
    private String fiolast;
    private String phone;

    public FioModel() {
    }

    public FioModel(String fiofirst, String fiomiddle, String fiolast, String phone) {
        this.fiofirst = fiofirst;
        this.fiomiddle = fiomiddle;
        this.fiolast = fiolast;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "FioModel{" +
                "fiofirst='" + fiofirst + '\'' +
                ", fiomiddle='" + fiomiddle + '\'' +
                ", fiolast='" + fiolast + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
