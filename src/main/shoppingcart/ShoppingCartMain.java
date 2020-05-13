package main.shoppingcart;

import main.shoppingcart.model.Category;
import main.shoppingcart.model.Coupon;
import main.shoppingcart.model.DeliveryCostCalculator;
import main.shoppingcart.model.Product;
import main.shoppingcart.service.ShoppingCart;
import main.shoppingcart.utility.Constants;
import main.shoppingcart.utility.GeneralEnumeration.DiscountType;

public class ShoppingCartMain {

    public static void main(String[] args) {
        Category categoryOne = new Category(1L, "Elektronik");
        Category categoryTwo = new Category(2L, "Notebook", 1L);

        Product computer = new Product(1L, "Asus", 100.0, categoryTwo);
        Product phone = new Product(2L, "Samsung", 50.0, categoryOne);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.addItem(computer, 3);
        shoppingCart.addItem(phone, 2);

        System.out.println(shoppingCart);
        System.out.println("\n----------------------");

        shoppingCart.applyDiscounts();
        System.out.println(shoppingCart + "\n----------------------");

        shoppingCart.applyCoupon();
        System.out.println(shoppingCart + "\n----------------------");

        Coupon coupon = new Coupon(200.0, 10.0, DiscountType.Rate);
        shoppingCart.applyCoupon(coupon);

        DeliveryCostCalculator costCalculator = new DeliveryCostCalculator(10.0, 5.0, Constants.FIXED_COST);
        costCalculator.calculateFor(shoppingCart);
        System.out.println(costCalculator + "\n----------------------");

        shoppingCart.addDiscounts();
        System.out.println(shoppingCart + "\n----------------------");

       shoppingCart.print();
   }

}
