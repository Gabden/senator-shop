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

    @RequestMapping(value = "/edit/color", method = RequestMethod.POST)
    public ResponseEntity editGroupDiscountColor(@RequestParam("color") String color, @RequestParam("discount") Integer discount) {
        if (discount == null || discount < 10 || discount > 100) {
            return ResponseEntity.badRequest().body("Значение скидки должно быть в пределах от 10 до 100");
        }

        List<Product> productsByType = productService.findProductsByProductDetails_ProductAlcoholColorContains(color);
        productsByType.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        return ResponseEntity.ok("Color sale successfully updated");
    }

    @RequestMapping(value = "/edit/type", method = RequestMethod.POST)
    public ResponseEntity editGroupDiscountType(@RequestParam("type") String type, @RequestParam("discount") Integer discount) {
        if (discount == null || discount < 10 || discount > 100) {
            return ResponseEntity.badRequest().body("Значение скидки должно быть в пределах от 10 до 100");
        }

        List<Product> productsByType = productService.findProductsByProductDetails_ProductTypeContains(type);
        productsByType.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        return ResponseEntity.ok("Type sale successfully updated");
    }

    @RequestMapping(value = "/edit/manufacturer", method = RequestMethod.POST)
    public ResponseEntity editGroupDiscountManufacturer(@RequestParam("manufacturer") String manufacturer, @RequestParam("discount") Integer discount) {
        if (discount == null || discount < 10 || discount > 100) {
            return ResponseEntity.badRequest().body("Значение скидки должно быть в пределах от 10 до 100");
        }

        List<Product> productsByManufacturer = productService.findProductsByProductDetails_ProductManufacturerContains(manufacturer);
        productsByManufacturer.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        return ResponseEntity.ok("Type sale successfully updated");
    }

    @RequestMapping(value = "/edit/country", method = RequestMethod.POST)
    public ResponseEntity editGroupDiscountCountry(@RequestParam("country") String country, @RequestParam("discount") Integer discount) {
        if (discount == null || discount < 10 || discount > 100) {
            return ResponseEntity.badRequest().body("Значение скидки должно быть в пределах от 10 до 100");
        }

        List<Product> productsByCountry = productService.findProductsByProductDetails_ProductCountryContains(country);
        productsByCountry.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        return ResponseEntity.ok("Type sale successfully updated");
    }

    @RequestMapping(value = "/edit/all", method = RequestMethod.POST)
    public ResponseEntity editGroupDiscountAll(@RequestParam("discount") Integer discount) {
        List<Product> products = productService.findAll();
        products.forEach(product -> {
            try {
                int newPrice = Integer.parseInt(product.getProductPrice()) + Integer.parseInt(product.getProductPrice()) * discount / 100;
                product.setProductPrice(String.valueOf(newPrice));
                productService.update(Optional.of(product));
            } catch (Exception e) {
                System.out.println("error changing price" + e);
            }

        });
        return ResponseEntity.ok("Type sale successfully updated");
    }
}
