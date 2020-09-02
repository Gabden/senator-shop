package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/group-sale")
public class GroupSaleController {
    private ProductService productService;

    public GroupSaleController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping(value = "/edit/category", method = RequestMethod.POST)
    public ResponseEntity editGroupDiscount(@RequestParam("category") String category, @RequestParam("discount") Integer discount) {
        if (discount == null || discount < 10 || discount > 100) {
            return ResponseEntity.badRequest().body("Значение скидки должно быть в пределах от 10 до 100");
        }

        List<Product> productsByCategory = productService.findProductsByProductCategoryContains(category);
        productsByCategory.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        return ResponseEntity.ok("Category sale successfully updated");
    }
}
