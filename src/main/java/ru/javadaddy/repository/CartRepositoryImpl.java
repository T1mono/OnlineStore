package ru.javadaddy.repository;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        if (percent < 0 || percent > 100) {
            throw new IllegalArgumentException("Процент скидки должен от 1 до 100");
        }

        //Пройти по каждому товару в списке и получить цену каждого товара и применить к цене скидку, и установить цену со скидку к товару
        cartItemList.forEach(item -> {
            if (item.getProduct() == null) {
                throw new IllegalArgumentException("Товар не найден");
            }
            double oldPrice = item.getProduct().getPrice();
            double newPrice = oldPrice * (1 - percent / 100);
            item.getProduct().setPrice(newPrice);
        });
    }

    //TODO: Расчёт и вывод содержимого корзины
    public double getCalculateTotal() {
        if (cartItemList == null || cartItemList.isEmpty()) {
            return 0.0;
        }

        double total = 0.0;

        for (CartItem item : cartItemList) {
            if (item.getProduct() != null) {
                total += item.getProduct().getPrice() * item.getQuantity();
            }
        }

        return total;
    }

    //TODO: Реализовать получение товаров из корзины
    private List<CartItem> findItems() {

        if (cartItemList == null || cartItemList.isEmpty()) {
            return Collections.emptyList();
        }

        return new ArrayList<>(cartItemList);
    }
}
