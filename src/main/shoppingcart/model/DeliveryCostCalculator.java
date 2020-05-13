package main.shoppingcart.model;

import main.shoppingcart.service.ShoppingCart;

public class DeliveryCostCalculator {

    private Double costPerDelivery;
    private Double costPerProduct;
    private Double fixedCost;

    public DeliveryCostCalculator() {
    }

    public DeliveryCostCalculator(Double costPerDelivery, Double costPerProduct, Double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    public Double calculateFor(ShoppingCart shoppingCart) {
        final Long numberOfDelivery = shoppingCart.getCarts().stream()
                .map(Cart::getProduct)
                .map(product -> product.getCategory().getCategoryId())
                .distinct()
                .count();

        final Long numberOfProducts = shoppingCart.getCarts().stream()
                .map(Cart::getProduct)
                .map(product -> product.getProductId())
                .distinct()
                .count();

        Double deliveryCost = (this.costPerDelivery * numberOfDelivery) + (this.costPerProduct * numberOfProducts) + fixedCost;
        shoppingCart.setDeliverCost(deliveryCost);

        return deliveryCost;
    }

    public Double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(Double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }

    public Double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(Double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public Double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(Double fixedCost) {
        this.fixedCost = fixedCost;
    }

    @Override
    public String toString() {
        return "DeliveryCostCalculator{" +
                "costPerDelivery=" + costPerDelivery +
                ", costPerProduct=" + costPerProduct +
                ", fixedCost=" + fixedCost +
                '}';
    }
}
