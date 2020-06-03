package com.spboot.springbootlesson.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.spboot.springbootlesson.persist.entity.User;
import com.spboot.springbootlesson.service.UserService;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService  userService) {
        this.userService = userService;
    }

    @GetMapping
    public String userList(Model model) {
        logger.info("User list");

        model.addAttribute("users", userService.findAll());
        return "users";
    }

    @GetMapping("new")
    public String createUser(Model model) {
        logger.info("Create user form");

        model.addAttribute("user", new User());
        return "user";
    }
    @GetMapping("edit")
    public String createUser(@RequestParam("id") Long id, Model model) {
        logger.info("Edit user width id {} ", id);

        model.addAttribute("user", userService.findById(id));
        return "user";
    }

    @PostMapping
    public String saveUser(@Valid User user, BindingResult bindingResult) {
        logger.info("Save user method");

        // стандартная (внутренняя) валидация
        if (bindingResult.hasErrors()) {
            return "user";
        }
        // пользовательская валидация
        if (!user.getPassword().equals(user.getRepeatPassword())) {
            bindingResult.rejectValue("repeatPassword", "", "пароли не совпадают");
            return "user";
        }
        userService.save(user);
        return "redirect:/user";
    }
    // переход на страницу продуктов
    @GetMapping("toProducts")
    public String goToProducts() {
        logger.info("Going to products");
        return "redirect:/product";
    }
    // на страницу user
    @GetMapping("toUsers")
    public String goToUser() {
        logger.info("Going to user");
        return "redirect:/user";
    }
    @DeleteMapping
    public String delete(@RequestParam("id") long id) {
        logger.info("Delete user width id {} ", id);

        userService.delete(id);
        return "redirect:/user";
    }
}