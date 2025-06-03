package ru.ivt.mvc.foodDelivery.DB;

import ru.ivt.mvc.foodDelivery.model.Dish;
import ru.ivt.mvc.foodDelivery.model.Order;
import ru.ivt.mvc.foodDelivery.model.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

public class RestaurantManager implements Serializable {
    private static RestaurantManager instance;
    private List<User> users = new ArrayList<>();
    private List<Dish> dishes = new ArrayList<>();
    private List<Order> orders = new ArrayList<>();
    private static final String DATA_DIR = "src/main/webapp/data"; // Добавлена константа пути

    private RestaurantManager() {
        createDataDirectory(); // Создаем директорию при инициализации
        loadData();
        if (users.isEmpty()) {
            users.add(new User("admin", "admin123", "admin"));
            saveData();
        }
    }

    public static synchronized RestaurantManager getInstance() {
        if (instance == null) {
            instance = new RestaurantManager();
        }
        return instance;
    }

    // Методы для пользователей
    public void addUser(User user) {
        users.add(user);
        saveData();
    }

    public User findUser(String username, String password) {
        return users.stream()
                .filter(u -> u.getUsername().equals(username) && u.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }

    public boolean userExists(String username) {
        return users.stream().anyMatch(u -> u.getUsername().equals(username));
    }

    // Методы для блюд
    public void addDish(Dish dish) {
        // Автоматическая генерация ID
        int newId = dishes.stream()
                .mapToInt(Dish::getId)
                .max()
                .orElse(0) + 1;
        dish.setId(newId);
        dishes.add(dish);
        saveData();
    }
    public void deleteDish(int id) {
        dishes.removeIf(d -> d.getId() == id);
        saveData();
    }
    public void toggleDishAvailability(int id) {
        Dish dish = getDishById(id);
        if (dish != null) {
            dish.setAvailable(!dish.isAvailable());
            saveData();
        }
    }
    public void updateDish(Dish updatedDish) {
        for (int i = 0; i < dishes.size(); i++) {
            Dish dish = dishes.get(i);
            if (dish.getId() == updatedDish.getId()) {
                dishes.set(i, updatedDish);
                saveData();
                return;
            }
        }
    }
    public List<Dish> getAllDishes() {
        return new ArrayList<>(dishes);
    }
    public List<Dish> getDishesForOrder(Order order) {
        return order.getDishIds().stream()
                .map(this::getDishById)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
    public Dish getDishById(int id) {
        return dishes.stream()
                .filter(d -> d.getId() == id)
                .findFirst()
                .orElse(null);
    }

    // Методы для заказов
    public void createOrder(Order order, List<Dish> selectedDishes) {
        // Очищаем текущие блюда в заказе
        order.getDishIds().clear();

        // Добавляем только ID выбранных доступных блюд
        for (Dish dish : selectedDishes) {
            if (dish.isAvailable()) {
                order.getDishIds().add(dish.getId());
            }
        }

        // Генерируем новый ID
        int newId = orders.stream()
                .mapToInt(Order::getId)
                .max()
                .orElse(0) + 1;
        order.setId(newId);

        orders.add(order);
        saveData();
    }

    public void deleteOrder(int orderId) {
        orders.removeIf(o -> o.getId() == orderId);
        saveData();
    }

    public void updateOrder(Order updatedOrder) {
        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (order.getId() == updatedOrder.getId()) {
                orders.set(i, updatedOrder);
                saveData();
                return;
            }
        }
    }
    public void updateOrderStatus(int orderId, String status) {
        getOrderById(orderId).ifPresent(o -> o.setStatus(status));
        saveData();
    }

    public Optional<Order> getOrderById(int orderId) {
        return orders.stream()
                .filter(o -> o.getId() == orderId)
                .findFirst();
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders);
    }

    public List<Order> getOrdersForUser(int userId) {
        return orders.stream()
                .filter(o -> o.getUserId() == userId)
                .toList();
    }

    // Создание директории для данных
    private void createDataDirectory() {
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            boolean created = dataDir.mkdirs();
            if (!created) {
                System.err.println("Не удалось создать директорию: " + DATA_DIR);
            }
        }
    }

    // Работа с данными
    private void saveData() {
        saveToFile(DATA_DIR + "/users.ser", users); // Исправленные пути
        saveToFile(DATA_DIR + "/dishes.ser", dishes);
        saveToFile(DATA_DIR + "/orders.ser", orders);
    }

    private <T> void saveToFile(String filename, List<T> data) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            oos.writeObject(data);
        } catch (IOException e) {
            System.err.println("Ошибка сохранения данных: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadData() {
        users = (List<User>) loadFromFile(DATA_DIR + "/users.ser", new ArrayList<>());
        dishes = (List<Dish>) loadFromFile(DATA_DIR + "/dishes.ser", new ArrayList<>());
        orders = (List<Order>) loadFromFile(DATA_DIR + "/orders.ser", new ArrayList<>());


    }

    private List<?> loadFromFile(String filename, List<?> defaultValue) {
        File file = new File(filename);
        if (!file.exists()) {
            return defaultValue;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            return (List<?>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return defaultValue;
        }
    }
}