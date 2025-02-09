package com.user.farmtocart.services;

import com.user.farmtocart.models.Product;
import com.user.farmtocart.repositories.BuyerRepository;
import com.user.farmtocart.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BuyerService {
    private final BuyerRepository buyerRepository;
    private final ProductRepository productRepository;

    public BuyerService(BuyerRepository buyerRepository, ProductRepository productRepository) {
        this.buyerRepository = buyerRepository;
        this.productRepository = productRepository;
    }

    // this service returns all the products
    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        productRepository.findAll().forEach(product -> {

            products.add(product);
        });
        return products;
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow();
    }
}
