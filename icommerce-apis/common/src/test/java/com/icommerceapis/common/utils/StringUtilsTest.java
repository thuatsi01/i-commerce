package com.icommerceapis.common.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

class StringUtilsTest {

    @Test
    void isBlank_false() {
        Assertions.assertFalse(StringUtils.isBlank("123"));
    }

    @Test
    void format() {
        var map = new HashMap<String, String>();
        map.put("test", "123");
        Assertions.assertEquals("test1234", StringUtils.format("test${test}4", map));
    }
}