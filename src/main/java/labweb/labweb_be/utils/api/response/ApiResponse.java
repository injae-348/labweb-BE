package labweb.labweb_be.utils.api.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import labweb.labweb_be.utils.exception.model.CustomException;
import labweb.labweb_be.utils.exception.model.ExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;

public record ApiResponse<T> (
        @JsonIgnore // 응답에 포함되지 않음 -> Interceptor에서 처리
        HttpStatus httpStatus,
        boolean success,
        @Nullable T data,
        @Nullable ExceptionDto error
) {

    /**
     * 성공적인 응답을 생성합니다. HTTP 200 OK 상태와 함께 데이터를 포함할 수 있습니다.
     *
     * @param data 응답 데이터
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> ok(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.OK, true, data, null);
    }

    /**
     * 성공적인 생성 응답을 생성합니다. HTTP 201 Created 상태와 함께 데이터를 포함할 수 있습니다.
     *
     * @param data 응답 데이터
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> created(@Nullable final T data) {
        return new ApiResponse<>(HttpStatus.CREATED, true, data, null);
    }

    /**
     * 요청이 수락되었지만 처리되지는 않은 응답을 생성합니다. HTTP 202 Accepted 상태를 사용합니다.
     *
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> accepted() {
        return new ApiResponse<>(HttpStatus.ACCEPTED, true, null, null);
    }

    /**
     * 요청이 성공적으로 처리되었지만 응답 데이터가 없는 경우를 나타냅니다. HTTP 204 No Content 상태를 사용합니다.
     *
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> noContent() {
        return new ApiResponse<>(HttpStatus.NO_CONTENT, true, null, null);
    }

    /**
     * 클라이언트 오류를 나타내는 응답을 생성합니다. HTTP 400 Bad Request 상태와 함께 오류 정보를 포함합니다.
     *
     * @param error 오류 정보
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> badRequest(@Nullable final ExceptionDto error) {
        return new ApiResponse<>(HttpStatus.BAD_REQUEST, false, null, error);
    }

    /**
     * 인증되지 않은 요청을 나타내는 응답을 생성합니다. HTTP 401 Unauthorized 상태와 함께 오류 정보를 포함합니다.
     *
     * @param error 오류 정보
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> unauthorized(@Nullable final ExceptionDto error) {
        return new ApiResponse<>(HttpStatus.UNAUTHORIZED, false, null, error);
    }

    /**
     * 권한이 없는 요청을 나타내는 응답을 생성합니다. HTTP 403 Forbidden 상태와 함께 오류 정보를 포함합니다.
     *
     * @param error 오류 정보
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> forbidden(@Nullable final ExceptionDto error) {
        return new ApiResponse<>(HttpStatus.FORBIDDEN, false, null, error);
    }

    /**
     * 커스텀 예외를 처리하여 실패 응답을 생성합니다. 예외의 HTTP 상태 코드와 함께 오류 정보를 포함합니다.
     *
     * @param e 커스텀 예외
     * @param <T> 응답 데이터의 타입
     * @return ApiResponse 객체
     */
    public static <T> ApiResponse<T> fail(final CustomException e) {
        return new ApiResponse<>(e.getErrorCode().getHttpStatus(), false, null, ExceptionDto.of(e.getErrorCode()));
    }

}
