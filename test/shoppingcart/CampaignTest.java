package shoppingcart;

import main.shoppingcart.model.Campaign;
import main.shoppingcart.model.Cart;
import main.shoppingcart.model.Category;
import main.shoppingcart.model.Product;
import main.shoppingcart.utility.Constants;
import main.shoppingcart.utility.GeneralEnumeration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class CampaignTest {

    Cart cart;
    Product product;
    Category category;
    List<Cart> cartList = new ArrayList<>();

    @BeforeEach
    void initEach() {
        category = new Category(1L, "Phone");
        product = new Product(1L, "Samsung", 100.0, category);
    }

    @Test
    @DisplayName("Percent of 20")
    void ruleOne() {
        cart = new Cart(product, 3);
        cartList.add(cart);

        Campaign campaign = new Campaign(category, 20.0, 3, GeneralEnumeration.DiscountType.Rate);
        Campaign result = Campaign.selectedDiscountCampaign(cartList, category.getCategoryId());
        Assertions.assertAll("Percent of 20",
                () -> Assertions.assertNotEquals(result.getItem(), 4),
                () -> Assertions.assertEquals(result.getDiscountType(), campaign.getDiscountType()),
                () -> Assertions.assertEquals(result.getDiscount(), Constants.PERCENT_OF_TWENTY));
    }

    @Test
    @DisplayName("Percent of 50")
    void ruleTwo() {
        cart = new Cart(product, 5);
        cartList.add(cart);

        Campaign campaign = new Campaign(category, 50.0, 5, GeneralEnumeration.DiscountType.Rate);
        Campaign result = Campaign.selectedDiscountCampaign(cartList, category.getCategoryId());
        Assertions.assertAll("Percent of 50",
                () -> Assertions.assertNotEquals(result.getItem(), 4),
                () -> Assertions.assertEquals(result.getDiscountType(), campaign.getDiscountType()),
                () -> Assertions.assertEquals(result.getDiscount(), Constants.PERCENT_OF_FIFTY));
    }

    @Test
    @DisplayName("Amount 5")
    void ruleThree() {
        cart = new Cart(product, 2);
        cartList.add(cart);

        Campaign campaign = new Campaign(category, 5.0, 2, GeneralEnumeration.DiscountType.Amount);
        Campaign result = Campaign.selectedDiscountCampaign(cartList, category.getCategoryId());
        Assertions.assertAll("Amount",
                () -> Assertions.assertNotEquals(result.getItem(), 2),
                () -> Assertions.assertEquals(result.getDiscountType(), campaign.getDiscountType()),
                () -> Assertions.assertEquals(result.getDiscount(), Constants.DISCOUNT_AMOUNT));
    }
}
