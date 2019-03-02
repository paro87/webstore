package com.paro.service.impl;

import com.paro.domain.Product;
import com.paro.domain.repository.ProductRepository;
import com.paro.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void updateAllStock() {
        List<Product> allProducts = productRepository.getAllProducts();
        for(Product product : allProducts) {
            if(product.getUnitsInStock()<500)
                productRepository.updateStock(product.getProductId(), product.getUnitsInStock()+1000);
        }
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();

    }

    @Override
    public List<Product> getProductsByCategory(String category) {
        return productRepository.getProductByCategory(category);
    }

    @Override
    public List<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
        /*
        for (Map.Entry<String, List<String>> entry : filterParams.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue().toString());
        }
        */
        return productRepository.getProductByFilter(filterParams);
    }

    @Override
    public Product getProductById(String productID) {
        return productRepository.getProductById(productID);
    }

    @Override
    public List<Product> getProductsByPriceFilter(Map<String, List<String>> filterParams) {
        return productRepository.getProductsByPriceFilter(filterParams);
    }

    @Override
    public Product getProductByManufacturer(String manufacturer) {
        return productRepository.getProductByManufacturer(manufacturer);
    }

    @Override
    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }


}
