package com.cybertek.businessmansystem_api.exception;



import com.cybertek.businessmansystem_api.entity.ResponseWrapper;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;
import java.nio.file.AccessDeniedException;
import java.util.Optional;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class ExceptionMessageHandler {



    @ExceptionHandler(BussManException.class)
    public ResponseEntity<ResponseWrapper> serviceException(BussManException bussManExceptionmessage){

        String message = bussManExceptionmessage.getMessage();
        return new ResponseEntity<>(ResponseWrapper.builder().success(false).code(HttpStatus.CONFLICT.value())
                .message(message).build(),HttpStatus.CONFLICT);
    }


    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ResponseWrapper> accessdeniedException(AccessDeniedException accessDeniedExceptionmessage){
        String message = accessDeniedExceptionmessage.getMessage();

        return new ResponseEntity<>(ResponseWrapper.builder().success(false).code(HttpStatus.FORBIDDEN.value())
        .message(message).build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler({Exception.class,RuntimeException.class,Throwable.class, BadCredentialsException.class})
    public ResponseEntity<ResponseWrapper> genericException(Throwable e, HandlerMethod handlerMethod){

        Optional<DefaultExceptionMessageDTO> defaultMessage = getMessageFromAnnotation(handlerMethod.getMethod());
        if (defaultMessage.isPresent() && !ObjectUtils.isEmpty(defaultMessage.get().getMessage())){
            ResponseWrapper response = ResponseWrapper
                    .builder()
                    .success(false)
                    .message(defaultMessage.get().getMessage())
                    .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .build();

            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(ResponseWrapper
                .builder().success(false).message("Action failed: An error occured!")
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value()).build(),HttpStatus.INTERNAL_SERVER_ERROR);


    }


    //reflection usage in framework
    private Optional<DefaultExceptionMessageDTO> getMessageFromAnnotation(Method method){

        DefaultExceptionMessage defaultExceptionMessage = method.getAnnotation(DefaultExceptionMessage.class);

        if (defaultExceptionMessage != null){
            DefaultExceptionMessageDTO  defaultExceptionMessageDTO = DefaultExceptionMessageDTO
                    .builder()
                    .message(defaultExceptionMessage.defaultmessage())
                    .build();

            return Optional.of(defaultExceptionMessageDTO);


        }

        return Optional.empty();


    }

}
