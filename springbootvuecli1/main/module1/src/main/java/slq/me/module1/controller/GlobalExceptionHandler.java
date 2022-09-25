package slq.me.module1.controller;

import java.io.IOException;

import javax.validation.ConstraintViolationException;

import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import slq.me.module1.entity.Result;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ResponseBody
    @ExceptionHandler({ NullPointerException.class, RuntimeException.class, ClassCastException.class,
            IOException.class, IndexOutOfBoundsException.class, HttpRequestMethodNotSupportedException.class })
    public Result otherExceptionHandler(Exception e) {
        log.error(e.getMessage());
        // return new Result(1, e.getMessage(), null);
        return new Result(1, null,"otherException", e);
    }

    @ExceptionHandler(BindException.class)
    @ResponseBody
    public Result BindExceptionHandler(BindException e) {
        // log.error(e.getMessage());
        for (ObjectError error : e.getAllErrors()) {
            log.error(error.getObjectName() + ">>>" + error.getDefaultMessage());
        }
        return new Result(2, null,"BindException", e.getAllErrors());
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        for (ObjectError error : e.getAllErrors()) {
            log.error(error.getObjectName() + ">>>" + error.getDefaultMessage());
        }
        return new Result(2, null,"MethodArgumentNotValidException", e.getAllErrors());
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Result ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        log.error(e.getMessage());
        // return new Result(1, e.getMessage(), null);
        return new Result(1, null, "ConstraintViolationException", e);
    }

}