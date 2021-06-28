package com.icommerceapis.restapis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "product_colour")
public class ProductColourEntity {
    @Id
    @Column(name = "colour_code", nullable = false)
    private String colourCode;

    @Column(name = "colour_english", nullable = false)
    private String colourEnglish;

    @Column(name = "hex", nullable = false)
    private String hexCode;

    @Column(name = "is_delete", nullable = false)
    private Boolean isDelete;

    public String getColourCode() {
        return colourCode;
    }

    public void setColourCode(String colourCode) {
        this.colourCode = colourCode;
    }

    public String getColourEnglish() {
        return colourEnglish;
    }

    public void setColourEnglish(String colourEnglish) {
        this.colourEnglish = colourEnglish;
    }

    public String getHexCode() {
        return hexCode;
    }

    public void setHexCode(String hexCode) {
        this.hexCode = hexCode;
    }

    public Boolean getDelete() {
        return isDelete;
    }

    public void setDelete(Boolean delete) {
        isDelete = delete;
    }
}
