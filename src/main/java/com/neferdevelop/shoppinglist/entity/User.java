package com.neferdevelop.shoppinglist.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long userId;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    // Вот здесь начинаем прописывать, то о чём говориться перед полем private User user; в классе ShoppingItem
    // Здесь мы берём какой-то набор каких-то shoppingItem
    // Здесь аннотация наоборот OneToMane - связано с БД
    @OneToMany(
            mappedBy = "user", // Говорим, что список мапится на поле user, указали имя поля private User user;
            cascade = CascadeType.ALL, // Говорим, что при удалении пользователя все его записи должны быть удалены
            orphanRemoval = true // Если обнаружатся Item непривязанные ни к одному пользователю, то они будут удаляться, чтобы не копился мусор в БД
    )
    private List<ShoppingItem> shoppingItems;

    public User() {
    }

    public Long getId() {
        return userId;
    }

    public void setId(Long id) {
        this.userId = id;
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

    public List<ShoppingItem> getShoppingItems() {
        return shoppingItems;
    }

    public void setShoppingItems(List<ShoppingItem> shoppingItems) {
        this.shoppingItems = shoppingItems;
    }
}
