package main.shoppingcart.model;

import main.shoppingcart.utility.Constants;
import main.shoppingcart.utility.GeneralEnumeration.DiscountType;

import java.util.List;
import java.util.stream.Collectors;

public class Coupon {

    private Double minimumPrice;
    private Double discount;
    private DiscountType discountType;

    public Coupon() {
        minimumPrice = Constants.MINIMUM_AMOUNT;
    }

    public Coupon(Double discount, DiscountType discountType) {
        this.discount = discount;
        this.discountType = discountType;
    }

    public Coupon(Double minimumPrice, Double discount, DiscountType discountType) {
        this.minimumPrice = minimumPrice;
        this.discount = discount;
        this.discountType = discountType;
    }

    public static Coupon applyCouponDiscount(List<Cart> cart, Coupon coupon) {
        Double totalAmount = cart.stream()
                .map(product -> product.getProduct().getProductPrice() * product.getItem())
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(Double::valueOf)
                .sum();
        if (totalAmount >= coupon.getMinimumPrice()) {
            return new Coupon(coupon.getDiscount(), DiscountType.Rate);
        }
        return new Coupon(Constants.NO_DISCOUNT, DiscountType.Rate);
    }

    public Double getMinimumPrice() {
        return minimumPrice;
    }

    public void setMinimumPrice(Double minimumPrice) {
        this.minimumPrice = minimumPrice;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return "Coupon{" +
                "minimumPrice=" + minimumPrice +
                ", discount=" + discount +
                ", discountType=" + discountType +
                '}';
    }
}
