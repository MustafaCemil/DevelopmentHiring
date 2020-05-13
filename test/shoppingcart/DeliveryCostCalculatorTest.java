package shoppingcart;

import main.shoppingcart.model.Cart;
import main.shoppingcart.model.Category;
import main.shoppingcart.model.DeliveryCostCalculator;
import main.shoppingcart.model.Product;
import main.shoppingcart.service.ShoppingCart;
import main.shoppingcart.utility.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DeliveryCostCalculatorTest {
    Product product;
    Category category;
    Cart cart;
    ShoppingCart shoppingCart;
    List<Cart> cartList = new ArrayList<>();

    @BeforeEach
    void initEach() {
        category = new Category(1L, "Tablet");
        product = new Product(1L, "Apple", 100.0, category);
        cart = new Cart(product, 3);
        cartList.add(cart);
        shoppingCart = new ShoppingCart(cartList);
    }

    @Test
    @DisplayName("Delivery Cost Calculator Test")
    void testOne() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(10.0, 10.0, Constants.FIXED_COST);
        Double amount = deliveryCostCalculator.calculateFor(shoppingCart);
        Assertions.assertAll("Delivery Cost Calculator Test",
                () -> Assertions.assertNotNull(amount),
                () -> Assertions.assertNotEquals(amount, 100),
                () -> Assertions.assertEquals(amount, 22.990000000000002));
    }
}
