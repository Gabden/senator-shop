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
        if (productInCart.getSalePrice() != null && productInCart.getSalePrice().length() > 0) {
            cartItem.setCartItemFinalPrice(productInCart.getSalePrice());
            cartItemService.update(cartItem);
            return;
        }

        if (quantity > 0 && quantity < 3) {
            priceAfterSale *= 0.9;
            String finalPrice = String.valueOf(priceAfterSale);
            cartItem.setCartItemFinalPrice(finalPrice);
            cartItemService.update(cartItem);

        } else if (quantity >= 3 && quantity < 6) {
            priceAfterSale *= 0.85;
            String finalPrice = String.valueOf(priceAfterSale);
            cartItem.setCartItemFinalPrice(finalPrice);
            cartItemService.update(cartItem);
        } else if (quantity >= 6) {
            priceAfterSale *= 0.75;
            String finalPrice = String.valueOf(priceAfterSale);
            cartItem.setCartItemFinalPrice(finalPrice);
            cartItemService.update(cartItem);
        }
    }
}
