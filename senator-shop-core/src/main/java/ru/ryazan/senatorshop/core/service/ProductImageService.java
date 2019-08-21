package ru.ryazan.senatorshop.core.service;

import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.ProductImage;

public interface ProductImageService {
    ProductImage storeFile(MultipartFile file);
    ProductImage getFile(Long fileId) ;
}
