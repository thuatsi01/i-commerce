package com.icommerceapis.restapis.repository;

import com.icommerceapis.restapis.entity.ProductEntity;
import com.icommerceapis.restapis.entity.embeddedkey.ProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, ProductId> {

    Optional<ProductBookingInformation> findByProductIdAndIsDelete(@Param("productId") ProductId productId, @Param("isDelete") Boolean isDelete);

    interface ProductBookingInformation {
        ProductBookingId getProductId();

        String getDisplayName();

        Long getPrice();

        ProductBookingColour getProductColour();

        ProductBookingBrand getProductBrand();

        interface ProductBookingId {
            String getSku();

            Integer getBrandId();
        }

        interface ProductBookingColour {
            String getHexCode();

            String getColourEnglish();
        }

        interface ProductBookingBrand {
            String getBrand();
        }
    }
}
