package ru.eracom.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.eracom.persist.entity.User;
import ru.eracom.service.UserService;

import javax.validation.Valid;

@RequestMapping("/user")
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

//    private UserRepository userRepository; // репозиторий юзер-сущностей, напрямую их использовать нельзя
    private UserService userService; // нужно пользоваться userService

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
        return "redirect:/product?minCost=&maxCost=&productTitle=";
    }
    // на страницу user
    @GetMapping("toUsers")
    public String goToUser() {
        logger.info("Going to user");
        return "redirect:/user";
    }
}