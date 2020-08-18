package ru.gabdulindv.senatorshop.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_details_description")
public class UserDetailsDescription implements Serializable {
    private static final long serialVersionUID = 7692196695514198213L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String phone;
    private String FIOfirst;
    private String FIOmiddle;
    private String FIOlast;

    public UserDetailsDescription() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFIOfirst() {
        return FIOfirst;
    }

    public void setFIOfirst(String FIOfirst) {
        this.FIOfirst = FIOfirst;
    }

    public String getFIOmiddle() {
        return FIOmiddle;
    }

    public void setFIOmiddle(String FIOmiddle) {
        this.FIOmiddle = FIOmiddle;
    }

    public String getFIOlast() {
        return FIOlast;
    }

    public void setFIOlast(String FIOlast) {
        this.FIOlast = FIOlast;
    }

    @Override
    public String toString() {
        return "UserDetailsDescription{" +
                "id=" + id +
                ", phone='" + phone + '\'' +
                ", FIOfirst='" + FIOfirst + '\'' +
                ", FIOmiddle='" + FIOmiddle + '\'' +
                ", FIOlast='" + FIOlast + '\'' +
                '}';
    }
}
