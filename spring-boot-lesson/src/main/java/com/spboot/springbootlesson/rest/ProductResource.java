package com.spboot.springbootlesson.rest;

import com.spboot.springbootlesson.persist.entity.Product;
import com.spboot.springbootlesson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/v1/product")
@RestController
public class ProductResource {
    private final ProductService productService;

    @Autowired
    public ProductResource(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "all", produces = "application/json")
    public List<Product> findAll() {
        return productService.findAll();
    }

    @GetMapping(path = "{id}/id")
    public Product findById(@PathVariable("id") long id) {
        return productService.productById(id)
                .orElseThrow(() -> new NotFoundException("В базе данных нет продукта с id = " + id));
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        if (product.getId() != null) {
            throw new IllegalArgumentException("Id found in the create request");
        }
        productService.saveProduct(product);

    }
    @PutMapping
    public void updateProduct(@RequestBody Product product) {
        productService.saveProduct(product);
    }

    @DeleteMapping(path = "/{id}/id")
    public void deleteProduct(@PathVariable("id") long id) {
        productService.delete(id);
    }

    // вывод сообщения Not found this product!
//    @ExceptionHandler
//    public ResponseEntity<String> notFoundEntityExceptionHandler(NotFoundEntityException exception) {
//        return new ResponseEntity<>("Not found this product!", HttpStatus.NOT_FOUND);
//    }

    // вывод сообщения в виде json
    @ExceptionHandler
    public ResponseEntity<ExceptionMessage> notFoundEntityExceptionHandler(NotFoundException ex) {
        ExceptionMessage errorResponse = new ExceptionMessage();
        errorResponse.setMessage(ex.getMessage());
        errorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<String> illegalArgumentExceptionHandler(IllegalArgumentException exc) {
        return new ResponseEntity<>(exc.getLocalizedMessage(), HttpStatus.BAD_REQUEST);
    }
}
