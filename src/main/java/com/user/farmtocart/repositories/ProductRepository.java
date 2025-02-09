package com.user.farmtocart.repositories;

import com.user.farmtocart.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
