package com.icommerceapis.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TimestampUtilsTest {

    @Test
    void getCurrentLocalDateTime() {
        Assertions.assertNotNull(TimestampUtils.getCurrentLocalDateTime());
    }

}