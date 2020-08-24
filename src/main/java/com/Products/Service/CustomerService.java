package com.Products.Service;

import com.Products.Model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> findAll();
    Customer findById(Long id);
    Customer save (Customer customer);
    Customer findByCPF(String CPF);
}
