package com.neferdevelop.shoppinglist.controller;

import com.neferdevelop.shoppinglist.service.UserReprisentation;
import com.neferdevelop.shoppinglist.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    private final UserService userService; // Обязательно внедрили сюда серивс

    @Autowired // Чтобы Spring понял, что сюда нужно внедрить userService
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

}
