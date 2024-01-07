package com.neferdevelop.shoppinglist.repository;

import com.neferdevelop.shoppinglist.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Метод, который будет извлекать из баы данных пользователя по его имени
    // Нужен, когда мы хотим автризоваться
    Optional<User> findByUsername(String username); // В Spring Data JPA не нужно писать запрос, если название метода
    // соответстует определённой конвенции. JPA сама напишет запрос.
}
