package ru.eracom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eracom.persist.entity.Product;
import ru.eracom.persist.repo.ProductRepository;

// @RequestMapping означает, что данный контроллер будет обрабатывать все url,
// которые начинаются с /product
@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping
    public String getProductList(Model model) {
        logger.info("Product list");

        model.addAttribute("products", productRepository.findProducts());
        return "products";

    }
 // для создания нового продукта (url "new")
    @GetMapping("new")
    public String createProduct(Model model) {
        logger.info("Create product form");

        model.addAttribute("product", new Product());
        return "product";
    }

    @PostMapping
    public String saveProduct(Product product) {
        logger.info("Save product method");

        productRepository.saveProduct(product);
        return "redirect:/product";
    }
}
