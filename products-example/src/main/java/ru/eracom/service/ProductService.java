package ru.eracom.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.eracom.persist.entity.Product;
import ru.eracom.persist.repo.ProductRepository;

import java.math.BigDecimal;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional(readOnly = true)
    public Page<Product> productFilter(BigDecimal minCost, BigDecimal maxCost,
                                       String productTitle, Pageable pageable) {

            if (minCost == null && maxCost == null) {
                if (productTitle.equals(""))
                return productRepository.findAll(pageable);
                else {
                    return productRepository.findByTitleContains(productTitle, pageable);
                }
            }

            if (minCost != null && maxCost == null) {
                if (productTitle.equals(""))
                return productRepository.findByCostGreaterThanEqual(minCost, pageable);
                else return productRepository.findByTitleContainsAndCostGreaterThanEqual(productTitle, minCost, pageable);
            }

            if (minCost == null) {
                if (productTitle.equals(""))
                    return productRepository.findByCostLessThanEqual(maxCost, pageable);
                else {
                    System.out.println(productTitle + " - productTitle");
                    return productRepository.findByTitleContainsAndCostLessThanEqual(productTitle, maxCost, pageable);
                }
            }

            if (productTitle.equals(""))
                  return productRepository.findByCostBetween(minCost, maxCost, pageable);
            else return productRepository.findByTitleContainsAndCostBetween(productTitle, minCost, maxCost, pageable);

    }

    @Transactional
    public void saveProduct(Product newProduct) {
        productRepository.save(newProduct);
    }

    @Transactional(readOnly = true)
    public Optional<Product> productById(long id) {
        return productRepository.findById(id);
    }

}
