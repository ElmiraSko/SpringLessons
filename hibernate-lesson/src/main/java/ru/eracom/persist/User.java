package ru.eracom.persist;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(length = 64, unique = true, nullable = false)
    private String name;

    @Column(length = 64, nullable = true)
    private String password;

    @ManyToMany
    @JoinTable(name = "users_products",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "products_id"))
    private List<Product> buyProducts = new ArrayList<>();


    public User() {
    }

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Product> getBuyProducts() {
        return buyProducts;
    }

    public void setBuyProducts(List<Product> products) {
        this.buyProducts = products;
    }

    @Override
    public String toString() {
        StringBuilder userInfo = new StringBuilder("User: \nid= ").append(id);
        userInfo.append(", name= ").append(name);
        userInfo.append(", password= ").append(password);
        userInfo.append(",\nbuy products: ");

        if ((buyProducts != null) && (buyProducts.size() > 0)) {
            for (int i = 0; i < buyProducts.size(); i++) {
                if (i > 0)
                    userInfo.append(", ");
                userInfo.append(buyProducts.get(i).getTitle());
            }
        }
        return userInfo.toString();
    }
}

