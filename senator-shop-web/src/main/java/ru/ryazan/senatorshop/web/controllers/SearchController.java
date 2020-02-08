package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.pagination.PaginationNumbers;

import java.util.*;
import java.util.stream.Collectors;

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

        Set<String> countries = new HashSet<>();
        Set<String> manufacturers = new HashSet<>();

        int totalPages = products.getTotalPages();


        if (totalPages > 0) {
            products.forEach(product -> {
                if (product.getProductCountry() != null) {
                    countries.add(product.getProductCountry());
                }
            });
            products.forEach(product -> {
                if (product.getProductManufacturer() != null) {
                    manufacturers.add(product.getProductManufacturer());
                }
            });
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page, totalPages);
            model.addAttribute("pageNumbers", pageNumbersList);
        }
        model.addAttribute("countries", countries);
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("orders", products);
        model.addAttribute("url", "/search");
        model.addAttribute("products", products);
        return "search-result";
    }

    @RequestMapping("/filter")
    public String filter(@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "category", required = false) String[] categories,
                         @RequestParam(name = "country", required = false) String[] countries,
                         @RequestParam(name = "manufacturer", required = false) String[] manufacturers,
                         @RequestParam(name = "min-price", required = false) String minPrice,
                         @RequestParam(name = "max-price", required = false) String maxPrice,
                         Model model) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("id").descending());
        Page<Product> products = productService.findAll(pageable);
        List<Product> filterProducts = new ArrayList<>();

        if (categories.length > 0 && !categories[0].equals("all")) {
            filterProducts = products.stream().filter(product -> Arrays.stream(categories).parallel().anyMatch(category -> product.getProductCategory().contains(category))).collect(Collectors.toList());

        } else {
            filterProducts.addAll(products.getContent());
        }

        if (countries != null && countries.length > 0) {
            filterProducts = filterProducts.stream().filter(product -> Arrays.stream(countries).parallel().anyMatch(country -> product.getProductCountry().contains(country))).collect(Collectors.toList());
        }

        if (manufacturers != null && manufacturers.length > 0) {
            filterProducts = filterProducts.stream().filter(product -> Arrays.stream(manufacturers).parallel().anyMatch(manufacture -> product.getProductManufacturer().contains(manufacture))).collect(Collectors.toList());
        }

        try {
            if (minPrice != null && minPrice.length() > 0 && maxPrice != null && maxPrice.length() > 0) {
                int min = Integer.parseInt(minPrice);
                int max = Integer.parseInt(maxPrice);

                filterProducts = filterProducts.stream().filter(product -> {
                    int price = Integer.parseInt(product.getProductPrice());
                    return price >= min && price <= max;
                }).collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.out.println("ошибка преобразования");
        }

        if (filterProducts.size() > 0) {
            products = new PageImpl<>(filterProducts, pageable, filterProducts.size());
        }

        Set<String> countriesNames = new HashSet<>();
        Set<String> manufacturersNames = new HashSet<>();

        int totalPages = products.getTotalPages();


        if (totalPages > 0) {
            products.forEach(product -> {
                if (product.getProductCountry() != null) {
                    countriesNames.add(product.getProductCountry());
                }
            });
            products.forEach(product -> {
                if (product.getProductManufacturer() != null) {
                    manufacturersNames.add(product.getProductManufacturer());
                }
            });
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page, totalPages);
            model.addAttribute("pageNumbers", pageNumbersList);
        }
        model.addAttribute("countries", countriesNames);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("orders", products);
        model.addAttribute("url", "/search");
        model.addAttribute("products", products);
        return "search-result";
    }
}
