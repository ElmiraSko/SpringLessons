package com.spboot.springbootlesson.persist.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Это поле обязательное для заполнения")
    @Column(length = 32, nullable = false)
    private String name;

    @Min(value = 16, message = "Минимальное значение поля 16 лет")
    @Max(value = 110, message = "Максимальное значение поля 110 лет")
    @Column(nullable = false)
    private Integer age;

    @NotBlank(message = "Email введен не верно")
    @Column(length = 64, nullable = false)
    private String email;

    @NotBlank(message = "Это поле обязательное для заполнения")
    @Column(length = 32, nullable = false)
    private String password;

    @Transient
    private String repeatPassword;

    public User() {
    }

    public User(Long id, String name, Integer age, String password) {
        this.id = id;
        this.name = name;
        this.age = age;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}

