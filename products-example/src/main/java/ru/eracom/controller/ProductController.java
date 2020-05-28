package ru.eracom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eracom.persist.entity.Product;
import ru.eracom.persist.repo.ProductRepository;
import ru.eracom.service.ProductService;

import java.math.BigDecimal;
import java.util.List;

// @RequestMapping означает, что данный контроллер будет обрабатывать все url,
// которые начинаются с /product
@RequestMapping("/product")
@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public String getProductList(Model model,
                                 @RequestParam(required = false) BigDecimal minCost,
                                 @RequestParam(required = false) BigDecimal maxCost) {
        logger.info("Product list width minCost{} and maxCost{}", minCost, maxCost);

        if (minCost == null){
            minCost = new BigDecimal("0.00");
        }
        if (maxCost == null){
            maxCost = new BigDecimal(Integer.MAX_VALUE);
        }
        List<Product> productList =productService.findAllByCostBetween(minCost, maxCost);
        model.addAttribute("products", productList);
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

        productService.saveProduct(product);
        return "redirect:/product";
    }

    @GetMapping(value="/{id}")
    public String findProductById(Model model, @PathVariable(value="id") int id) {
        logger.info("Find product by id method");

        model.addAttribute("products", productService.findById(id));
        return "products";
    }
}
