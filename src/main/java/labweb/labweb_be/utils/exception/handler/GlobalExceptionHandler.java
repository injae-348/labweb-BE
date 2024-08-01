package labweb.labweb_be.utils.exception.handler;

import labweb.labweb_be.utils.api.response.ApiResponse;
import labweb.labweb_be.utils.exception.model.CustomException;
import labweb.labweb_be.utils.exception.model.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 존재 하지 않는 페이지 요청 시
    @ExceptionHandler(value = {NoHandlerFoundException.class, HttpRequestMethodNotSupportedException.class})
    public ApiResponse<?> handleNoPageFoundException(Exception e) {
        log.error("GlobalExceptionHandler.handleNoPageFoundException : {}", e.getMessage());
        return ApiResponse.fail(new CustomException(ErrorCode.NOT_FOUND_END_POINT));
    }

    // 커스텀 예외
    @ExceptionHandler(value = {CustomException.class})
    public ApiResponse<?> handleCustomException(CustomException e) {
        log.error("GlobalExceptionHandler.handleCustomException : {}", e.getMessage());
        return ApiResponse.fail(e);
    }

    // 기본 예외
    @ExceptionHandler(value = {Exception.class})
    public ApiResponse<?> handleException(Exception e) {
        log.error("GlobalExceptionHandler.handleException : {}", e.getMessage());
        e.printStackTrace();
        return ApiResponse.fail(new CustomException(ErrorCode.INTERNAL_SERVER_ERROR));
    }

}
