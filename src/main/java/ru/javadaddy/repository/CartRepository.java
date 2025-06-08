package ru.javadaddy.repository;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;

import java.util.ArrayList;
import java.util.List;

public class CartRepository {

    List<CartItem> cartItemList = new ArrayList<>();

    public void addItem(Product product, int quantity) {
        if (product == null) {
            throw new IllegalArgumentException("Товар не может быть пустым");
        }

        boolean isContainsProduct = cartItemList.stream()
                .anyMatch(i -> i.getProduct().equals(product));

        final CartItem cartItem = new CartItem(product, quantity);

        if (!isContainsProduct) {
            cartItemList.add(cartItem);
        }
    }
}
