package com.neferdevelop.shoppinglist.repository;

import com.neferdevelop.shoppinglist.entity.ShoppingItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShoppingItemRepository extends JpaRepository<ShoppingItem, Long> { // что храним и первичный ключ
    // Чтобы наши пользователи видели только свой список здесь делаем правильный SELECT
    List<ShoppingItem> findByUserUsername(String username);
}
