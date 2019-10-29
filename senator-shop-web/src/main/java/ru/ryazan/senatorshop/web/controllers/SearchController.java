package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class SearchController {
    private ProductService productService;

    public SearchController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/search")
    public String search(@RequestParam(name = "category", defaultValue = "all") String category,
                         @RequestParam(name = "description", required = false) String description,
                         @RequestParam(name = "page", defaultValue = "0")int page,Model model){
        Pageable pageable = PageRequest.of(page, 8, Sort.by("id").descending());
        Page<Product> products;
        List<Product> filteredList;
        if (category.equals("all")){
           products = productService.findAll(pageable);
        } else {
            products = productService.findByproductCategory(category,pageable);
        }


        if(description != null){
            filteredList = products.stream().filter(product ->
                    (product.getProductDescription().toLowerCase().contains(description.toLowerCase()))
                            || (product.getProductName().toLowerCase().contains(description.toLowerCase()))) .collect(Collectors.toList());
            products = new PageImpl<>(filteredList, pageable, filteredList.size());
        }

        int totalPages = products.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("orders", products);
        model.addAttribute("url", "/search");
        model.addAttribute("products", products);
        return "search-result";
    }
}
