package com.icommerceapis.restapis.entity.embeddedkey;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class ProductId implements Serializable {

    @Column(name = "sku", nullable = false)
    private String sku;

    @Column(name = "brand_id", nullable = false)
    private Integer brandId;

    public ProductId() {
    }

    public ProductId(String sku, Integer brandId) {
        this.sku = sku;
        this.brandId = brandId;
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
}
