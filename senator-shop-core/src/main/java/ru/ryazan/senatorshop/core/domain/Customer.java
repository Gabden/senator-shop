package ru.ryazan.senatorshop.core.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import ru.ryazan.senatorshop.core.domain.address.BillingAddress;
import ru.ryazan.senatorshop.core.domain.address.ShippingAddress;
import ru.ryazan.senatorshop.core.domain.cart.Cart;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
public class Customer implements Serializable {

    private static final long serialVersionUID = 7692196695514198213L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long customerId;

    @NotNull(message = "Name must not be null")
    @Size(min=2, max=40)
    private String customerName;


    @NotNull(message = "Password must not be null")
    private String customerPassword;

    @NotNull(message = "Password must not be null")
    @Transient
    private String customerPasswordAccept;

    @NotNull(message = "Phone must not be null")
    private String customerPhone;
    private boolean enabled;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "shippingAddressId")
    private ShippingAddress shippingAddress;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "cartId")
    @JsonIgnore
    private Cart cart;

    @OneToMany(mappedBy = "customer", orphanRemoval = true)
    private List<Favorites> favorites;

    private String FIOfirst;
    private String FIOmiddle;
    private String FIOlast;

    public Customer() {
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

    public List<Favorites> getFavorites() {
        return favorites;
    }

    public void setFavorites(List<Favorites> favorites) {
        this.favorites = favorites;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerPasswordAccept() {
        return customerPasswordAccept;
    }

    public void setCustomerPasswordAccept(String customerPasswordAccept) {
        this.customerPasswordAccept = customerPasswordAccept;
    }

    public String getCustomerPassword() {
        return customerPassword;
    }

    public void setCustomerPassword(String customerPassword) {
        this.customerPassword = customerPassword;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public BillingAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(BillingAddress billingAddress) {
        this.billingAddress = billingAddress;
    }

    public ShippingAddress getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(ShippingAddress shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
    }
}
