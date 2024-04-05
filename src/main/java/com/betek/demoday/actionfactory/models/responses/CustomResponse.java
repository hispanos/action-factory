package com.betek.demoday.actionfactory.models.responses;

import org.springframework.http.HttpStatus;

public class CustomResponse<T> {
    private HttpStatus status;
    private T data;
    private ApiError error;

    private CustomResponse(HttpStatus status, T data) {
        this.status = status;
        this.data = data;
        this.error = null;
    }

    private CustomResponse(HttpStatus status, String errorMessage) {
        this.status = status;
        this.data = null;
        this.error = new ApiError(errorMessage);
    }

    public static <T> CustomResponse<T> success(T data) {
        return new CustomResponse<>(HttpStatus.OK, data);
    }

    public static <T> CustomResponse<T> error(HttpStatus status, String errorMessage) {
        return new CustomResponse<>(status, errorMessage);
    }

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ApiError getError() {
        return error;
    }

    public void setError(ApiError error) {
        this.error = error;
    }
}
