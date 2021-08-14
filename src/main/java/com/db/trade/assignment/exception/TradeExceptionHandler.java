package com.db.trade.assignment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class TradeExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> tradeNotFoundException(final BusinessException businessException) {
        ErrorResponse errorResponse = this.buildErrorResponse(businessException.getReason(), businessException.getResponse());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    private ErrorResponse buildErrorResponse(String reason, Map<String, Object> response) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("INVTRD");
        errorResponse.setCause(reason);
        return  errorResponse;
    }

}
