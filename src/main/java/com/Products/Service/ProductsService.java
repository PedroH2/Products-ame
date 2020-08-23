package com.Products.Service;

import com.Products.Model.Product;

import java.util.List;

public interface ProductsService {
    List<Product> findAll();
    Product findById(long id);
    Product save(Product product);
}
