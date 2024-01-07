package com.neferdevelop.shoppinglist.security;

import com.neferdevelop.shoppinglist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserAuthService implements UserDetailsService {
    private final UserRepository userRepository; // нужен доступ в бд

    @Autowired
    public UserAuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override // Проверит, есть ли такой пользователь в бд, если нет, то мы кинем исключение
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .map(user -> new User(
                        user.getUsername(),
                        user.getPassword(),
                        Collections.singletonList(new SimpleGrantedAuthority("USER")) // Здесь прописываем роли
                )) // С помощью метода map из optional преобразуем во что=то ещё
                .orElseThrow(() -> new UsernameNotFoundException("Пользователь не найден"));
    }
}
