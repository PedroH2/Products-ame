package com.Products.Utils;

import com.Products.Model.Product;
import com.Products.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyData {

    @Autowired
    ProductRepository productRepository;

   // @PostConstruct
    public void postProducts(){
        List<Product> products = new ArrayList<>();
        Product pd1 = new Product();
        pd1.setPostdate(LocalDate.now());
        pd1.setProductDescription("Mouse gamer com 8000dpi, botoes laterais e rbg");
        pd1.setProductName("Mouse gamer");
        pd1.setSeller("Tokstok");
        pd1.setTags("Tecnologia, games");
        pd1.setValue("100.00");

        products.add(pd1);

        for(Product product: products){
            Product productSaved = productRepository.save(product);
            System.out.println(productSaved.getId());
        }
    }
}
