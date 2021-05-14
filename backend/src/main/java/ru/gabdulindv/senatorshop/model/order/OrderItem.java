package ru.gabdulindv.senatorshop.model.order;

public class OrderItem {
    private Long productId;
    private String finalPrice;
    private int quantity;
    private String productName;

    public OrderItem(Long productId, String finalPrice, int quantity, String productName) {
        this.productId = productId;
        this.finalPrice = finalPrice;
        this.quantity = quantity;
        this.productName = productName;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getFinalPrice() {
        return finalPrice;
    }

    public void setFinalPrice(String finalPrice) {
        this.finalPrice = finalPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    @Override
    public String toString() {
        return "OrderTemplateData{" +
                "productId=" + productId +
                ", finalPrice='" + finalPrice + '\'' +
                ", quantity=" + quantity +
                ", productName='" + productName + '\'' +
                '}';
    }
}