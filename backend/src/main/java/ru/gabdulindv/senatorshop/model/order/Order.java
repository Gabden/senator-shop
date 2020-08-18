package ru.gabdulindv.senatorshop.model.order;

import ru.gabdulindv.senatorshop.model.User;
import ru.gabdulindv.senatorshop.model.address.BillingAddress;
import ru.gabdulindv.senatorshop.model.address.ShippingAddress;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "ORDER_TABLE")
public class Order implements Serializable {

    private static final long serialVersionUID = 5906767062695827006L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long orderId;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId")
    private ReservedCart reservedCart;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "id")
    private User user;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "billingAddressId")
    private BillingAddress billingAddress;

    @OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "shippingAddressId")
    private ShippingAddress shippingAddress;


    private Timestamp timestamp;

    //can be created or confirmed or canceled
    private String status;


    public Order() {
        this.status = "created";
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public long getCustomerOrderId() {
        return orderId;
    }

    public void setCustomerOrderId(long customerOrderId) {
        this.orderId = customerOrderId;
    }

    public ReservedCart getReservedCart() {
        return reservedCart;
    }

    public void setReservedCart(ReservedCart reservedCart) {
        this.reservedCart = reservedCart;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    @Override
    public String toString() {
        return "Order{" +
                "customerOrderId=" + orderId +
                ", reservedCart=" + reservedCart +
                ", user=" + user +
                ", billingAddress=" + billingAddress +
                ", shippingAddress=" + shippingAddress +
                ", timestamp=" + timestamp +
                ", status='" + status + '\'' +
                '}';
    }
}