package labweb.labweb_be.utils.api.interceptor;

import labweb.labweb_be.utils.api.response.ApiResponse;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class ResponseInterceptor implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(@NonNull MethodParameter returnType, @NonNull Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, @Nullable MediaType selectedContentType,
                                  @Nullable Class selectedConverterType, @NonNull ServerHttpRequest request,
                                  @NonNull ServerHttpResponse response) {
        if (returnType.getParameterType() == ApiResponse.class) {
            HttpStatus status = ((ApiResponse<?>) body).httpStatus();
            response.setStatusCode(status);
        }
        return body;
    }
}
