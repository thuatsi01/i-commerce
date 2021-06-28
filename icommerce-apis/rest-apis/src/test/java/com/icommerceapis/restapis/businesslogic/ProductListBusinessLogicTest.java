package com.icommerceapis.restapis.businesslogic;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.icommerceapis.restapis.model.request.ProductListRequest;
import com.icommerceapis.restapis.repository.ProductListRepository;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {TestConfiguration.PATH_UNIT_TEST_CONFIG})
class ProductListBusinessLogicTest {

    @Autowired
    private ProductListBusinessLogic productListBusinessLogic;

    @Test
    void covertRequestToCondition() throws JsonProcessingException {
        var request = new ProductListRequest();
        request.setBrandId(1);
        request.setCategoryCode("category 01");
        request.setColourCode("colour 01");
        request.setMaxPrice(100L);
        request.setMinPrice(1L);
        request.setPageIndex(1);
        request.setPageSize(25);

        var expected = ProductListRepository.ProductListInformationSearchCondition.builder()
                .brandId(1)
                .categoryCode("category 01")
                .colourCode("colour 01")
                .maxPrice(100L)
                .minPrice(1L)
                .pageIndex(1)
                .pageSize(25)
                .build();

        var actual = productListBusinessLogic.covertRequestToCondition(request);
        var objectMapper = new ObjectMapper();
        Assertions.assertEquals(objectMapper.writeValueAsString(expected), objectMapper.writeValueAsString(actual));
    }
}