package ru.gabdulindv.senatorshop.controllers.admin;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.model.product.ProductImage;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin")
public class AdminProductController {
    private ProductService productService;
    private List<Sort.Order> sortOrder;

    public AdminProductController(ProductService productService) {
        this.productService = productService;
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Order orderOutOfStock = new Sort.Order(Sort.Direction.ASC, "productDetails.isOutOfStock");
        Sort.Order orderId = new Sort.Order(Sort.Direction.DESC, "productId");
        orders.add(orderOutOfStock);
        orders.add(orderId);
        sortOrder = orders;
    }

    @RequestMapping("/products")
    public ResponseEntity getProducts(@RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortOrder));
        Page<Product> allProducts = productService.findAll(pageable);

        return ResponseEntity.ok(allProducts);
    }

    @RequestMapping("/product/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product.get());
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/product/search")
    public ResponseEntity getProduct(@RequestParam(name = "text") String description, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String textForFind = description.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDescriptionContainsOrProductNameContains(textForFind, textForFind, pageable);

        return ResponseEntity.ok(products);
    }

    @RequestMapping(value = "/product/create", method = RequestMethod.POST)
    public ResponseEntity createProduct(@RequestBody Product product) {
        if (product.getProductId() != null) {
            Optional<Product> oldProduct = productService.findById(product.getProductId());
            product.setReservedCartItems(oldProduct.get().getReservedCartItems());
            product.setCartItems(oldProduct.get().getCartItems());
        }

        product.getProductDetails().setOutOfStock(product.getProductDetails().getProductUnitInStock().equals("0"));
        productService.addProduct(product);
        return ResponseEntity.ok(product.getProductId());
    }

    @RequestMapping(value = "/product/update/image/{id}", method = RequestMethod.POST, consumes = {"multipart/form-data"})
    public ResponseEntity updateImage(@PathVariable("id") Long id, @RequestParam("file") CommonsMultipartFile file) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ProductImage productImage = new ProductImage();
            productImage.setFileType(file.getContentType());
            productImage.setFileName(file.getName());
            productImage.setFileData(file.getBytes());
            product.get().setProductImage(productImage);
            productService.addProduct(product.get());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @RequestMapping(value = "/product/delete/{id}", method = RequestMethod.POST)
    public ResponseEntity deleteProduct(@PathVariable("id") Long id) {
        productService.deleteById(id);
        return ResponseEntity.ok("Deleted");
    }
}
