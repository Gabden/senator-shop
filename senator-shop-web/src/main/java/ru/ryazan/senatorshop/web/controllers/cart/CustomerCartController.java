package ru.ryazan.senatorshop.web.controllers.cart;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.cart.Cart;
import ru.ryazan.senatorshop.core.domain.cart.CartItem;
import ru.ryazan.senatorshop.core.service.CartService;
import ru.ryazan.senatorshop.core.service.CustomerService;
import ru.ryazan.senatorshop.core.utils.PriceCalculator;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/customer/cart")
public class CustomerCartController {

    private CustomerService customerService;
    private CartService cartService;
    private PriceCalculator priceCalculator;

    public CustomerCartController(CustomerService customerService, CartService cartService, PriceCalculator priceCalculator) {
        this.customerService = customerService;
        this.cartService = cartService;
        this.priceCalculator = priceCalculator;
    }

    @RequestMapping
    public String getCustomerCart (@AuthenticationPrincipal UserDetails userDetails, HttpServletRequest request){
        if (userDetails != null) {
            Customer customer = customerService.findCustomerByCustomerName(userDetails.getUsername());
            Cart cart = customer.getCart();
            long cartId = cart.getCartId();


            return "redirect:/customer/cart/" + cartId;
        }
        String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
        return "redirect:/customer/cart/session/" + sessionId;
    }

    @RequestMapping("/{cartId}")
    public String getCartRedirect(@PathVariable("cartId") Long id, Model model) {
        Optional<Cart> cart = cartService.read(id);
        int grandTotal = 0;
        cart.ifPresent(cart1 -> updatePriceItems(cart1, model));


        model.addAttribute("cart", cart.get().getCartItems());
        for (CartItem value : cart.get().getCartItems()) {
            grandTotal += value.getTotalPrice();
        }

        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("cartId", id);
        return "cart";
    }

    @RequestMapping("session/{sessionId}")
    public String getCartRedirectWithSession(@PathVariable("sessionId") String id, Model model, HttpServletRequest request) {
        String sessionId = String.valueOf(request.getSession().getAttribute("USERSESSION"));
        Optional<Cart> cart = cartService.readBySessionId(sessionId);
        int grandTotal = 0;

        cart.ifPresent(cart1 -> updatePriceItems(cart1, model));

        if (cart.isPresent()) {
            model.addAttribute("cart", cart.get().getCartItems());
            for (CartItem value2 : cart.get().getCartItems()) {
                grandTotal += value2.getTotalPrice();
            }
        }


        model.addAttribute("grandTotal", grandTotal);
        model.addAttribute("cartId", id);
        return "cart";
    }

    private void updatePriceItems(Cart cart, Model model) {
        List<CartItem> alcoListInCart = cart.getCartItems().stream().filter(cartItem -> cartItem.getProduct().getProductCategory().contains("alco")).collect(Collectors.toList());
        int numberOfAlcoholPositions = alcoListInCart.stream().mapToInt(CartItem::getQuantity).sum();
        model.addAttribute("quantity", numberOfAlcoholPositions);
        alcoListInCart.forEach(cartItem -> {
            priceCalculator.finalPriceItem(cartItem, numberOfAlcoholPositions);
        });
    }
}
