package com.neferdevelop.shoppinglist.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "product")
public class ShoppingItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "order_number")
    private Integer order_number;

    // Привязываем item к конкретному пользователю, наша аннотация говорит, что какое-то количсетво ShoppingItem будет соответствовать одному пользователю
    // Но это не всё, также нужно всё прописать в user
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public ShoppingItem() {
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getOrder_number() {
        return order_number;
    }

    public void setOrder_number(Integer order_number) {
        this.order_number = order_number;
    }
}