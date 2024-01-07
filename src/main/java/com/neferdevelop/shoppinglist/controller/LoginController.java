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

    // Отображает форму для ввода информации
    @GetMapping("/register")
    public String registerPage(Model model){
        model.addAttribute("user", new UserReprisentation()); // добавляем пользователя в виде атрибута, внутри создаём класс
        return "register";
    }

    // Метод, который обрабатывает эту самую информацию
    @PostMapping("/register")
    public String registerNewUser(@Valid UserReprisentation userReprisentation, BindingResult bindingResult){ // Последний параметр валидация (будет возвращаться информация о том, как была провалидирована информация из полей в userRepresentation на основании полей из userRepresentation)
        if(bindingResult.hasErrors()){ // проверка, всё ли было хорошо при валидации
            return "register";
        }
        if(!userReprisentation.getPassword().equals(userReprisentation.getRepeatPassword())){ // Проверяем равенство паролей
            bindingResult.rejectValue("password","", "Пароли не совпадают");
            return "register";
        }

        // После успешной валдиации создаём пользователя и возвращаемся на строницу login
        userService.create(userReprisentation);
        return "redirect:/login";
    }


}
