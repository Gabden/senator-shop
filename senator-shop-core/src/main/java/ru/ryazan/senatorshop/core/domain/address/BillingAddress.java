package ru.ryazan.senatorshop.core.domain.address;

import ru.ryazan.senatorshop.core.domain.Customer;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class BillingAddress implements Serializable {

    private static final long serialVersionUID = 4968422341843391958L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long billingAddressId;
    private String streetName;
    private String apartmentNumber;
    private String city;
    private String country;
    private String zipCode;
    @OneToOne
    private Customer customer;

    public BillingAddress() {
    }

    public long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getApartmentNumber() {
        return apartmentNumber;
    }

    public void setApartmentNumber(String apartmentNumber) {
        this.apartmentNumber = apartmentNumber;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "BillingAddress{" +
                "streetName='" + streetName + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
