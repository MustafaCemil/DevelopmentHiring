package shoppingcart;

import main.shoppingcart.model.Cart;
import main.shoppingcart.model.Category;
import main.shoppingcart.model.Coupon;
import main.shoppingcart.model.Product;
import main.shoppingcart.utility.Constants;
import main.shoppingcart.utility.GeneralEnumeration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CouponTest {

    private Product product;
    private Category category;
    private Cart cart;
    private final List<Cart> cartList = new ArrayList<>();

    @BeforeEach
    void initEach() {
        product = new Product(1L, "Asus", 70.0, category);
        category = new Category(1L, "Elektronik");
        cart = new Cart(product, 2);
        cartList.add(cart);
    }

    @Test
    @DisplayName("Coupon Test Case Apply Discount")
    void couponTestOne() {
        Coupon coupon = new Coupon(100.0, 5.0, GeneralEnumeration.DiscountType.Rate);
        Coupon result = Coupon.applyCouponDiscount(cartList, coupon);
        Assertions.assertAll("Coupon Test Case Apply Discount",
                () -> Assertions.assertNotEquals(result.getDiscountType(), GeneralEnumeration.DiscountType.Amount),
                () -> Assertions.assertEquals(result.getDiscountType(), coupon.getDiscountType()),
                () -> Assertions.assertEquals(result.getDiscount(), coupon.getDiscount()));
    }

    @Test
    @DisplayName("Coupon Test Case no Discount")
    void couponTestTwo() {
        Coupon coupon = new Coupon(500.0, 10.0, GeneralEnumeration.DiscountType.Rate);
        Coupon result = Coupon.applyCouponDiscount(cartList, coupon);
        Assertions.assertAll("Coupon Test Case Apply Discount",
                () -> Assertions.assertNotEquals(result.getDiscountType(), GeneralEnumeration.DiscountType.Amount),
                () -> Assertions.assertEquals(result.getDiscountType(), coupon.getDiscountType()),
                () -> Assertions.assertNotEquals(result.getDiscount(), coupon.getDiscount()),
                () -> Assertions.assertEquals(result.getDiscount(), Constants.NO_DISCOUNT));
    }
}
