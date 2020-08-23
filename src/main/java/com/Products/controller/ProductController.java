package com.Products.controller;

import com.Products.Model.Product;
import com.Products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    ProductsService productsService;

    @RequestMapping (value="products/{id}", method = RequestMethod.GET)
    public ModelAndView getProductDetails(@PathVariable("id") long id){
        
        ModelAndView mv = new ModelAndView("productDetail");
        Product product = productsService.findById(id);

        mv.addObject("product", product);
         return  mv;
    }
    @RequestMapping(value="/newproduct", method = RequestMethod.GET)
    public String getProductForm() { return "productForm"; }

    @RequestMapping(value="/newproduct", method = RequestMethod.POST)
    public String saveProduct(@Valid Product product, BindingResult result, RedirectAttributes attributes){
        if(result.hasErrors()){
            attributes.addFlashAttribute("mensagem", "Verifique se os campos obrigatórios foram preenchidos!");
            return  "redirect:/newproduct";
        }
        product.setPostdate(LocalDate.now());
        productsService.save(product);
        return "redirect:/products";
    }

    @RequestMapping(value="/products", method = RequestMethod.GET)
    public ModelAndView getProducts(){
        ModelAndView mv = new ModelAndView("products");
        List<Product> products = productsService.findAll();

        mv.addObject("products", products);
        return mv;
    }
}
