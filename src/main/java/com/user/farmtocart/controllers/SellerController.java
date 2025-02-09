package com.user.farmtocart.controllers;

import com.user.farmtocart.models.Product;
import com.user.farmtocart.services.SellerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/seller")
public class SellerController {
    private final SellerService sellerService;

    public SellerController(SellerService sellerService) {
        this.sellerService = sellerService;
    }

   @PostMapping ("/product")
    public Product createProduct(@RequestBody Product product) {
        return sellerService.createProduct(product);
    }

    @GetMapping("/{id}")
    public List<Product> getAllProductsBySellerId(@PathVariable("id") Long sellerId) {
        return sellerService.getAllProductsBySellerId(sellerId);
    }
    @PutMapping("/product")
    public Product updateProduct(@RequestBody Product product) {
        return sellerService.updateProduct(product);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable("id") Long productId) {
        return sellerService.deleteProduct(productId);
    }
}
