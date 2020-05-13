package main.shoppingcart.model;

public class Cart {

    private Product product;
    private Integer item;

    public Cart() {
    }

    public Cart(Product product, Integer item) {
        this.product = product;
        this.item = item;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getItem() {
        return item;
    }

    public void setItem(Integer item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "ProductAndItem{" +
                "product=" + product +
                ", item=" + item +
                '}';
    }
}
