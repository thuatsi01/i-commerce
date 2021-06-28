package com.icommerceapis.common.utils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;

public class TimestampUtils {

    public static Timestamp getCurrentLocalDateTime() {
        var localDateTime = LocalDateTime.now(ZoneId.of("GMT+00:00"));
        return Timestamp.valueOf(localDateTime);
    }
}
