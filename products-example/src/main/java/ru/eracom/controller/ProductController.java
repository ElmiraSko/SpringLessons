package ru.eracom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.eracom.persist.entity.Product;
import ru.eracom.service.ProductService;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.Optional;

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

    // для страницы продуктов
    @GetMapping
    public String getProductList(Model model,
                                 @RequestParam(name = "minCost", required = false) BigDecimal minCost,
                                 @RequestParam(name = "maxCost", required = false) BigDecimal maxCost,
                                 @RequestParam(name = "productTitle", required = false) String productTitle,
                                 @RequestParam(name = "page") Optional<Integer> page,
                                 @RequestParam(name = "size") Optional<Integer> size) {

        logger.info("Product list width minCost{} and maxCost{}", minCost, maxCost);

        Page<Product> productPage = productService.productFilter(minCost, maxCost, productTitle,
                PageRequest.of(page.orElse(1)-1, size.orElse(5)));

        model.addAttribute("productsPage", productPage);
        model.addAttribute("minCost", minCost);
        model.addAttribute("maxCost", maxCost);
        model.addAttribute("productTitle", productTitle);
        model.addAttribute("prevPageNumber", productPage.hasPrevious() ? productPage.previousPageable().getPageNumber() + 1 : -1);
        model.addAttribute("nextPageNumber", productPage.hasNext() ? productPage.nextPageable().getPageNumber() + 1 : -1);
        return "products";
    }

 // для создания нового продукта (url "new")
    @GetMapping("new")
    public String createProduct(Model model) {
        logger.info("Create product form");

        model.addAttribute("product", new Product());
        return "product";
    }
    // для перехода на страницу user со страницы продукта
    @GetMapping("toUsers")
    public String goToUser() {
        logger.info("Going to user from product");
        return "redirect:/user";
    }
    // для перехода на страницу продуктов
    @GetMapping("toProducts")
    public String goToProducts() {
        logger.info("Going to products");
        return "redirect:/product?minCost=&maxCost=&productTitle=";
    }
    // сохраняем новый продукт
    @PostMapping
    public String saveProduct(Product product) {
        logger.info("Save product method");

        productService.saveProduct(product);
        return "redirect:/product?minCost=&maxCost=&productTitle=";
    }

    // переход на страницу редактирования товара
    @GetMapping(value="/{id}")
    public String findProductById(Model model, @PathVariable(value="id") long id) {
        logger.info("Find product by id method");

        Optional<Product> editProduct = productService.productById(id);
        model.addAttribute("id", id);
        model.addAttribute("editProduct", editProduct.get());
        return "editProduct";
    }

    // редактирование и сохранение товара
    @PostMapping(value="/{id}")
    public String saveProductById(@Valid Product product) {
        logger.info("Save product by id");

        productService.saveProduct(product);
        return "redirect:/product?minCost=&maxCost=&productTitle=";
    }
}
