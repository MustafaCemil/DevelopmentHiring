package shoppingcart;

import main.shoppingcart.model.Cart;
import main.shoppingcart.model.Category;
import main.shoppingcart.model.Coupon;
import main.shoppingcart.model.Product;
import main.shoppingcart.service.ShoppingCart;
import main.shoppingcart.utility.GeneralEnumeration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCartTest {

    private final List<Cart> cartList = new ArrayList<>();
    private Product product;
    private Category category;
    private Cart cart;

    @BeforeEach
    void initEach() {
        category = new Category(1L, "Elektronik");
        product = new Product(1L, "Asus", 100.0, category);
        cart = new Cart(product, 2);

        cartList.add(cart);
    }

    @Test
    @DisplayName("Apply Discount")
    void applyDiscount() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Product prod = new Product(2L, "Apple", 120.0, category);
        shoppingCart.addItem(product, 2);
        shoppingCart.addItem(prod, 2);

        shoppingCart.applyDiscounts();

        Double totalDiscount = shoppingCart.getCarts().stream()
                .map(Cart::getProduct)
                .mapToDouble(Product::getProductPrice)
                .sum();
        Assertions.assertAll("Apply Discount",
                () -> Assertions.assertNotEquals(totalDiscount, 220.0),
                () -> Assertions.assertEquals(totalDiscount, 176.0));
    }

    @Test
    @DisplayName("Apply Coupon")
    void applyCoupon() {
        ShoppingCart shoppingCart = new ShoppingCart();

        Product prod = new Product(2L, "Apple", 120.0, category);
        shoppingCart.addItem(product, 2);
        shoppingCart.addItem(prod, 2);

        shoppingCart.applyCoupon(new Coupon(100.0, 5.0, GeneralEnumeration.DiscountType.Rate));

        Double totalDiscount = shoppingCart.getCarts().stream()
                .map(Cart::getProduct)
                .mapToDouble(Product::getProductPrice)
                .sum();
        Assertions.assertAll("Apply Coupon",
                () -> Assertions.assertNotEquals(totalDiscount, 440.0),
                () -> Assertions.assertEquals(totalDiscount, 209.0));
    }
}