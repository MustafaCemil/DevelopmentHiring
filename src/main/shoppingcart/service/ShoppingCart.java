package main.shoppingcart.service;

import main.shoppingcart.model.*;
import main.shoppingcart.utility.Constants;
import main.shoppingcart.utility.GeneralEnumeration.DiscountType;

import java.util.*;
import java.util.stream.Collectors;

public class ShoppingCart {

    private List<Cart> carts;
    private List<Cart> firstCartList;
    private Double totalAmontAfterDiscounts;
    private Double couponDiscount;
    private Double campignDiscount;
    private Double deliverCost;

    public ShoppingCart() {
        carts = new ArrayList<>();
        firstCartList = new ArrayList<>();
    }

    public ShoppingCart(List<Cart> carts) {
        this.carts = carts;
    }

    //TODO add item
    public void addItem(Product product, Integer item) {
        this.carts.add(new Cart(product, item));

        Product prod = new Product(product.getProductId(), product.getProductTitle(), product.getProductPrice(), product.getCategory());
        this.firstCartList.add(new Cart(prod, item));
    }

    public void applyDiscounts() {
        Set<Long> categoryIds = this.carts.stream()
                .map(Cart::getProduct)
                .map(product -> product.getCategory().getCategoryId())
                .collect(Collectors.toSet());

        categoryIds.forEach(categoryId -> {
            Campaign campaign = Campaign.selectedDiscountCampaign(this.carts, categoryId);
            this.selectedCategoryWithProductsDiscount(categoryId, campaign.getDiscount(), campaign.getDiscountType());
        });
    }

    public void applyCoupon() {
        Coupon coupon = Coupon.applyCouponDiscount(this.carts, new Coupon(100.0, 5.0, DiscountType.Rate));
        this.allProductsDiscount(coupon.getDiscount(), coupon.getDiscountType());
    }

    public void applyCoupon(Coupon coupon) {
        coupon = Coupon.applyCouponDiscount(this.carts, coupon);
        this.allProductsDiscount(coupon.getDiscount(), coupon.getDiscountType());
    }

    public void addDiscounts() {
        this.addCampignDiscount();
        this.addCouponDiscount();
        this.addTotalAmountAfterDiscount();
    }

    public void print() {
        List<GroupByDto> groupByDtos = this.groupByDtoList();

        Map<Category, List<GroupByDto>> result = groupByDtos.stream()
                .collect(Collectors.groupingBy(GroupByDto::getCategory));

        System.out.println(result);
    }

    //TODO all products discount
    private void allProductsDiscount(Double discount, DiscountType discountType) {
        carts.stream()
                .map(Cart::getProduct)
                .forEach(product -> {
                    if (discountType.equals(DiscountType.Amount)) {
                        product.setProductPrice(product.getProductPrice() - discount);
                    } else
                        product.setProductPrice(product.getProductPrice() - (product.getProductPrice() * discount / 100));
                });
    }

    //TODO auto selected campaing products discount
    private void selectedCategoryWithProductsDiscount(Long categoryId, Double discount, DiscountType discountType) {
        carts.stream()
                .map(Cart::getProduct)
                .filter(prod -> prod.getCategory().getCategoryId().equals(categoryId))
                .forEach(product -> {
                    if (discountType.equals(DiscountType.Amount)) {
                        product.setProductPrice(product.getProductPrice() - discount);
                    } else
                        product.setProductPrice(product.getProductPrice() - (product.getProductPrice() * discount / 100));
                });
    }

    private void addTotalAmountAfterDiscount() {
        Double totalAmount = this.amountCalculate() + this.deliverCost;
        this.setTotalAmontAfterDiscounts(totalAmount);
    }

    private void addCouponDiscount() {
        ShoppingCart cart = inqureFirstShoppingCart(this.firstCartList);
        cart.applyCoupon();

        Double couponDiscount = cart.amountCalculate();
        this.setCouponDiscount(couponDiscount);
    }

