package com.api.user.exceptionhandler;

import com.api.user.exceptionhandler.excptions.CPFExistsException;
import com.api.user.exceptionhandler.excptions.CPFInvalidException;
import com.api.user.exceptionhandler.excptions.UserNotFund;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

@ControllerAdvice
public class UserControllerAdvice {

    @ResponseBody
    @ExceptionHandler(CPFInvalidException.class)
    public ResponseEntity<MessageExceptionHandler> cpfInvalid(CPFInvalidException cpfInvalidException) {
        MessageExceptionHandler error = new MessageExceptionHandler( new Date(), HttpStatus.NOT_FOUND.value(), "CPF Invalido.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(CPFExistsException.class)
    public ResponseEntity<MessageExceptionHandler> cpfExists(CPFExistsException cpfExistsException) {
        MessageExceptionHandler error = new MessageExceptionHandler( new Date(), HttpStatus.NOT_FOUND.value(), "CPF já cadastrado.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(UserNotFund.class)
    public ResponseEntity<MessageExceptionHandler> userNotFund(UserNotFund userNotFund) {
        MessageExceptionHandler error = new MessageExceptionHandler( new Date(), HttpStatus.NOT_FOUND.value(), "Usuário não encontrado.");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public  ResponseEntity<MessageExceptionHandler> argumentsNotValid(MethodArgumentNotValidException notValid) {
        BindingResult result = notValid.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        StringBuilder sb = new StringBuilder("Os campos seguintes não podem ser nulos:");
        for (FieldError fieldError : fieldErrors) {
            sb.append(" ");
            sb.append(fieldError.getField());
        }

        MessageExceptionHandler error = new MessageExceptionHandler(
                new Date(), HttpStatus.BAD_REQUEST.value(), sb.toString());
        return new ResponseEntity<>(error,HttpStatus.BAD_REQUEST);
    }
}
