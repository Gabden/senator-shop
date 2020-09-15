package ru.gabdulindv.senatorshop.controllers;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.model.sales.Banner;
import ru.gabdulindv.senatorshop.service.BannerService;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/home")
public class IndexController {
    private ProductService productService;
    private BannerService bannerService;

    public IndexController(ProductService productService, BannerService bannerService) {
        this.productService = productService;
        this.bannerService = bannerService;
    }

    @RequestMapping("/all/products")
    public ResponseEntity getStartPageNotifications(@RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 8, Sort.by("productId").descending());
        Page<Product> products = productService.findAll(pageable);

        return ResponseEntity.ok(products);
    }

    @RequestMapping("/banners/all")
    public ResponseEntity getAllBanners() {
        List<Banner> banners = bannerService.findAll();
        return ResponseEntity.ok(banners);
    }
}
