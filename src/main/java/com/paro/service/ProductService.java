package com.paro.service;


import com.paro.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductService {
    void updateAllStock();
    List<Product> getAllProducts();
    List<Product> getProductsByCategory(String category);
    List<Product> getProductsByFilter(Map<String, List<String>> filterParams);
    Product getProductById(String productID);
    List<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
    Product getProductByManufacturer(String manufacturer);
    void addProduct(Product product);

}
