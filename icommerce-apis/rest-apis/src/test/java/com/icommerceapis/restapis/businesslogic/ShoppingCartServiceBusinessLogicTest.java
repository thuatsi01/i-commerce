package com.icommerceapis.restapis.businesslogic;

import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.restapis.entity.OrderShoppingCartEntity;
import com.icommerceapis.restapis.model.request.ShoppingCartSaveRequest;
import com.icommerceapis.restapis.repository.ProductRepository;
import com.icommerceapis.restapis.testconfig.TestConfiguration;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.Stream;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(properties = {TestConfiguration.PATH_UNIT_TEST_CONFIG})
class ShoppingCartServiceBusinessLogicTest {

    @Autowired
    private ShoppingCartServiceBusinessLogic shoppingCartServiceBusinessLogic;

    static Stream<Arguments> validateSaveRequestThrowExceptionDataProvider() {
        var saveRequest1 = new ShoppingCartSaveRequest();
        saveRequest1.setSku("01");

        var saveRequest2 = new ShoppingCartSaveRequest();
        saveRequest2.setSku("02");
        saveRequest2.setBrandId(2);

        var saveRequest3 = new ShoppingCartSaveRequest();
        saveRequest3.setSku("03");
        saveRequest3.setBrandId(3);
        saveRequest3.setQuantity(-1L);

        var saveRequest4 = new ShoppingCartSaveRequest();
        saveRequest4.setSku("04");
        saveRequest4.setBrandId(4);
        saveRequest4.setQuantity(4L);

        var saveRequest5 = new ShoppingCartSaveRequest();
        saveRequest5.setSku("05");
        saveRequest5.setBrandId(5);
        saveRequest5.setQuantity(5L);
        saveRequest5.setPrice(-1L);

        return Stream.of(
                Arguments.of(null, "Save request is null"),
                Arguments.of(new ShoppingCartSaveRequest(), "Save request all properties is null"),
                Arguments.of(saveRequest1, "Only sku"),
                Arguments.of(saveRequest2, "Missing quantity and price"),
                Arguments.of(saveRequest3, "Quantity less than 0"),
                Arguments.of(saveRequest4, "Missing price"),
                Arguments.of(saveRequest5, "Price is less than 0")
        );
    }

    @ParameterizedTest
    @MethodSource("validateSaveRequestThrowExceptionDataProvider")
    void validateSaveRequest_throwException(ShoppingCartSaveRequest request, String description) {
        Assertions.assertThrows(BusinessException.class, () -> shoppingCartServiceBusinessLogic.validateSaveRequest(request), description);
    }

    @Test
    void validateSaveRequest_success() {
        var saveRequest = new ShoppingCartSaveRequest();
        saveRequest.setSku("05");
        saveRequest.setBrandId(5);
        saveRequest.setQuantity(5L);
        saveRequest.setPrice(1000L);

        try {
            shoppingCartServiceBusinessLogic.validateSaveRequest(saveRequest);
        } catch (Exception ignore) {
            Assertions.fail();
        }
    }

    @Test
    void convertSaveEntity_success() {
        var saveRequest = new ShoppingCartSaveRequest();
        saveRequest.setSku("01");
        saveRequest.setBrandId(1);
        saveRequest.setQuantity(1L);
        saveRequest.setPrice(1000L);

        var productInformation = new ProductRepository.ProductBookingInformation() {
            @Override
            public ProductBookingId getProductId() {
                return new ProductBookingId() {
                    @Override
                    public String getSku() {
                        return "sku 01";
                    }

                    @Override
                    public Integer getBrandId() {
                        return 1;
                    }
                };
            }

            @Override
            public String getDisplayName() {
                return "test product 01";
            }

            @Override
            public Long getPrice() {
                return 1000L;
            }

            @Override
            public ProductBookingColour getProductColour() {
                return new ProductBookingColour() {
                    @Override
                    public String getHexCode() {
                        return "code 01";
                    }

                    @Override
                    public String getColourEnglish() {
                        return "colour 01";
                    }
                };
            }

            @Override
            public ProductBookingBrand getProductBrand() {
                return () -> "Test rand 01";
            }
        };

        var userId = 1;
        var condition = ShoppingCartServiceBusinessLogic.ShoppingSaveDto
                .of(userId, saveRequest, null, productInformation);

        var actual = shoppingCartServiceBusinessLogic.convertSaveEntity(condition);

        Assertions.assertEquals(1, actual.getUserId());
        Assertions.assertEquals("sku 01", actual.getSku());
        Assertions.assertEquals("Test rand 01", actual.getBrandName());
        Assertions.assertEquals("colour 01", actual.getColourEnglish());
        Assertions.assertEquals(1000L, actual.getPrice());
    }
}