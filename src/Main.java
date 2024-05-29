import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Product> products = new ArrayList<>();

        products.add(new Product(1L, "Harry Potter", "Books", 80.00));
        products.add(new Product(2L, "The Lord Of The Rings", "Books", 50.00));
        products.add(new Product(3L, "Ball", "Baby", 70.00));
        products.add(new Product(4L, "Treasure", "Boys", 65.00));
        products.add(new Product(5L, "T-Shirt", "Baby", 30.00));
        products.add(new Product(6L, "Don Chisciotte", "Books", 130.00));
        products.add(new Product(7L, "Hat", "Boys", 120.00));
        products.add(new Product(8L, "Pacifier", "Baby", 20.00));
        products.add(new Product(9L, "Football Shoes", "Boys", 45.00));
        products.add(new Product(10L, "Armrests", "Baby", 550.00));

        List<Product> filteredListBooks = getBooksPriceOver100(products, 100);
        filteredListBooks.forEach(System.out::println);

        Costumer costumer1 = new Costumer(1L, "Luca", 1);
        Costumer costumer2 = new Costumer(2L, "Pippo", 2);
        Costumer costumer3 = new Costumer(3L, "Checco", 2);

        List<Order> orders = new ArrayList<>();

        orders.add(new Order(1L, "Delivering", LocalDate.now().minusDays(2), LocalDate.now().plusDays(1), List.of(products.get(2), products.get(3)), costumer1));
        orders.add(new Order(2L, "Shipped", LocalDate.now(), LocalDate.now().plusDays(3), List.of(products.get(6), products.get(9)), costumer2));
        orders.add(new Order(3L, "Delivering", LocalDate.now().minusDays(1), LocalDate.now().plusDays(3), List.of(products.get(0), products.get(5)), costumer3));

        List<Order> filteredListOrders = getOrdersBabyProducts(orders);
        filteredListOrders.forEach(System.out::println);
    }

    public static List<Product> getBooksPriceOver100(List<Product> products, double price) {
        return products.stream().filter(product -> product.getCategory().equals("Books")).filter(product -> product.getPrice() > price).toList();
    }

    public static List<Order> getOrdersBabyProducts(List<Order> orders) {
        return orders.stream().filter(order -> order.getProducts().stream().anyMatch(product -> product.getCategory().equals("Baby"))).toList();
    }

    public static List<Product> getProductsBoysDiscount(List<Product> products) {
        double discount = 1 - 10 / 100.00;
        return products.stream().filter(product -> product.getCategory().equals("Boys")).map(product -> {
            product.setPrice(product.getPrice() * discount);
            return product;
        }).toList();
    }
}
