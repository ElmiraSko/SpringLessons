package ru.eracom.persist.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ru.eracom.persist.entity.Product;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    Page<Product> findByCostGreaterThanEqual(BigDecimal minCost, Pageable pageable);
    Page<Product> findByCostLessThanEqual(BigDecimal maxCost, Pageable pageable);
    Page<Product> findByCostBetween(BigDecimal minCost, BigDecimal maxCost, Pageable pageable);

    // поиск по полному соответствию
    Page<Product> findByTitle(String productTitle, Pageable pageable);
    // поиск по начальным буквам
    Page<Product> findByTitleStartingWith(String productTitle, Pageable pageable);

    // поиск по фрагменту
    Page<Product> findByTitleContains(String productTitle, Pageable pageable);

    Page<Product> findByTitleContainsAndCostGreaterThanEqual(String productTitle, BigDecimal minCost, Pageable pageable);
    Page<Product> findByTitleContainsAndCostLessThanEqual(String productTitle, BigDecimal maxCost, Pageable pageable);
    Page<Product> findByTitleContainsAndCostBetween(String productTitle, BigDecimal minCost, BigDecimal maxCost, Pageable pageable);

    Optional<Product> findById(Long id);



}
