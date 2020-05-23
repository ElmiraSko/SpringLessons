package ru.eracom.persist.repo;

import org.springframework.stereotype.Repository;
import ru.eracom.persist.entity.Product;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class ProductRepository {

    private final AtomicInteger identityGen;
    private final List<Product> products;

    public ProductRepository() {
        identityGen = new AtomicInteger(0);
        this.products = new LinkedList<>();
    }

    public List<Product> findProducts() { // может вернуть все продукты в виде списка
        return products;
    }

    public void saveProduct(Product newProduct) {
        long id = identityGen.incrementAndGet();
        newProduct.setId(id);
        products.add(newProduct);
    }

    public Product findById(int id) {  // может выдать продукт по ключу
        return products.get(id);
    }
}
