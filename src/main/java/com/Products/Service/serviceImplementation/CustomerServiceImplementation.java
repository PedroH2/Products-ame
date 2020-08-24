package com.Products.Service.serviceImplementation;

import com.Products.Model.Customer;
import com.Products.Repository.CustomerRepository;
import com.Products.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Override
    public List<Customer> findAll(){return customerRepository.findAll();}
    @Override
    public Customer findById(Long id){ return customerRepository.findById(id).get();}
    @Override
    public Customer save(Customer customer){ return customerRepository.save(customer); }
    @Override
    public Customer findByCPF(String CPF){ return customerRepository.findByCPF(CPF);}
}
