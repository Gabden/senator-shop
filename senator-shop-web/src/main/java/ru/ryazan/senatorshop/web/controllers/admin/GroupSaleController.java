package ru.ryazan.senatorshop.web.controllers.admin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.service.ProductService;

@Controller
@RequestMapping("/admin/group-discount")
public class GroupSaleController {

    private ProductService productService;

    public GroupSaleController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/edit")
    public String editGroupDiscount() {
        return "admin/change-group-discount";
    }

    @RequestMapping(value = "/edit/category", method = RequestMethod.POST)
    public String editGroupDiscount(@RequestParam("category") String category, @RequestParam("discount") Integer discount, Model model) {
        if (discount == null || discount < 10 || discount > 100) {
            model.addAttribute("error", "Значение скидки должно быть в пределах от 10 до 100");
            return "admin/change-group-discount";
        }

        return "redirect:/admin/searchByDescription?description=&category=" + category;
    }
}
