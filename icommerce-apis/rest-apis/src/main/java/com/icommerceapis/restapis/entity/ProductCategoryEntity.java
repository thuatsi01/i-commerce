package com.icommerceapis.restapis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_category")
public class ProductCategoryEntity {
    @Id
    @Column(name = "category_code", nullable = false)
    private String categoryCode;

    @Column(name = "category_english", nullable = false)
    private String categoryEnglish;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryEnglish() {
        return categoryEnglish;
    }

    public void setCategoryEnglish(String categoryEnglish) {
        this.categoryEnglish = categoryEnglish;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
