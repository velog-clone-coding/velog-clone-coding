package velog.sideProject.exception.exhandler.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import velog.sideProject.exception.exception.VelogNotFoundException;
import velog.sideProject.exception.exhandler.ErrorResult;

@Slf4j
@RestControllerAdvice
public class globalControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<ErrorResult> velogNotFoundExceptionHandler(VelogNotFoundException e) {
        log.info("velogNotFoundExceptionHandler");
        ErrorResult errorResult = new ErrorResult(HttpStatus.BAD_REQUEST.toString(), e.getMessage(), getApiName());
        return new ResponseEntity<>(errorResult, HttpStatus.BAD_REQUEST);
    }

    // 현재 요청된 API 이름을 얻는 메서드
    private String getApiName() {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes != null) {
            return attributes.getRequest().getRequestURI();
        }
        return "Unknown API";
    }
}
