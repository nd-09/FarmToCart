package com.user.farmtocart.services;

import com.user.farmtocart.models.Product;
import com.user.farmtocart.repositories.ProductRepository;
import com.user.farmtocart.repositories.SellerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class SellerService {
    private final SellerRepository sellerRepository;
    private final ProductRepository productRepository;

    public SellerService(SellerRepository sellerRepository, ProductRepository productRepository) {
        this.sellerRepository = sellerRepository;
        this.productRepository = productRepository;
    }

    public Product createProduct(Product product) {
        Product newProduct =productRepository.save(product);
    return newProduct;
    }

    public List<Product> getAllProductsBySellerId(Long sellerId){
        List<Product> products= productRepository.findAll()
                .stream()
                .filter((product)-> product.getSellerId().equals(sellerId))
                .collect(Collectors.toList());
        return products;
    }

    public Product updateProduct(Product product) {
        Optional<Product> newProduct=productRepository.findById(product.getId());
        if(newProduct.isPresent()){
            newProduct.get().setName(product.getName());
            newProduct.get().setPrice(product.getPrice());
            newProduct.get().setDescription(product.getDescription());
            newProduct.get().setQuantity(product.getQuantity());
            newProduct.get().setCategory(product.getCategory());

            return productRepository.save(newProduct.get());
        }else{
            throw new RuntimeException("Product not found");
        }
    }

    public String deleteProduct(Long productId) {
        Optional<Product> product=productRepository.findById(productId);
        if(product.isPresent()){
            productRepository.delete(product.get());
        }else{
            throw new RuntimeException("Product not found");
        }
        return "Product deleted successfully";
    }
}
