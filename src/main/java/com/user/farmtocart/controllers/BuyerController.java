package com.user.farmtocart.controllers;

import com.user.farmtocart.models.Product;
import com.user.farmtocart.services.BuyerService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/buyer")
public class BuyerController {
    private final BuyerService buyerService;
    public BuyerController(BuyerService buyerService) {
        this.buyerService = buyerService;
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        // gets all products for buyer
        return buyerService.getAllProducts();
    }
    @GetMapping("/{id}")
    public Product getProductById(@PathVariable("id") Long id) {
        return buyerService.getProductById(id);
    }

}
