package com.bank.BankTransaction.handler;

import com.bank.BankTransaction.exception.BadRequestException;
import com.bank.BankTransaction.exception.BadRequestExceptionDetails;
import com.bank.BankTransaction.exception.ExceptionDetails;
import com.bank.BankTransaction.exception.ValidationExceptionDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler  {
    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException badRequestException){
        return new ResponseEntity<>(
                new BadRequestExceptionDetails(
                    "Bad Request Exception",
                        HttpStatus.BAD_REQUEST.value(),
                        badRequestException.getMessage(),
                        badRequestException.getClass().getName(),
                        LocalDateTime.now()
                ),HttpStatus.BAD_REQUEST
        );
    }

    //MethodArgumentNotValidException

  //  @ExceptionHandler(MethodArgumentNotValidException.class)
    //public ResponseEntity<ValidationExceptionDetails> handlerMethodArgumentNotValidException
      //      (MethodArgumentNotValidException e){
    @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
          MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {
        // Campos
        List<FieldError> fieldErrorList=  e
                .getBindingResult()
                .getFieldErrors();
        String fields = fieldErrorList.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));
        //Message
        String fieldsMessage = fieldErrorList.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        return new ResponseEntity<>(
                new ValidationExceptionDetails(
                        "Campo Inválido",
                        HttpStatus.BAD_REQUEST.value(),
                        e.getMessage(),
                        e.getClass().getName(),
                        LocalDateTime.now(),
                        fields,
                        fieldsMessage), HttpStatus.BAD_REQUEST
        );}

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(
            Exception ex, @Nullable Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

        ExceptionDetails exceptionDetails = new ExceptionDetails(ex.getMessage(),
                status.value(),
                ex.getMessage(),
                ex.getClass().getName(),
                LocalDateTime.now());
        return new ResponseEntity<>(exceptionDetails, headers, status);
    }


}