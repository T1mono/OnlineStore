package ru.javadaddy.repository;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;

import java.util.List;

public interface CartRepository {
    void addItem(Product product, int quantity);

    void applyDiscount(double percent);

    double getCalculateTotal();
    List<CartItem> findItems();
}
