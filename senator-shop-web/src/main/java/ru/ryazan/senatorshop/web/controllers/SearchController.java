package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.pagination.PaginationNumbers;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SearchController {
    private ProductService productService;
    private PaginationNumbers paginationNumbers;

    public SearchController(ProductService productService, PaginationNumbers paginationNumbers) {
        this.productService = productService;
        this.paginationNumbers = paginationNumbers;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(name = "category", defaultValue = "all") String category,
                         @RequestParam(name = "description", required = false) String description,
                         @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("id").descending());
        Page<Product> products = new PageImpl<>(new ArrayList<>(), pageable, 0);

        if (category.contains("all") && description == null) {
            products = productService.findAll(pageable);
        } else if (category.contains("all") && description != null) {
            products = productService.findProductsByProductDescriptionContains(description, pageable);
        } else if (!category.contains("all") && (description == null || description.length() == 0)) {
            products = productService.findProductsByProductCategoryContains(category, pageable);
        } else if (!category.contains("all") && (description != null && description.length() > 0)) {
            products = productService.findProductsByProductCategoryContainsAndProductDescriptionContains(category, description, pageable);
        }

        int totalPages = products.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page, totalPages);
            model.addAttribute("pageNumbers", pageNumbersList);
        }

        model.addAttribute("orders", products);
        model.addAttribute("url", "/search");
        model.addAttribute("products", products);
        return "search-result";
    }
}
