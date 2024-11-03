package com.JJsCarRent.config;

import com.JJsCarRent.models.exceptions.HttpGenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                               WebRequest request) {
        log.error("Ha ocurrido un error MethodArgumentNotValidException {}", ex);
        List<ValidacionError> errores = new ArrayList<>();
        ex.getBindingResult().getAllErrors().forEach(error -> errores.add(ValidacionError.builder()
                .mensaje(error.getDefaultMessage())
                .campo(((FieldError) error).getField())
                .valorRechazado(((FieldError) error).getRejectedValue())
                .build()));

        return ResponseEntity.badRequest()
                .body(ErrorResponse.builder()
                        .mensaje("La solicitud contiene errores de validación")
                        .errores(errores)
                        .estado(HttpStatus.BAD_REQUEST)
                        .marcaDeTiempo(LocalDateTime.now())
                        .ruta(request.getDescription(false))
                        .build());
    }

    @ExceptionHandler(value = IOException.class)
    public ResponseEntity<ErrorResponse> handleFileNotFoundException(IOException ex) {
        log.error("Ha ocurrido un error handleFileNotFoundException {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensaje("Ha ocurrido un error generando el PDF")
                .marcaDeTiempo(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<ErrorResponse> handleMessageNotReadableException(HttpMessageNotReadableException ex) {
        log.error("Ha ocurrido un error MessageNotReadableException {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensaje("Ups! No pudimos leer la información :/")
                .marcaDeTiempo(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = HttpGenericException.class)
    public ResponseEntity<ErrorResponse> handleHttpGenericException(HttpGenericException e) {
        log.error("Ha ocurrido un error handleHttpGenericException {}", e);
        ErrorResponse error = ErrorResponse.builder()
                .codigo(e.getHttpStatus().value())
                .mensaje(e.getMessage())
                .marcaDeTiempo(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, e.getHttpStatus());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleBadCredentialsException(BadCredentialsException e) {
        log.error("Ha ocurrido un error handleBadCredentialsException {}", e);
        ErrorResponse error = ErrorResponse.builder()
                .codigo(HttpStatus.BAD_REQUEST.value())
                .mensaje(e.getMessage())
                .marcaDeTiempo(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleAccessDeniedException(AccessDeniedException e) {
        log.error("Ha ocurrido un error handleAccessDeniedException {}", e);
        ErrorResponse error = ErrorResponse.builder()
                .codigo(HttpStatus.FORBIDDEN.value())
                .mensaje(e.getMessage())
                .marcaDeTiempo(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        log.error("Ha ocurrido un error handleException {}", ex);
        ErrorResponse error = ErrorResponse.builder()
                .codigo(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .mensaje("Ha ocurrido un error")
                .marcaDeTiempo(LocalDateTime.now())
                .build();
        ex.printStackTrace();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
