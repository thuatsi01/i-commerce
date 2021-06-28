package com.icommerceapis.restapis.controller.handler;

import com.icommerceapis.common.ResultInformation;
import com.icommerceapis.common.exception.BusinessException;
import com.icommerceapis.common.exception.ErrorCode;
import com.icommerceapis.common.utils.StringUtils;
import com.icommerceapis.restapis.model.response.DefaultResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler
    protected ResponseEntity<Object> handleExceptionInternal(Exception exception, WebRequest request) {
        if (exception instanceof BusinessException) {
            var businessException = (BusinessException) exception;

            if (businessException.getDatas() == null || businessException.getDatas().isEmpty()) {
                return new ResponseEntity<>((DefaultResponse.of(ResultInformation.error(businessException.getErrorCode()))), HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>((
                        DefaultResponse.of(ResultInformation.error(businessException.getErrorCode().getCode(), StringUtils.format(businessException.getErrorCode().getMessage(), businessException.getDatas())))
                ), HttpStatus.BAD_REQUEST);
            }
        }

        return new ResponseEntity<>((DefaultResponse.of(ResultInformation.error(ErrorCode.OTHER))), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
