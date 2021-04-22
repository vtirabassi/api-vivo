package com.vivo.apismartphones.exceptionhandler;

import com.vivo.apismartphones.excpetions.NegocioException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @ExceptionHandler(NegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Error handlerNegocioException(NegocioException ex, WebRequest webRequest){

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        Error error =
                Error.builder()
                        .dataHora(OffsetDateTime.now())
                        .status(httpStatus.value())
                        .mensagem(ex.getMessage())
                        .build();

        return error;
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        Error error = Error.builder()
                .dataHora(OffsetDateTime.now())
                .status(status.value())
                .mensagem("Uma ou mais campos estão invalidos")
                .build();

        var campoErrors = new ArrayList<CampoError>();

        for (ObjectError ob: ex.getBindingResult().getAllErrors()) {
            var campo = ((FieldError) ob).getField();
            var mensagem = messageSource.getMessage(ob, LocaleContextHolder.getLocale());

            campoErrors.add(new CampoError(campo, mensagem));
        }

        error.setCamposErrors(campoErrors);

        return super.handleExceptionInternal(ex, error, headers, status, request);
    }
}
