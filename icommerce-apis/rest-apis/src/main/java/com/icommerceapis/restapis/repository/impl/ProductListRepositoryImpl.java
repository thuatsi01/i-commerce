package com.icommerceapis.restapis.repository.impl;

import com.google.common.collect.ImmutableList;
import com.icommerceapis.restapis.entity.ProductBrandEntity_;
import com.icommerceapis.restapis.entity.ProductCategoryEntity_;
import com.icommerceapis.restapis.entity.ProductColourEntity_;
import com.icommerceapis.restapis.entity.ProductEntity;
import com.icommerceapis.restapis.entity.ProductEntity_;
import com.icommerceapis.restapis.entity.embeddedkey.ProductId_;
import com.icommerceapis.restapis.repository.ProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import java.util.List;

@Repository
public class ProductListRepositoryImpl implements ProductListRepository {

    @Autowired
    private EntityManager entityManager;

    @Override
    public List<ProductionInformation> getProductListInformation(ProductListInformationSearchCondition searchCondition) {
        if (searchCondition == null) {
            return List.of();
        }

        var criteriaBuilder = entityManager.getCriteriaBuilder();
        var query = criteriaBuilder.createQuery(ProductionInformation.class);

        // Join condition
        var productEntity = query.from(ProductEntity.class);
        var productBrand = productEntity.join(ProductEntity_.productBrand);
        var productColour = productEntity.join(ProductEntity_.productColour);
        var productCategory = productEntity.join(ProductEntity_.productCategory);

        // Where condition
        var predicateListBuilder = ImmutableList.<Predicate>builder();
        searchCondition.getBrandId().ifPresent(brandId -> predicateListBuilder.add(criteriaBuilder.equal(productBrand.get(ProductBrandEntity_.id), brandId)));
        searchCondition.getCategoryCode().ifPresent(categoryCode -> predicateListBuilder.add(criteriaBuilder.equal(productCategory.get(ProductCategoryEntity_.categoryCode), categoryCode)));
        searchCondition.getColourCode().ifPresent(colourCode -> predicateListBuilder.add(criteriaBuilder.equal(productColour.get(ProductColourEntity_.colourCode), colourCode)));
        searchCondition.getMinPrice().ifPresent(minPrice -> predicateListBuilder.add(criteriaBuilder.greaterThanOrEqualTo(productEntity.get(ProductEntity_.price), minPrice)));
        searchCondition.getMaxPrice().ifPresent(maxPrice -> predicateListBuilder.add(criteriaBuilder.lessThanOrEqualTo(productEntity.get(ProductEntity_.price), maxPrice)));
        query.where(criteriaBuilder.and(predicateListBuilder.build().toArray(new Predicate[0])));

        query.select(criteriaBuilder.construct(ProductionInformation.class,
                productEntity.get(ProductEntity_.productId).get(ProductId_.sku),
                productEntity.get(ProductEntity_.displayName),
                productBrand.get(ProductBrandEntity_.id),
                productBrand.get(ProductBrandEntity_.brand),
                productColour.get(ProductColourEntity_.colourCode),
                productColour.get(ProductColourEntity_.colourEnglish),
                productColour.get(ProductColourEntity_.hexCode),
                productCategory.get(ProductCategoryEntity_.categoryCode),
                productCategory.get(ProductCategoryEntity_.categoryEnglish),
                productEntity.get(ProductEntity_.price)
        )).orderBy(criteriaBuilder.asc(productEntity.get(ProductEntity_.productId)));

        return entityManager.createQuery(query)
                .setFirstResult(searchCondition.getPageIndex() * searchCondition.getPageSize())
                .setMaxResults(searchCondition.getPageSize())
                .getResultList();
    }
}
