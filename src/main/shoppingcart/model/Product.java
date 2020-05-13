package main.shoppingcart.model;

public class Product {

    private Long productId;
    private String productTitle;
    private Double productPrice;
    private Category category;

    public Product() {

    }

    public Product(Long productId, String productTitle, Double productPrice, Category category) {
        this.productId = productId;
        this.productTitle = productTitle;
        this.productPrice = productPrice;
        this.category = category;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductTitle() {
        return productTitle;
    }

    public void setProductTitle(String productTitle) {
        this.productTitle = productTitle;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", productTitle='" + productTitle + '\'' +
                ", productPrice=" + productPrice +
                ", category=" + category +
                '}';
    }
}
