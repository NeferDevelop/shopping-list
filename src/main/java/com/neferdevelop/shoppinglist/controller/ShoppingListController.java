package com.neferdevelop.shoppinglist.controller;


import com.neferdevelop.shoppinglist.entity.ShoppingItem;
import com.neferdevelop.shoppinglist.entity.User;
import com.neferdevelop.shoppinglist.repository.ShoppingItemRepository;
import com.neferdevelop.shoppinglist.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;

@Controller
public class ShoppingListController {
    // Обработка основной старницы

    private static final Logger logger = LoggerFactory.getLogger(ShoppingListController.class);
    private final UserRepository userRepository;
    // Для получения репозитория делаем следующее
    private final ShoppingItemRepository shoppingItemRepository;
    // Это конструктор
    @Autowired
    public ShoppingListController(UserRepository userRepository, ShoppingItemRepository shoppingProductRepository) {
        this.userRepository = userRepository;
        this.shoppingItemRepository = shoppingProductRepository;
    }
    ////
    @GetMapping //Отправляем запрос типа Get
    public String indexPage(Model model, Principal principal){ // Последний параметр - получение пользователя для отображения страницы, присуще именно ему
        logger.info("Username{ }", principal.getName());
        //Чтобы показать добавленные покупки нужно сделать следующее действие
        model.addAttribute("items", shoppingItemRepository.findByUserUsername(principal.getName())); // Под именами item и items доступен представления объектов в html
        // findByUserUsername(principal.getName()) - эта часть позволит нам искать страницы user присуще именно ему
        //Чтобы создавать эти покупки нужно сделать следующее
        model.addAttribute("item", new ShoppingItem());
        return "index";
    }

    // Следующий метод ответственный за добавление элемента в список
    @PostMapping //Благодаря этой аннотации Spring будет направлять post запросы в этот метод (как правило используется для создания новых объектов)
    public String newShoppingItem(ShoppingItem shoppingItem, Principal principal){
        logger.info("Username{ }", principal.getName());
        User user = userRepository.findByUsername(principal.getName()).get();
        shoppingItem.setUser(user);
        // Установите order_number перед сохранением
        List<ShoppingItem> items = shoppingItemRepository.findAll();
        int newOrderNumber = items.size() + 1; // Новый элемент будет иметь порядковый номер на 1 больше, чем общее количество элементов
        shoppingItem.setOrder_number(newOrderNumber);
        shoppingItemRepository.save(shoppingItem);
        updateOrderNumbers(); // Обновление порядковых номеров для всех элементов
        return "redirect:/";
    }
    private void updateOrderNumbers() {
        List<ShoppingItem> items = shoppingItemRepository.findAll();
        for (int i = 0; i < items.size(); i++) {
            ShoppingItem item = items.get(i);
            item.setOrder_number(i + 1);
            shoppingItemRepository.save(item);
        }
    }

    // spring.mvc.hiddenmethod.felter.enabled=true  - в настройках будет давать возможность сделать delete
    // Через get это делать нельзя, так как этот метод не должен изменить на странице, для изменения используем put,delete,post. Метод get должен быть безопасным
    @DeleteMapping("/{id}") // Делаем через url
    public String deleteShoppingItem(@PathVariable("id") Long id){ // Чтобы попала сюда, нужно написать аннотацию
        shoppingItemRepository.deleteById(id);
        updateOrderNumbers(); // Обновление порядковых номеров для всех элементов
        return "redirect:/"; // обновили страницу
    }

}