package com.icommerceapis.restapis.entity;

import com.icommerceapis.restapis.entity.embeddedkey.ProductId;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.sql.Timestamp;

@Entity
@Table(name = "product")
public class ProductEntity {
    @EmbeddedId
    private ProductId productId;

    @Column(name = "category_code", nullable = false)
    private String categoryCode;

    @Column(name = "colour_code")
    private String colourCode;

    @Column(name = "display_name", nullable = false)
    private String displayName;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    @OneToOne(fetch = FetchType.LAZY)
    @MapsId("brandId")
    @JoinColumn(name = "brand_id", referencedColumnName = "id", insertable = false, updatable = false)
    private ProductBrandEntity productBrand;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_code", referencedColumnName = "category_code", insertable = false, updatable = false)
    private ProductCategoryEntity productCategory;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "colour_code", referencedColumnName = "colour_code", insertable = false, updatable = false)
    private ProductColourEntity productColour;

    public ProductId getProductId() {
        return productId;
    }

    public void setProductId(ProductId productId) {
        this.productId = productId;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public ProductBrandEntity getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(ProductBrandEntity productBrand) {
        this.productBrand = productBrand;
    }

    public ProductCategoryEntity getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(ProductCategoryEntity productCategory) {
        this.productCategory = productCategory;
    }

    public ProductColourEntity getProductColour() {
        return productColour;
    }

    public void setProductColour(ProductColourEntity productColour) {
        this.productColour = productColour;
    }
}
