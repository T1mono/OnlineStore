package ru.javadaddy;

import ru.javadaddy.model.CartItem;
import ru.javadaddy.model.Product;
import ru.javadaddy.service.StoreService;
import ru.javadaddy.service.StoreServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class StoreApp {

    private final StoreService storeService;
    private final Map<Long, Product> productCatalog;
    private final Scanner scanner;

    public StoreApp() {
        this.storeService = new StoreServiceImpl();
        this.productCatalog = initializeProducts();
        this.scanner = new Scanner(System.in);
    }

    private Map<Long, Product> initializeProducts() {
        Map<Long, Product> products = new HashMap<>();
        products.put(1L, new Product(1L, "Ноутбук", 89990.99));
        products.put(2L, new Product(2L, "Телефон", 49990.50));
        products.put(3L, new Product(3L, "Монитор", 12990.00));
        products.put(4L, new Product(4L, "Клавиатура", 5990.75));
        products.put(5L, new Product(5L, "Мышь", 2990.25));
        return products;
    }

    public void start() {
        boolean running = true;
        while (running) {
            printMainMenu();
            System.out.println("\nВведите число от 1 до 5");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> showProductCatalog();
                case 2 -> addToCart();
                case 3 -> applyDiscount();
                case 4 -> showCart();
                case 5 -> running = false;
            }
        }
        System.out.println("Программа завершена.");
        scanner.close();
    }

    private void printMainMenu() {
        System.out.println("\n=== Меню магазина ===");
        System.out.println("1. Показать каталог товаров");
        System.out.println("2. Добавить товар в корзину");
        System.out.println("3. Применить скидку");
        System.out.println("4. Показать корзину");
        System.out.println("5. Выход");
    }

    private void showProductCatalog() {
        System.out.println("\n=== Каталог товаров ===");
        productCatalog.values().forEach(product ->
                System.out.println(product.getId() + " " + product.getName() + " " + product.getPrice())
        );
    }

    private void addToCart() {
        showProductCatalog();

        System.out.println("\nВведите ID товара: ");
        long productId = scanner.nextLong();
        scanner.nextLine();

        if (!productCatalog.containsKey(productId)) {
            System.out.println("Товар не найден!");
            return;
        }

        System.out.println("Введите колчисество: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();


        Product product = productCatalog.get(productId);
        storeService.searchItem(product, quantity);
        System.out.println("Добавлено: " + product.getName() + " x" + quantity);
    }


    private void applyDiscount() {
        System.out.print("\nВведите скидку (%): ");
        int discount = scanner.nextInt();
        scanner.nextLine();

        storeService.applyDiscount(discount);
        System.out.println("Скидка применена!");
    }


    private void showCart() {
        System.out.println("\n=== Корзина ===");

        List<CartItem> items = storeService.getItems();

        if (items.isEmpty()) {
            System.out.println("(пусто)");
            return;
        }

        // Просто перебираем товары и выводим их
        for (CartItem item : items) {
            Product p = item.getProduct();
            System.out.println(
                    p.getName() + " - " +
                            p.getPrice() + " руб. × " +
                            item.getQuantity() + " = " +
                            item.getTotalPrice() + " руб."
            );
        }

        // Итоговая сумма
        System.out.println("\nОбщая сумма: " + storeService.getCalculateTotal() + " руб.");
    }

    public static void main(String[] args) {
        StoreApp storeApp = new StoreApp();
        storeApp.start();
    }
}