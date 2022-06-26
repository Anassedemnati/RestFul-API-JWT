package com.emsi.meteo.app.ws.exceptions;

import com.emsi.meteo.app.ws.responses.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value = {UserException.class})
    public ResponseEntity<Object> HandlerUserException(UserException userException, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),userException.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> HandlException(Exception exception, WebRequest request){
        ErrorMessage errorMessage = new ErrorMessage(new Date(),exception.getMessage());

        return new ResponseEntity<>(errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Object> HandleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
        Map<String,String> mapErrors = new HashMap<>();
        exception.getBindingResult().getFieldErrors().forEach(error->
            mapErrors.put(error.getField(),error.getDefaultMessage())
        );
        return new ResponseEntity<>(mapErrors, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
