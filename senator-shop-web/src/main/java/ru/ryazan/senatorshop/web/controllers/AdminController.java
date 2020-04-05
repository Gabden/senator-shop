package ru.ryazan.senatorshop.web.controllers;

import org.springframework.data.domain.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.*;
import ru.ryazan.senatorshop.core.service.*;
import ru.ryazan.senatorshop.web.pagination.PaginationNumbers;

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
    private CustomerOrderService customerOrderService;
    private PasswordEncoder passwordEncoder;
    private UserService userService;
    private PaginationNumbers paginationNumbers;

    public AdminController(ProductService productService, ProductImageService DBFileStorageService
            , PasswordEncoder passwordEncoder, CustomerService customerService,
                           UserService userService, CustomerOrderService customerOrderService
            , PaginationNumbers paginationNumbers) {
        this.productService = productService;
        this.DBFileStorageService = DBFileStorageService;
        this.customerService = customerService;
        this.passwordEncoder = passwordEncoder;
        this.customerOrderService = customerOrderService;
        this.userService = userService;
        this.paginationNumbers = paginationNumbers;
    }

    @RequestMapping("/delete/all")
    public String deleteAll() {
        List<Customer> customers = customerService.findAll();
        customers.forEach(customer -> customerService.deleteById(customer.getCustomerId()));
        List<User> users = userService.findAll();
        users.forEach(user -> userService.deleteById(user.getId()));
        return "redirect:/";
    }

    @RequestMapping({"", "/"})
    public String admin(Model model) {
        return "admin";
    }

    @RequestMapping("/productInventory")
    public String productInventory(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 8, Sort.by("id").descending());
        Page<Product> allProducts = productService.findAll(pageable);
        int totalPages = allProducts.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page, totalPages);

            model.addAttribute("pageNumbers", pageNumbersList);
        }
        model.addAttribute("products", allProducts);
        model.addAttribute("orders", allProducts);
        model.addAttribute("url", "/admin/productInventory");
        return "product-inventory";
    }

    @RequestMapping("/searchById")
    public String productInventory(@RequestParam(name = "id", required = false) long id, @RequestParam(name = "description", required = false) String description,
                                   @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        if (id > 0) {
            Optional<Product> product = productService.findById(id);
            if (product.isPresent()) {
                model.addAttribute("products", Collections.singletonList(product.get()));
                model.addAttribute("url", "/admin/searchById");
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
                         @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by("id").descending());
        Page<Product> products = new PageImpl<>(new ArrayList<>(), pageable, 0);
        if (category.contains("all") && description == null) {
            products = productService.findAll(pageable);
        } else if (category.contains("all") && description != null) {
            products = productService.findProductsByProductDescriptionContainsOrProductNameContains(description, description, pageable);
        } else if (!category.contains("all") && (description == null || description.length() == 0)) {
            products = productService.findProductsByProductCategoryContains(category, pageable);
        } else if (!category.contains("all") && (description != null && description.length() > 0)) {
            products = productService.findProductsByProductCategoryContainsAndProductDescriptionContainsOrProductNameContains(category, description, description, pageable);
        }

        int totalPages = products.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbersList = paginationNumbers.getPaginationListNumbers(page, totalPages);
            model.addAttribute("pageNumbers", pageNumbersList);
        }

        model.addAttribute("orders", products);
        model.addAttribute("url", "/admin/searchByDescription");
        model.addAttribute("products", products);
        return "product-inventory";
    }

    @RequestMapping("/productInventory/addProduct")
    public String adminAdd(Model model) {
        Product product = new Product();
        ProductImage image = new ProductImage();
        model.addAttribute("product", product);
        model.addAttribute("image", image);
        return "addProduct";
    }

    @RequestMapping(value = "/productInventory/addProduct", method = RequestMethod.POST)
    public String productInventoryAdd(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file, HttpServletRequest request) {
        System.out.println(product.getProductName());
        if (product.getProductCategory() == null) {
            product.setProductCategory("alcohol");
        }
        productService.addProduct(product);
        ProductImage dbFile = DBFileStorageService.storeFile(file, product);

        return "redirect:/admin/productInventory";
    }

    @RequestMapping(value = "/productInventory/updateProduct/{id}")
    public String updateProduct(@PathVariable Long id, Model model) {
        Optional<Product> product = productService.findById(id);
        product.ifPresent(model::addAttribute);
        return "edit-product";
    }

    @RequestMapping(value = "/productInventory/updateProduct/{id}", method = RequestMethod.POST)
    public String update(@ModelAttribute("product") Product product, Model model, @RequestParam("file") MultipartFile file,
                         @PathVariable("id") Long id,
                         HttpServletRequest request) {

        Optional<Product> productFromDB = Optional.of(product);
        productFromDB.ifPresent(productNew -> {
            if (productNew.getProductCategory() == null) {
                productNew.setProductCategory("alcohol");
            }
            productNew.setId(id);
        });
        ArrayList<ProductImage> image = DBFileStorageService.findProductImageByProduct(product);
        if (!file.isEmpty()) {
            DBFileStorageService.deleteOldPhoto(image);
            DBFileStorageService.storeFile(file, product);
        }

        productService.update(productFromDB);
        return "redirect:/productList/product/" + id;
    }

    @RequestMapping(value = "/productInventory/deleteProduct/{id}")
    public String productInventoryDelete(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/admin/productInventory";
    }

    @RequestMapping("/customers")
    public String customerManagement(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by("customerId").descending());
        Page<Customer> customers = customerService.getAllCustomers(pageable);

        int totalPages = customers.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("customers", customers);
        model.addAttribute("orders", customers);
        model.addAttribute("url", "/admin/customers");

        return "customerManagement";
    }

    @RequestMapping("/searchUserById")
    public String searchUser(@RequestParam(name = "id", required = true) long id, @RequestParam(name = "description", required = false) String description,
                             @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        if (id > 0) {
            Optional<Customer> customer = customerService.getCustomerById(id);
            if (customer.isPresent()) {
                model.addAttribute("customers", Collections.singletonList(customer.get()));
                model.addAttribute("url", "/admin/searchUserById");
                return "customerManagement";
            }
        } else {
            model.addAttribute("customers", new ArrayList<>());
            model.addAttribute("msg", "По вашему запросу ничего не найдено");
            return "customerManagement";
        }
        return "customerManagement";
    }


    @RequestMapping("/searchUserByPhone")
    public String searchUser(@RequestParam(name = "phone", required = true) String phone,
                             @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by("customerId").descending());
        List<Customer> filteredList = new ArrayList<>();
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        customers.stream().filter(customer -> customer.getCustomerPhone().contains(phone)).forEach(filteredList::add);
        customers = new PageImpl<>(filteredList, pageable, filteredList.size());
        int totalPages = customers.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("orders", customers);
        model.addAttribute("url", "/admin/searchUserByPhone");
        model.addAttribute("customers", customers);
        return "customerManagement";
    }

    @RequestMapping("/searchUserByMail")
    public String searchUserByMail(@RequestParam(name = "email", required = true) String email,
                                   @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by("customerId").descending());
        List<Customer> filteredList = new ArrayList<>();
        Page<Customer> customers = customerService.getAllCustomers(pageable);
        customers.stream().filter(customer -> customer.getCustomerName().contains(email)).forEach(filteredList::add);
        customers = new PageImpl<>(filteredList, pageable, filteredList.size());
        int totalPages = customers.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("orders", customers);
        model.addAttribute("url", "/admin/searchUserByMail");
        model.addAttribute("customers", customers);
        return "customerManagement";
    }


    // USER PROFILE EDIT
    @RequestMapping("/user-profile/{id}")
    public String profile(@PathVariable(name = "id") long id, Model model, @RequestParam(name = "page") int page) {

        Optional<Customer> customer = customerService.getCustomerById(id);

        Pageable firstPageWithTwoElements = PageRequest.of(page, 5, Sort.by("customerOrderId").descending());
        Page<CustomerOrder> orderByCustomer = customerOrderService.findAllByCustomer(customer.get(), firstPageWithTwoElements);

        int totalPages = orderByCustomer.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        model.addAttribute("customer", customer.get());
        model.addAttribute("orders", orderByCustomer);
        model.addAttribute("url", "/admin/user-profile");
        return "admin/admin-customer-profile-edit";

    }

    @RequestMapping(value = "/updateProfile", method = RequestMethod.POST)
    public String updateProfile(@ModelAttribute(name = "customer") Customer customer) {
        Optional<Customer> oldCustomer = customerService.getCustomerById(customer.getCustomerId());
        if (oldCustomer.isPresent()) {
            oldCustomer.get().setCustomerName(customer.getCustomerName());
            oldCustomer.get().setCustomerPhone(customer.getCustomerPhone());
            oldCustomer.get().setFIOfirst(customer.getFIOfirst());
            oldCustomer.get().setFIOlast(customer.getFIOlast());
            oldCustomer.get().setFIOmiddle(customer.getFIOmiddle());
            oldCustomer.get().setCustomerPassword(passwordEncoder.encode(customer.getCustomerPassword()));
            oldCustomer.get().setCustomerPasswordAccept(passwordEncoder.encode(customer.getCustomerPasswordAccept()));
            if (!customer.getCustomerName().equals(oldCustomer.get().getCustomerName())) {
                User user = userService.findUserByUsername(oldCustomer.get().getCustomerName());
                user.setUsername(customer.getCustomerName());
                userService.save(user);
            }
            customerService.addCustomer(oldCustomer.get());
        }
        return "redirect:/admin/customers";
    }

    // USER PROFILE EDIT

    // ORDER EDIT
    @RequestMapping("/customerOrder")
    public String customerOrdersManagement(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        Pageable pageable = PageRequest.of(page, 7, Sort.by("customerOrderId").descending());
        Page<CustomerOrder> customerOrders = customerOrderService.findAll(pageable);

        int totalPages = customerOrders.getTotalPages();

        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("customerOrders", customerOrders);
        model.addAttribute("orders", customerOrders);
        model.addAttribute("url", "/admin/customerOrder");

        return "admin/admin-orders";
    }

    @RequestMapping(value = "/customerOrder", method = RequestMethod.POST)
    public String customerOrder(@RequestParam(name = "id") long id, Model model) {

        if (id > 0) {
            Optional<CustomerOrder> customerOrder = customerOrderService.findById(id);
            if (customerOrder.isPresent()) {
                model.addAttribute("customerOrders", Collections.singletonList(customerOrder.get()));
                model.addAttribute("url", "/admin/customerOrder");
                return "admin/admin-orders";
            }
        } else {
            model.addAttribute("products", new ArrayList<>());
            model.addAttribute("msg", "По вашему запросу ничего не найдено");
            return "admin/admin-orders";
        }
        return "admin/admin-orders";

    }


    // ORDER EDIT


}
