package main.shoppingcart.model;

import main.shoppingcart.utility.Constants;
import main.shoppingcart.utility.GeneralEnumeration.DiscountType;

import java.util.List;

public class Campaign {

    private Category category;
    private Double discount;
    private Integer item;
    private DiscountType discountType;

    public Campaign() {

    }

    public Campaign(Double discount, DiscountType discountType) {
        this.discount = discount;
        this.discountType = discountType;
    }

    public Campaign(Category category, Double discount, Integer item, DiscountType discountType) {
        this.category = category;
        this.discount = discount;
        this.item = item;
        this.discountType = discountType;
    }

    //TODO Selected Discount Campaign
    public static Campaign selectedDiscountCampaign(List<Cart> cartList, Long categoryId) {
        final Integer maximum = cartList.stream()
                .filter(productAndItem -> productAndItem.getProduct().getCategory().getCategoryId().equals(categoryId))
                .mapToInt(Cart::getItem)
                .sum();
        if (maximum > Constants.NUMBER_ONE && maximum < Constants.NUMBER_THREE) {
            return new Campaign(Constants.DISCOUNT_AMOUNT, DiscountType.Amount);
        } else if (maximum >= Constants.NUMBER_THREE && maximum < Constants.NUMBER_FIVE) {
            return new Campaign(Constants.PERCENT_OF_TWENTY, DiscountType.Rate);
        } else if (maximum >= Constants.NUMBER_FIVE) {
            return new Campaign(Constants.PERCENT_OF_FIFTY, DiscountType.Rate);
        }

        return new Campaign(Constants.NO_DISCOUNT, DiscountType.Rate);
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    public DiscountType getDiscountType() {
        return discountType;
    }

    public void setDiscountType(DiscountType discountType) {
        this.discountType = discountType;
    }

    @Override
    public String toString() {
        return "Campaign{" +
                "category=" + category +
                ", discount=" + discount +
                ", item=" + item +
                ", discountType=" + discountType +
                '}';
    }
}
