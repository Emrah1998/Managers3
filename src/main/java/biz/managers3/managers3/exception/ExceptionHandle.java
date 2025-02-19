package biz.managers3.managers3.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static biz.managers3.managers3.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_CODE;
import static biz.managers3.managers3.exception.ExceptionConstants.UNEXPECTED_EXCEPTION_MESSAGE;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
@Slf4j
public class ExceptionHandle {
 @ExceptionHandler(Exception.class)
 @ResponseStatus(INTERNAL_SERVER_ERROR)
 public ExceptionResponse handleException(Exception e) {
     log.error("exception:",e);
     return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE, UNEXPECTED_EXCEPTION_MESSAGE);
 }

 @ExceptionHandler(NotFoundException.class)
 @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException e) {
      log.error("NotFoundException:",e);
      return new ExceptionResponse(e.getCode(), e.getMessage());
 }
}
