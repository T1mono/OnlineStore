package ru.javadaddy.service;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;
import ru.javadaddy.repository.CartRepository;
import ru.javadaddy.repository.CartRepositoryImpl;

import java.util.List;

public class StoreService {
    private final CartRepository cartRepository = new CartRepositoryImpl();


    public void searchItem(Product product, int quantity) {
        cartRepository.addItem(product, quantity);
    }

    public void applyDiscount(double percent) {
        cartRepository.applyDiscount(percent);
    }

    public double getCalculateTotal() {
        return cartRepository.getCalculateTotal();
    }

    public List<CartItem> getItems() {
        return cartRepository.findItems();
    }
}

