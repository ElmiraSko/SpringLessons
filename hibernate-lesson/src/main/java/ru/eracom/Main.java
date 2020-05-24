package ru.eracom;

import org.hibernate.cfg.Configuration;
import ru.eracom.persist.Product;
import ru.eracom.persist.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

// база данных уже есть, но пока нет таблиц
        EntityManager em = emFactory.createEntityManager();

//        User user1 = new User(null, "Ivan", "passIvan");
//        User user2 = new User(null, "Oleg", "passOleg");
//        User user3 = new User(null, "Ira", "passIra");
//
//        Product product1 = new Product(null, "tea", 200);
//        Product product2 = new Product(null, "milk", 80);
//        Product product3 = new Product(null, "bread", 40);
//        Product product4 = new Product(null, "sour cream", 50);
//        Product product5 = new Product(null, "oil", 85);
//
//        user1.getBuyProducts().add(product1);
//        user1.getBuyProducts().add(product2);
//        user1.getBuyProducts().add(product5);
//        user2.getBuyProducts().add(product2);
//        user2.getBuyProducts().add(product3);
//        user3.getBuyProducts().add(product3);
//        user3.getBuyProducts().add(product4);
//
//        em.getTransaction().begin();
//        em.persist(user1);
//        em.persist(user2);
//        em.persist(user3);
//        em.persist(product1);
//        em.persist(product2);
//        em.persist(product3);
//        em.persist(product4);
//        em.persist(product5);
//        em.getTransaction().commit();
// после выполнения кода таблицы были созданы и заполнены данными

//----- Вывод информации о покупателе и продукте ----------
//        EntityManager em = emFactory.createEntityManager();
//        User user = em.find(User.class, 1L);
//        Product product = em.find(Product.class, 2L);
//        System.out.println(user.toString());
//        System.out.println(product.toString());

//---- Создали тестовые покупателя и продукт для удаления
//        EntityManager em = emFactory.createEntityManager();
//        User user5 = new User();
//        user5.setName("TestUser");
//        user5.setPassword("testPass");
//
//        Product testProduct = new Product(null, "testProduct", 78.90f);
//        user5.getBuyProducts().add(testProduct);
//
//        em.getTransaction().begin();
//        em.persist(user5);
//        em.persist(testProduct);
//        em.getTransaction().commit();

// ---- Удаление -------------
//        User testUser = em.find(User.class, 5L);
//        Product prod = em.find(Product.class, 6L);
//
//        em.getTransaction().begin();
//        em.remove(testUser);
//        em.remove(prod);
//        em.getTransaction().commit();

//------Нахождение покупателя по имени -----------
//        EntityManager em = emFactory.createEntityManager();
//        User user =  em.createQuery("from User u where u.name = :name", User.class)
//                .setParameter("name", "Oleg")
//                .getSingleResult();
//
//        List<Product> products = user.getBuyProducts();
//        for (Product p: products) {
//            System.out.println(user.getName() + " buy " + p.getTitle() + " to " + p.getCost());
//        }

// --- Нахождение продукта по названию и по цене ------------
//        EntityManager em = emFactory.createEntityManager();
//        List<Product> productsList = em.createNamedQuery("findByTitle", Product.class)
//        .setParameter("title", "bread")
//        .getResultList();
//        productsList.forEach(System.out::println);

//        List<Product> productsList2 = em.createNamedQuery("findByCost", Product.class)
//                .setParameter("cost", 200f)
//                .getResultList();
//        productsList2.forEach(System.out::println);

        List<Product> prodList = em.createQuery("from Product p")
                .getResultList();
        System.out.println(prodList);
    }

}
