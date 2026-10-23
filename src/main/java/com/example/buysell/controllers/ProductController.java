package com.example.buysell.controllers;

import com.example.buysell.entities.Product;
import com.example.buysell.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping("/")
    public String products(@RequestParam(name = "title", required = false) String title,
                           Model model, Principal principal) {
        System.out.println(productService.getUserByPrincipal(principal));
        model.addAttribute("products", productService.getProductList(title));
        model.addAttribute("user", productService.getUserByPrincipal(principal));
        return "products";
    }

    @GetMapping("/products/{id}")
    public String productInfo(@PathVariable Long id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        model.addAttribute("images", product.getImages());

        return "productInfo";
    }

    @PostMapping("/products/create")
    public String createProduct(Product product, @RequestParam(name = "file1")MultipartFile file1,
                                @RequestParam(name = "file2")MultipartFile file2,
                                @RequestParam(name = "file3")MultipartFile file3,
                                Principal principal) throws IOException {
        productService.saveProduct(principal, product, List.of(file1, file2, file3));
        return "redirect:/";
    }

    @PostMapping("/products/delete/{id}")
    public String deleteProduct(@PathVariable long id) {
        productService.deleteProduct(id);
        return "redirect:/";
    }
}
