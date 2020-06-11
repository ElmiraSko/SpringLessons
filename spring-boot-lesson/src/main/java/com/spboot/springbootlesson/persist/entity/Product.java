package com.spboot.springbootlesson.persist.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Это поле обязательное для заполнения")
    @Column(length = 32, nullable = false)
    private String title;

    @DecimalMin(message = "Допустимое минимальное значение больше 10", value = "10", inclusive = false)
    @DecimalMax(message = "Превышено максимальное значение 500000", value = "500000")
    @Column(length = 32, nullable = false)
    private BigDecimal cost;

    @ManyToOne
    private User user;

    public Product() {
    }

    public Product(Long id, String title, BigDecimal cost) {
        this.id = id;
        this.title = title;
        this.cost = cost;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
