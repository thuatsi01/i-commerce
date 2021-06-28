package com.icommerceapis.restapis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import java.sql.Timestamp;

@Entity
@Table(name = "order_shopping_cart")
public class OrderShoppingCartEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Integer userId;

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    @Column(name = "brand_name", nullable = false)
    private String brandName;

    @Column(name = "colour_hex", nullable = false)
    private String colourHex;

    @Column(name = "colour_english", nullable = false)
    private String colourEnglish;

    @Column(name = "display_name")
    private String displayName;

    @Column(name = "price", nullable = false)
    private Long price;

    @Column(name = "quantity", nullable = false)
    private Long quantity;

    @Column(name = "create_date", nullable = false)
    private Timestamp createDate;

    @Version
    private Long version;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public Integer getBrandId() {
        return brandId;
    }

    public void setBrandId(Integer brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getColourHex() {
        return colourHex;
    }

    public void setColourHex(String colourHex) {
        this.colourHex = colourHex;
    }

    public String getColourEnglish() {
        return colourEnglish;
    }

    public void setColourEnglish(String colourEnglish) {
        this.colourEnglish = colourEnglish;
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

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createDate) {
        this.createDate = createDate;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
