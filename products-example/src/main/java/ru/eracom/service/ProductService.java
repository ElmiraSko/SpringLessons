package ru.eracom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eracom.persist.entity.Product;
import ru.eracom.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public List<Product> findAllByCostBetween(BigDecimal minCost, BigDecimal maxCost) {
        return productRepository.findAllByCostBetween(minCost, maxCost);
    }

    @Transactional
    public void saveProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    @Transactional(readOnly = true)
    public Optional<Product> findById(long id) {
        return productRepository.findById(id);
    }

}
