package ru.javadaddy.repository;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartRepositoryImpl {

    List<CartItem> cartItemList = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть null");
        }

        if (quantity <= 0) {
            throw new IllegalArgumentException("Количество товара должно быть положительным");
        }

        //Оборачиваем в Optional, проверяем в есть ли в списке cartItem товар, который хотим положить в корзину, если нашел, запомнить
        Optional<CartItem> existingItem = cartItemList.stream()
                .filter(item -> item.getProduct().equals(product))
                .findFirst();

        if (existingItem.isPresent()) {
            CartItem cartItem = existingItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        } else {
            cartItemList.add(new CartItem(product, quantity));
        }
    }

    //TODO: Реализовать применение скидки
    public void applyDiscount(double percent) {
        if (percent <= 0) {
            throw new IllegalArgumentException("Процент скидки должен быть положительным");
        }
    }

    //TODO: Реализовать итоговая сумму с учётом скидки
    public double getCalculateTotal() {
        return 0;
    }

    //TODO: Реализовать получение товара из корзины
    private Product findItem() {

        return null;
    }
}
