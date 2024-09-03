package org.restaurante.msplazoleta.domain.model;

import org.restaurante.msplazoleta.domain.model.enums.CategoryEnum;

public class DishModel {

    private Long id;

    private String name;

    private Integer price;

    private String description;

    private String urlImage;

    private CategoryEnum category;

    private Long restaurantId;

    private Boolean isEnable;

    public DishModel() {
    }

    public DishModel(Long id, String name, Integer price, String description, String urlImage, CategoryEnum category, Long restaurantId, Boolean isEnable) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.urlImage = urlImage;
        this.category = category;
        this.restaurantId = restaurantId;
        this.isEnable = isEnable;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public CategoryEnum getCategory() {
        return category;
    }

    public void setCategory(CategoryEnum category) {
        this.category = category;
    }

    public Long getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(Long restaurantId) {
        this.restaurantId = restaurantId;
    }

    public Boolean getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(Boolean enable) {
        isEnable = enable;
    }
}
