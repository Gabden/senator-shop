package ru.gabdulindv.senatorshop.controllers.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import ru.gabdulindv.senatorshop.model.product.Product;
import ru.gabdulindv.senatorshop.model.product.ProductImage;
import ru.gabdulindv.senatorshop.service.ProductDetailsService;
import ru.gabdulindv.senatorshop.service.ProductService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;
    private ProductDetailsService productDetailsService;
    private List<Sort.Order> sortOrder;

    public ProductController(ProductService productService, ProductDetailsService productDetailsService) {
        this.productService = productService;
        this.productDetailsService = productDetailsService;
        List<Sort.Order> orders = new ArrayList<Sort.Order>();
        Sort.Order orderOutOfStock = new Sort.Order(Sort.Direction.ASC, "productDetails.isOutOfStock");
        Sort.Order orderId = new Sort.Order(Sort.Direction.DESC, "productId");
        orders.add(orderOutOfStock);
        orders.add(orderId);
        sortOrder = orders;
    }

    @RequestMapping("/{id}")
    public ResponseEntity getProduct(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            return ResponseEntity.ok(product);
        }
        return ResponseEntity.notFound().build();
    }

    @RequestMapping("/all/types")
    public ResponseEntity getAllTypes() {
        Set<String> allTypes = productDetailsService.findAllTypes();
        Set<String> allTypesSorted = allTypes.parallelStream().map(type -> {
            if (type != null && type.length() > 0) {
                String typeTrimmed = type.trim().toLowerCase();
                return typeTrimmed.substring(0, 1).toUpperCase() + typeTrimmed.substring(1);
            }
            return "Ром";
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(allTypesSorted.stream().sorted());
    }

    @RequestMapping("/all/manufacturers")
    public ResponseEntity getAllManufacturers() {
        Set<String> manufacturers = productDetailsService.findAllManufacturers();
        Set<String> sortedMan = manufacturers.parallelStream().map(manufacturer -> {
            if (manufacturer != null && manufacturer.length() > 0) {
                String manufacturerTrimmed = manufacturer.trim().toLowerCase();
                return manufacturerTrimmed.substring(0, 1).toUpperCase() + manufacturerTrimmed.substring(1);
            }
            return "Delamain";
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(sortedMan.stream().sorted());
    }

    @RequestMapping("/all/countries")
    public ResponseEntity getAllCountries() {
        Set<String> countries = productDetailsService.findAllCountries();
        Set<String> countriesSorted = countries.parallelStream().map(country -> {
            if (country != null && country.length() > 0) {
                String countryTrimmed = country.trim().toLowerCase();
                return countryTrimmed.substring(0, 1).toUpperCase() + countryTrimmed.substring(1);
            }
            return "Франция";
        }).collect(Collectors.toSet());
        return ResponseEntity.ok(countriesSorted.stream().sorted());
    }

    @RequestMapping("/search")
    public ResponseEntity getProduct(@RequestParam(name = "text") String description, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String textForFind = description.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 10, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDescriptionContainsOrProductNameContains(textForFind, textForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/category")
    public ResponseEntity getProductByCategory(@RequestParam(name = "name") String category, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String categoryForFind = category.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductCategoryContains(categoryForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/volume")
    public ResponseEntity getProductByVolume(@RequestParam(name = "value") String volume, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String volumeForFind = volume.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductVolumeContains(volumeForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/color")
    public ResponseEntity getProductByColor(@RequestParam(name = "value") String color, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String colorForFind = color.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductAlcoholColorContains(colorForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/region")
    public ResponseEntity getProductByRegion(@RequestParam(name = "value") String region, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String regionForFind = region.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductRegionContains(regionForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/degree")
    public ResponseEntity getProductByDegree(@RequestParam(name = "value") String degree, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String degreeForFind = degree.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductAlcoholDegreeContains(degreeForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/sort")
    public ResponseEntity getProductBySort(@RequestParam(name = "value") String sort, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String sortForFind = sort.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductAlcoholSortContains(sortForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/country")
    public ResponseEntity getProductByCountry(@RequestParam(name = "value") String country, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String countryForFind = country.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductCountryContains(countryForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/sugar")
    public ResponseEntity getProductBySugar(@RequestParam(name = "value") String sugar, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String sugarForFind = sugar.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductAlcoholSugarContains(sugarForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/manufacturer")
    public ResponseEntity getProductByManufacturer(@RequestParam(name = "value") String manufacturer, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String manufacturerForFind = manufacturer.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductManufacturerContains(manufacturerForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/temperature")
    public ResponseEntity getProductByTemperature(@RequestParam(name = "value") String temperature, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String temperatureForFind = temperature.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductAlcoholTemperatureContains(temperatureForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/search/mature")
    public ResponseEntity getProductByMature(@RequestParam(name = "value") String mature, @RequestParam(name = "page", defaultValue = "0") int page) {
        if (page < 0) {
            page = 0;
        }
        String matureForFind = mature.toLowerCase().trim();
        Pageable pageable = PageRequest.of(page, 8, Sort.by(sortOrder));
        Page<Product> products = productService.findProductsByProductDetails_ProductMatureContains(matureForFind, pageable);
        return ResponseEntity.ok(products);
    }

    @RequestMapping("/image/{id}")
    @ResponseBody
    public ResponseEntity<byte[]> productImage(@PathVariable Long id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            ProductImage image = product.get().getProductImage();
            if (image == null) {
                throw new RuntimeException("Image with id" + id + " doesn`t exist");
            }
            byte[] imageByteArray = image.getFileData();
            HttpHeaders httpHeaders = new HttpHeaders();
            CacheControl cc = CacheControl.noCache();
            cc.cachePublic();
            httpHeaders.setCacheControl(cc.getHeaderValue());
            String imageType = image.getFileType().split("/")[1];
            httpHeaders.setContentType(new MediaType("image", imageType));
            return new ResponseEntity<>(imageByteArray, httpHeaders, HttpStatus.OK);
        } else {
            throw new RuntimeException("Image with id" + id + " doesn`t exist");
        }
    }
}
