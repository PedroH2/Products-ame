package com.Products.controller;

import com.Products.Model.Customer;
import com.Products.Model.Product;
import com.Products.Service.CustomerService;
import com.Products.Service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
public class ProductController {
    @Autowired
    ProductsService productsService;
    @Autowired
    CustomerService customerService;

    @GetMapping(value = { "/home" })
    public ModelAndView login(HttpServletRequest request, Customer cust) {
        HttpSession session = request.getSession();
        if(session.getAttribute("usuarioLogado") != null) {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject(new Customer());
            return mv;
        }else {
            ModelAndView mv = new ModelAndView("login");
            mv.addObject(new Customer());
            return mv;
        }
    }
    @PostMapping("/fazerLogin")
    public String fazerLogin(HttpServletRequest request,Customer cust) throws SQLException {
        Customer banco = customerService.findByCPF(cust.getCPF());
        if ( banco != null) {
            if(banco.getPassword().equals(cust.getPassword())) {
                request.getSession().setAttribute("usuarioLogado", banco);
                request.getSession().setAttribute("cliente", banco);
                return "redirect:/products/filter/" + banco.getId();
            }
            else{
                return "redirect:/";
            }
            }
            else {
                return "/";
        }
    }

    @RequestMapping (value="products/{id}", method = RequestMethod.GET)
    public ModelAndView getProductDetails(@PathVariable("id") long id){
        
        ModelAndView mv = new ModelAndView("productDetail");
        Product product = productsService.findById(id);

        mv.addObject("product", product);
         return  mv;
    }
    @RequestMapping(value="/", method = RequestMethod.GET)
    public String getLogin() { return "login"; }

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
    @RequestMapping(value="/products/filter/{id}", method = RequestMethod.GET)
    public ModelAndView getFilter(@PathVariable("id") Long id) {
        ModelAndView mv = new ModelAndView("products");
        Customer customer = new Customer();
        Product product = new Product();
        customer = customerService.findById(id);



        String tagCliente [] = new String [customer.getTags().split(",").length];
        tagCliente = customer.getTags().split(",");

        String tagProduto [];

        List<Product> listProduct = new ArrayList<>();

        List<Product> products = productsService.findAll();
        for (Product pd : products) {
            tagProduto = new String [pd.getTags().split(",").length];
            tagProduto = pd.getTags().split(",");

            for (int i = 0; i < tagCliente.length; i++) {
                if (Arrays.stream(tagProduto).anyMatch(tagCliente[i]::equals)) {
                    listProduct.add(pd);
                }
            }
        }

        mv.addObject("products", listProduct);
        return mv;
    }
}