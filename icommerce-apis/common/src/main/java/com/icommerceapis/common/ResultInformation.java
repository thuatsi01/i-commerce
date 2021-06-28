package com.icommerceapis.common;

import com.icommerceapis.common.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor(staticName = "of")
public class ResultInformation {
    Integer code;
    String message;

    public static ResultInformation success() {
        return ResultInformation.of(1, "Success");
    }

    public static ResultInformation error(ErrorCode errorCode) {
        return ResultInformation.of(errorCode.getCode(), errorCode.getMessage());
    }

    public static ResultInformation error(Integer code, String message) {
        return ResultInformation.of(code, message);
    }
}
