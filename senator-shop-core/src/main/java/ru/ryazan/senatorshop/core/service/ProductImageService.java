package ru.ryazan.senatorshop.core.service;

import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.ProductImage;

import java.util.ArrayList;

public interface ProductImageService {
    ProductImage storeFile(MultipartFile file, Product product);
    ProductImage getFile(Long fileId);
    ArrayList<ProductImage> findProductImageByProduct(Product product);

    void deleteOldPhoto(ArrayList<ProductImage> image);
}
