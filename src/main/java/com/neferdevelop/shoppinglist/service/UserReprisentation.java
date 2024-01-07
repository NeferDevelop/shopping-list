package com.neferdevelop.shoppinglist.service;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;

// Это тот же класс user, только в нём мы будем работать с формой для регистрации register.html, так как там присутствует дополнительное поле
public class UserReprisentation {
    private Long userId;
    @NotBlank // Поле обязательно должно быть заполнено
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String repeatPassword;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
