package ru.ryazan.senatorshop.core.utils;

import org.springframework.stereotype.Component;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartItemService;

@Component
public class PriceCalculator {
    private CartItemService cartItemService;

    public PriceCalculator(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    public void finalPriceItem(CartItem cartItem, int quantity) {
        Product productInCart = cartItem.getProduct();
        cartItem.setCartItemPrice(productInCart.getProductPrice());
        int priceAfterSale = Integer.parseInt(productInCart.getProductPrice());
        int discount = cartItem.getProduct().getDiscount();
        if (productInCart.getSalePrice() != null && productInCart.getSalePrice().length() > 0) {
            cartItem.setCartItemFinalPrice(productInCart.getSalePrice());
            cartItemService.update(cartItem);
            return;
        }

        if (discount > 10) {
            updateItemPrice(priceAfterSale, cartItem, discount);
        }

        if (quantity > 0 && quantity < 3 && discount == 10) {
            updateItemPrice(priceAfterSale, cartItem, 10);
        } else if (quantity >= 3 && quantity < 6 && discount < 15) {
            updateItemPrice(priceAfterSale, cartItem, 15);
        } else if (quantity >= 6 && discount < 25) {
            updateItemPrice(priceAfterSale, cartItem, 25);
        }
    }

    private void updateItemPrice(int priceAfterSale, CartItem cartItem, int discount) {
        int newPrice = priceAfterSale - (priceAfterSale * discount) / 100;
        String finalPrice = String.valueOf(newPrice);
        cartItem.setCartItemFinalPrice(finalPrice);
        cartItemService.update(cartItem);
    }
}
