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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

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
        List<Product> filteredList;
        List<Product> filteredListCustom;
        filteredListCustom = productService.findAllList();

        int start = (int) pageable.getOffset();


        if (category.contains("all")) {
            if (description != null && description.length() > 0) {
                filteredList = filteredListCustom.stream().filter(product ->
                        (product.getProductDescription().toLowerCase().contains(description.toLowerCase()))
                                || (product.getProductName().toLowerCase().contains(description.toLowerCase()))).collect(Collectors.toList());

                int end = (int) (start + pageable.getPageSize()) > filteredList.size() ? filteredList.size() : (start + pageable.getPageSize());
                products = new PageImpl<>(filteredList.subList(start, end), pageable, filteredList.size());
            } else {
                int end = (int) (start + pageable.getPageSize()) > filteredListCustom.size() ? filteredListCustom.size() : (start + pageable.getPageSize());
                products = new PageImpl<>(filteredListCustom.subList(start, end), pageable, filteredListCustom.size());
            }

        } else {

            if (description != null) {
                filteredList = filteredListCustom.stream().filter(product -> product.getProductCategory().toLowerCase().contains(category) && (
                        (product.getProductDescription().toLowerCase().contains(description.toLowerCase()))
                                || (product.getProductName().toLowerCase().contains(description.toLowerCase())))).collect(Collectors.toList());
            } else {
                filteredList = filteredListCustom.stream().filter(product -> product.getProductCategory().toLowerCase().contains(category)).collect(Collectors.toList());
            }
            int end = (int) (start + pageable.getPageSize()) > filteredList.size() ? filteredList.size() : (start + pageable.getPageSize());

            products = new PageImpl<>(filteredList.subList(start, end), pageable, filteredList.size());
        }


        int totalPages = products.getTotalPages();


        if (totalPages > 0) {
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page,totalPages);
            model.addAttribute("pageNumbers", pageNumbersList);
        }

        model.addAttribute("orders", products);
        model.addAttribute("url", "/search");
        model.addAttribute("products", products);
        return "search-result";
    }
}
