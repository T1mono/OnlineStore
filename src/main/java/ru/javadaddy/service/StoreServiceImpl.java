package ru.javadaddy.service;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;
import ru.javadaddy.repository.CartRepository;
import ru.javadaddy.repository.CartRepositoryImpl;

import java.util.List;

public class StoreServiceImpl implements StoreService {
    private final CartRepository cartRepository = new CartRepositoryImpl();

    @Override
    public void searchItem(Product product, int quantity) {
        cartRepository.addItem(product, quantity);
    }

    @Override
    public void applyDiscount(double percent) {
        cartRepository.applyDiscount(percent);
    }

    @Override
    public double getCalculateTotal() {
        return cartRepository.getCalculateTotal();
    }

    @Override
    public List<CartItem> getItems() {
        return cartRepository.findItems();
    }
}

