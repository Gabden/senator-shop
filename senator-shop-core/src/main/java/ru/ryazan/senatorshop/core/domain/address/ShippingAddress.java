package ru.ryazan.senatorshop.core.domain.address;

import ru.ryazan.senatorshop.core.domain.Customer;

import javax.persistence.*;
import java.io.Serializable;
@Entity
public class ShippingAddress implements Serializable {

    private static final long serialVersionUID = -173270705219326818L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long shippingAddressId;
    private String streetName;
    private String apartmentNumber;
    private String city;
    private String country;
    private String zipCode;
    @OneToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    private Customer customer;

    public ShippingAddress() {
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public long getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(long shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
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

    @Override
    public String toString() {
        return "ShippingAddress{" +
                "streetName='" + streetName + '\'' +
                ", apartmentNumber='" + apartmentNumber + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", zipCode='" + zipCode + '\'' +
                '}';
    }
}
