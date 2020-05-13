package main.shoppingcart.model;

public class Category {

    private Long categoryId;
    private String categoryTitle;
    private Long parentCategoryId;

    public Category() {

    }

    public Category(Long categoryId, String categoryTitle) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
    }

    public Category(Long categoryId, String categoryTitle, Long parentCategoryId) {
        this.categoryId = categoryId;
        this.categoryTitle = categoryTitle;
        this.parentCategoryId = parentCategoryId;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryTitle() {
        return categoryTitle;
    }

    public void setCategoryTitle(String categoryTitle) {
        this.categoryTitle = categoryTitle;
    }

    public Long getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Long parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }


    @Override
    public String toString() {
        return "Category{" +
                "categoryId=" + categoryId +
                ", categoryTitle='" + categoryTitle + '\'' +
                ", parentCategoryId=" + parentCategoryId +
                '}';
    }
}
