package com.neferdevelop.shoppinglist.service;

import com.neferdevelop.shoppinglist.entity.User;
import com.neferdevelop.shoppinglist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private UserRepository repository;

    private BCryptPasswordEncoder passwordEncoder; // шифруем пароль

    @Autowired // Найдёт экземпляры класса и внедрит сюда
    public UserService(UserRepository repository, BCryptPasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    // Метод будет получать класс представление
    public void create(UserReprisentation userReprisentation){
        User user = new User();
        user.setUsername(userReprisentation.getUsername()); // Вставляем имя пользователя из параметра
        user.setPassword(passwordEncoder.encode(userReprisentation.getPassword())); // защитили пароль
        repository.save(user); // сохранили user в базу
    }
}
