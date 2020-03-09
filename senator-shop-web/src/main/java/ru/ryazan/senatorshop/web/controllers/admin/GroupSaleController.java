package ru.ryazan.senatorshop.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;

import javax.annotation.PostConstruct;
import java.util.*;

@Controller
@RequestMapping("/admin/group-discount")
public class GroupSaleController {

    private ProductService productService;
    private Set<String> countriesNames;
    private Set<String> manufacturersNames;
    private Set<String> productTypes;
    private List<Product> all;

    public GroupSaleController(ProductService productService) {
        this.productService = productService;
        countriesNames = new TreeSet<>();
        manufacturersNames = new TreeSet<>();
        productTypes = new TreeSet<>();
        all = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        all = productService.findAll();
        all.forEach(product -> {
            if (product.getProductCountry() != null && product.getProductCountry().length() > 0) {
                countriesNames.add(capitalizeFirstLetter(product.getProductCountry().trim()));
            }
            if (product.getProductManufacturer() != null && product.getProductManufacturer().length() > 0) {
                manufacturersNames.add(capitalizeFirstLetter(product.getProductManufacturer().trim()));
            }
            if (product.getProductType() != null && product.getProductType().length() > 0) {
                productTypes.add(capitalizeFirstLetter(product.getProductType().trim()));
            }
        });
    }

    private String capitalizeFirstLetter(String str) {
        if (str != null && str.length() > 0) {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        } else {
            return "";
        }

    }

    @RequestMapping("/edit")
    public String editGroupDiscount(Model model) {
        model.addAttribute("types", productTypes);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("countries", countriesNames);
        return "admin/change-group-discount";
    }

    @RequestMapping(value = "/edit/category", method = RequestMethod.POST)
    public String editGroupDiscount(@RequestParam("category") String category, @RequestParam("discount") Integer discount, Model model) {
        if (discount == null || discount < 10 || discount > 100) {
            model.addAttribute("error", "Значение скидки должно быть в пределах от 10 до 100");
            return "admin/change-group-discount";
        }

        List<Product> productsByCategory = productService.findProductsByProductCategoryContains(category);
        productsByCategory.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        model.addAttribute("updateSuccess", "Скидка успешно обновлена");
        model.addAttribute("types", productTypes);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("countries", countriesNames);
        return "admin/change-group-discount";
    }

    @RequestMapping(value = "/edit/type", method = RequestMethod.POST)
    public String editGroupDiscountType(@RequestParam("type") String type, @RequestParam("discount") Integer discount, Model model) {
        if (discount == null || discount < 10 || discount > 100) {
            model.addAttribute("error", "Значение скидки должно быть в пределах от 10 до 100");
            return "admin/change-group-discount";
        }

        List<Product> productsByType = productService.findProductsByProductTypeContains(type.toLowerCase());
        productsByType.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        model.addAttribute("updateSuccess", "Скидка успешно обновлена");
        model.addAttribute("types", productTypes);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("countries", countriesNames);
        return "admin/change-group-discount";
    }

    @RequestMapping(value = "/edit/manufacturer", method = RequestMethod.POST)
    public String editGroupDiscountManufacturer(@RequestParam("manufacturer") String manufacturer, @RequestParam("discount") Integer discount, Model model) {
        if (discount == null || discount < 10 || discount > 100) {
            model.addAttribute("error", "Значение скидки должно быть в пределах от 10 до 100");
            return "admin/change-group-discount";
        }

        List<Product> productsByManufacturer = productService.findProductsByProductManufacturerContains(manufacturer.toLowerCase());
        productsByManufacturer.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        model.addAttribute("updateSuccess", "Скидка успешно обновлена");
        model.addAttribute("types", productTypes);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("countries", countriesNames);
        return "admin/change-group-discount";
    }

    @RequestMapping(value = "/edit/country", method = RequestMethod.POST)
    public String editGroupDiscountCountry(@RequestParam("country") String country, @RequestParam("discount") Integer discount, Model model) {
        if (discount == null || discount < 10 || discount > 100) {
            model.addAttribute("error", "Значение скидки должно быть в пределах от 10 до 100");
            return "admin/change-group-discount";
        }

        List<Product> productsByCountry = productService.findProductsByProductCountryContains(country.toLowerCase());
        productsByCountry.forEach(product -> {
            product.setDiscount(discount);
            productService.update(Optional.of(product));
        });
        model.addAttribute("updateSuccess", "Скидка успешно обновлена");
        model.addAttribute("types", productTypes);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("countries", countriesNames);
        return "admin/change-group-discount";
    }
}
