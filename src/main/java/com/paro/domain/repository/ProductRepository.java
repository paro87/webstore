package com.paro.domain.repository;

import com.paro.domain.Product;

import java.util.List;
import java.util.Map;

public interface ProductRepository {
    List <Product> getAllProducts();
    void updateStock(String productId, long noOfUnits);
    List<Product> getProductByCategory(String category);
    List<Product> getProductByFilter(Map<String, List<String>> filterParams);
    Product getProductById(String productID);
    List<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams);
    Product getProductByManufacturer(String manufacturer);
    void addProduct(Product product);

}