    private void addCampignDiscount() {
        ShoppingCart cart = inqureFirstShoppingCart(this.firstCartList);
        cart.applyDiscounts();

        Double campignDiscount = cart.amountCalculate();
        this.setCampignDiscount(campignDiscount);
    }

    private ShoppingCart inqureFirstShoppingCart(List<Cart> cartList) {
        ShoppingCart shoppingCart = new ShoppingCart();
        final List<Cart> carts = new ArrayList<>();

        cartList.stream().forEach(cart -> {
            Product product = new Product(cart.getProduct().getProductId(), cart.getProduct().getProductTitle(),
                    cart.getProduct().getProductPrice(), cart.getProduct().getCategory());
            carts.add(new Cart(product, cart.getItem()));
            shoppingCart.setCarts(Arrays.asList(new Cart(product, cart.getItem())));
        });
        shoppingCart.setCarts(carts);

        return shoppingCart;
    }

    private List<GroupByDto> groupByDtoList() {
        List<GroupByDto> groupByDtos = new ArrayList<>();
        this.firstCartList.stream()
                .forEach(cart -> {
                    GroupByDto groupByDto = new GroupByDto();
                    groupByDto.setCategory(cart.getProduct().getCategory());
                    groupByDto.setCategoryName(cart.getProduct().getCategory().getCategoryTitle());
                    groupByDto.setProductName(cart.getProduct().getProductTitle());
                    groupByDto.setQuantity(cart.getItem());
                    groupByDto.setUnitPrice(cart.getProduct().getProductPrice());
                    groupByDto.setTotalPrice(cart.getProduct().getProductPrice() * cart.getItem());

                    Double totalDiscount = (cart.getProduct().getProductPrice() - totalDiscountOnProduct(cart)) * cart.getItem();
                    groupByDto.setTotalDiscount(totalDiscount);
                    groupByDtos.add(groupByDto);
                });
        return groupByDtos;
    }

    private Double totalDiscountOnProduct(Cart cart) {
        ShoppingCart shoppingCart = inqureFirstShoppingCart(Arrays.asList(cart));
        shoppingCart.applyDiscounts();
        shoppingCart.applyCoupon();

        Double result = shoppingCart.getCarts().stream()
                .map(cart1 -> cart1.getProduct().getProductPrice())
                .findFirst().get();

        return result;
    }

    private Double amountCalculate() {
        return this.carts.stream()
                .map(product -> product.getProduct().getProductPrice() * product.getItem())
                .collect(Collectors.toList())
                .stream()
                .mapToDouble(Double::valueOf)
                .sum();
    }

    public List<Cart> getCarts() {
        return carts;
    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
    }

    public List<Cart> getFirstCartList() {
        return firstCartList;
    }

    public void setFirstCartList(List<Cart> firstCartList) {
        this.firstCartList = firstCartList;
    }

    public Double getTotalAmontAfterDiscounts() {
        return totalAmontAfterDiscounts;
    }

    public void setTotalAmontAfterDiscounts(Double totalAmontAfterDiscounts) {
        this.totalAmontAfterDiscounts = totalAmontAfterDiscounts;
    }

    public Double getCouponDiscount() {
        return couponDiscount;
    }

    public void setCouponDiscount(Double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    public Double getCampignDiscount() {
        return campignDiscount;
    }

    public void setCampignDiscount(Double campignDiscount) {
        this.campignDiscount = campignDiscount;
    }

    public Double getDeliverCost() {
        return deliverCost;
    }

    public void setDeliverCost(Double deliverCost) {
        this.deliverCost = deliverCost;
    }

    @Override
    public String toString() {
        return "ShoppingCart{" +
                "carts=" + carts +
                ", firstCartList=" + firstCartList +
                ", totalAmontAfterDiscounts=" + totalAmontAfterDiscounts +
                ", couponDiscount=" + couponDiscount +
                ", campignDiscount=" + campignDiscount +
                ", deliverCost=" + deliverCost +
                '}';
    }
}
