package com.Products.Repository;

import com.Products.Model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository <Customer, Long> {


    public Customer findByCPF(String CPF);
}
