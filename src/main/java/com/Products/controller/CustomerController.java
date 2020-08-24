package com.Products.controller;

import com.Products.Model.Customer;
import com.Products.Model.Product;
import com.Products.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;

@Controller
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @RequestMapping(value="/signup", method = RequestMethod.GET)
    public String getProductForm() { return "customerForm"; }

    @RequestMapping(value="/signup", method = RequestMethod.POST)
    public String saveProduct(@Valid Customer customer, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return  "redirect:/newproduct";
        }
        customerService.save(customer);
        return "redirect:/products";
    }
}
