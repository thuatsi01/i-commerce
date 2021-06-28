package com.icommerceapis.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor(staticName = "of")
@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;
    private final Map<String, String> datas;

    public static BusinessException of(ErrorCode errorCode) {
        return of(errorCode, new HashMap<>());
    }
}
