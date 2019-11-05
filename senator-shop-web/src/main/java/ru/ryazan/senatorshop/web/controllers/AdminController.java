package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.Customer;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.ProductImage;
import ru.ryazan.senatorshop.core.service.CustomerService;
import ru.ryazan.senatorshop.core.service.ProductImageService;
import ru.ryazan.senatorshop.core.service.ProductService;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private ProductService productService;
    private ProductImageService DBFileStorageService;
    private CustomerService customerService;

    public AdminController(ProductService productService, ProductImageService DBFileStorageService, CustomerService customerService) {
        this.productService = productService;
        this.DBFileStorageService = DBFileStorageService;
        this.customerService = customerService;
    }
    @RequestMapping({"","/"})
    public String admin(Model model){
        return "admin";
    }
    @RequestMapping("/productInventory")
    public String productInventory(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page, 7, Sort.by("id").descending());
        Page<Product> allProducts = productService.findAll(pageable);
        int totalPages = allProducts.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("products", allProducts);
        model.addAttribute("orders", allProducts);
        model.addAttribute("url", "/admin/productInventory");
        return "product-inventory";
    }

    @RequestMapping("/searchById")
    public String productInventory(@RequestParam(name = "id", required = false)long id,@RequestParam(name = "description", required = false)String description,
                                   @RequestParam(name = "page", defaultValue = "0")int page,Model model) {
        if (id > 0) {
            Optional<Product> product = productService.findById(id);
            if (product.isPresent()) {
                model.addAttribute("products", Collections.singletonList(product.get()));
                return "product-inventory";
            }
        } else {
            model.addAttribute("products", new ArrayList<>());
            model.addAttribute("msg", "По вашему запросу ничего не найдено");
            return "product-inventory";
        }
        return "product-inventory";
    }

    @RequestMapping("/searchByDescription")
    public String search(@RequestParam(name = "category", defaultValue = "all") String category,
                         @RequestParam(name = "description", required = false) String description,
                         @RequestParam(name = "page", defaultValue = "0")int page,Model model){
        Pageable pageable = PageRequest.of(page, 7, Sort.by("id").descending());
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
        return "product-inventory";
    }

    @RequestMapping("/productInventory/addProduct")
    public String adminAdd(Model model){
        Product product = new Product();
        ProductImage image = new ProductImage();
        model.addAttribute("product", product);
        model.addAttribute("image", image);
        return "addProduct";
    }

    @RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
    public String productInventoryAdd(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file,HttpServletRequest request){
        System.out.println(product.getProductName());
        productService.addProduct(product);
        ProductImage dbFile = DBFileStorageService.storeFile(file, product);

        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value = "/productInventory/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, Model model){
        Optional<Product> product = productService.findById(id);
        product.ifPresent(model::addAttribute);
        return "edit-product";
    }

    @RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("product")Product product, Model model, @RequestParam("file") MultipartFile file,
             @PathVariable("id") Long id,
             HttpServletRequest request) {

        Optional<Product> productFromDB = Optional.of(product);
        productFromDB.ifPresent(productNew ->productNew.setId(id));
        ArrayList<ProductImage> image = DBFileStorageService.findProductImageByProduct(product);
        if (!file.isEmpty()) {
            DBFileStorageService.deleteOldPhoto(image);
            DBFileStorageService.storeFile(file, product);
        }

        productService.update(productFromDB);
        return "redirect:/productList/product/" + id;
    }

    @RequestMapping(value = "/productInventory/deleteProduct/{id}")
    public String productInventoryDelete(@PathVariable Long id){
        productService.deleteById(id);
        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/customers")
    public String customerManagement(Model model){
        List<Customer> customers = customerService.getAllCustomers();
        model.addAttribute("customers", customers);
        return "customerManagement";
    }




}
