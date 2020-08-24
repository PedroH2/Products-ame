package com.Products.Utils;

import com.Products.Model.Product;
import com.Products.Model.Customer;
import com.Products.Repository.CustomerRepository;
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
    @Autowired
    CustomerRepository customerRepository;

   //@PostConstruct
    public void postProducts(){
        List<Product> products = new ArrayList<>();
        Product pd1 = new Product();
        pd1.setPostdate(LocalDate.now());
        pd1.setProductDescription("A escolha espontânea dos artistas e celebridades, a Ray-Ban® se tornou um verdadeiro fenômeno no mundo. É a marca preferida por lendas, amada pelas pessoas e imitada por todos. Com mais de 70 anos de autenticidade, tradição e qualid...");
        pd1.setProductName("Óculos de Sol Ray-Ban Hexagonal Verde G-15 /Dourado 51-21");
        pd1.setSeller("Rayban");
        pd1.setTags("Utilitarios");
        pd1.setValue("570,19");
        pd1.setUrlPhoto("https://images-americanas.b2w.io/produtos/01/00/oferta/25253/4/25253436_1SZ.jpg");

        products.add(pd1);

        for(Product product: products){
            Product productSaved = productRepository.save(product);
            System.out.println(productSaved.getId());
        }
    }
    //@PostConstruct
    public void postCustomer(){
       List<Customer> customer = new ArrayList<>();
       Customer cust = new Customer();
       cust.setCPF("123");
       cust.setName("teste");
       cust.setTags("Telefone");
       cust.setPassword("123");

       customer.add(cust);
       for(Customer ct1: customer){
           Customer customerSave = customerRepository.save(cust);
       }
       //Customer saveCustomer = customerRepository.save(cust);

    }
}
