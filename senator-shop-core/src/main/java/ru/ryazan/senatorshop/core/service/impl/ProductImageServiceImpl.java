package ru.ryazan.senatorshop.core.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import ru.ryazan.senatorshop.core.domain.Product;
import ru.ryazan.senatorshop.core.domain.ProductImage;
import ru.ryazan.senatorshop.core.exception.FileStorageException;
import ru.ryazan.senatorshop.core.exception.MyFileNotFoundException;
import ru.ryazan.senatorshop.core.repository.ProductImageRepository;
import ru.ryazan.senatorshop.core.service.ProductImageService;

import java.io.IOException;

@Service
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageRepository dbFileRepository;

    @Override
    public ProductImage storeFile(MultipartFile file, Product product) throws FileStorageException {
        //Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }

            ProductImage dbFile = new ProductImage(fileName, file.getContentType(), file.getBytes(), product);

            return dbFileRepository.save(dbFile);
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    @Override
    public ProductImage getFile(Long fileId) throws MyFileNotFoundException {
        return dbFileRepository.findById(fileId)
                .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
    }

    @Override
    public ProductImage findProductImageByProduct(Product product) {
        return dbFileRepository.findProductImageByProduct(product);
    }
}