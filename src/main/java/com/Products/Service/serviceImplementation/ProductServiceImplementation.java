package com.Products.Service.serviceImplementation;


import com.Products.Model.Product;
import com.Products.Repository.ProductRepository;
import com.Products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImplementation implements ProductsService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }
    @Override
    public Product findById(long id){
        return productRepository.findById(id).get();
    }
    @Override
    public Product save(Product product){
        return productRepository.save(product);
    }
}
