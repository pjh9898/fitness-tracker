package study.fitness.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionAdviceHandler {

    @ExceptionHandler({IllegalStateException.class})
    protected ResponseEntity handleIllegalStateException(IllegalStateException e) {
        return ResponseEntity.badRequest().build();
    }
}