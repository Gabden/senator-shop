package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.service.ProductService;
import ru.ryazan.senatorshop.web.pagination.PaginationNumbers;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

@Controller
public class SearchController {
    private ProductService productService;
    private PaginationNumbers paginationNumbers;
    private Set<String> countriesNames;
    private Set<String> manufacturersNames;
    private List<Product> all;

    public SearchController(ProductService productService, PaginationNumbers paginationNumbers) {
        this.productService = productService;
        this.paginationNumbers = paginationNumbers;
        countriesNames = new HashSet<>();
        manufacturersNames = new HashSet<>();
        all = new ArrayList<>();
    }

    @PostConstruct
    public void init() {
        all = productService.findAll();
        all.forEach(product -> {
            if (product.getProductCountry() != null) {
                countriesNames.add(product.getProductCountry());
            }
            if (product.getProductManufacturer() != null) {
                manufacturersNames.add(product.getProductManufacturer());
            }
        });
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

        model.addAttribute("countries", countriesNames);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("orders", products);
        model.addAttribute("url", "/search");
        model.addAttribute("products", products);
        return "search-result";
    }

    @RequestMapping("/filter")
    public String filter(@RequestParam(name = "page", defaultValue = "0") int page,
                         @RequestParam(name = "categories", required = false) String[] categories,
                         @RequestParam(name = "countries", required = false) String[] countries,
                         @RequestParam(name = "manufacturers", required = false) String[] manufacturers,
                         @RequestParam(name = "min-price", required = false) String minPrice,
                         @RequestParam(name = "max-price", required = false) String maxPrice,
                         HttpServletRequest request,
                         Model model) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("id").descending());
        Page<Product> products = productService.findAll(pageable);

        int start = (int) pageable.getOffset();

        List<Product> filterProducts = new ArrayList<>();

        try {
            if (categories != null && categories.length > 0 && !categories[0].equals("all")) {
                filterProducts = all.stream().filter(product -> Arrays.stream(categories).parallel().anyMatch(category -> {
                    if (product.getProductCategory() != null) {
                        return product.getProductCategory().contains(category);
                    } else {
                        return false;
                    }
                })).collect(Collectors.toList());

            } else {
                filterProducts.addAll(all);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (countries != null && countries.length > 0) {
                filterProducts = filterProducts.stream().filter(product -> Arrays.stream(countries).parallel().anyMatch(country -> {
                    if (product.getProductCountry() != null) {
                        return product.getProductCountry().contains(country);
                    } else {
                        return false;
                    }
                })).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            if (manufacturers != null && manufacturers.length > 0) {
                filterProducts = filterProducts.stream().filter(product -> Arrays.stream(manufacturers).parallel().anyMatch(manufacture -> product.getProductManufacturer().contains(manufacture))).collect(Collectors.toList());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {
            boolean isMaxAndMinNull = minPrice != null && minPrice.length() > 0 && maxPrice != null && maxPrice.length() > 0;
            boolean isMinExist = minPrice != null && minPrice.length() > 0;
            boolean isMaxExist = maxPrice != null && maxPrice.length() > 0;

            if (isMinExist && isMaxExist) {
                int min = Integer.parseInt(minPrice);
                int max = Integer.parseInt(maxPrice);
                filterProducts = filterProducts.stream().filter(product -> {
                    int price = Integer.parseInt(product.getProductPrice());
                    return price >= min && price <= max;
                }).collect(Collectors.toList());
            }
            if (!isMinExist && isMaxExist) {
                int max = Integer.parseInt(maxPrice);
                filterProducts = filterProducts.stream().filter(product -> {
                    int price = Integer.parseInt(product.getProductPrice());
                    return price <= max;
                }).collect(Collectors.toList());
            }
            if (isMinExist && !isMaxExist) {
                int min = Integer.parseInt(minPrice);
                filterProducts = filterProducts.stream().filter(product -> {
                    int price = Integer.parseInt(product.getProductPrice());
                    return price >= min;
                }).collect(Collectors.toList());
            }
        } catch (Exception e) {
            System.out.println("ошибка преобразования");
            e.printStackTrace();
        }

        if (filterProducts.size() > 0) {
            int end = (int) (start + pageable.getPageSize()) > filterProducts.size() ? filterProducts.size() : (start + pageable.getPageSize());
            products = new PageImpl<>(filterProducts.subList(start, end), pageable, filterProducts.size());
        }


        int totalPages = products.getTotalPages();


        if (totalPages > 0) {
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page, totalPages);
            model.addAttribute("pageNumbers", pageNumbersList);
        }
        String queries = request.getQueryString();
        if (queries.contains("page=")) {
            queries = queries.replaceAll("page=", "oldpage=");
        }
        String completeUrl = request.getRequestURI() + "?" + queries;
        model.addAttribute("countries", countriesNames);
        model.addAttribute("manufacturers", manufacturersNames);
        model.addAttribute("orders", products);
        model.addAttribute("url", completeUrl);
        model.addAttribute("products", products);
        return "search-result";
    }
}
