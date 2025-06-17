package ru.javadaddy.service;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;

import java.util.List;

public interface StoreService {
    void searchItem(Product product, int quantity);

    void applyDiscount(double percent);

    double getCalculateTotal();

    List<CartItem> getItems();
}
