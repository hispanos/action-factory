package com.betek.demoday.actionfactory.exceptions;

public class ApiException extends RuntimeException {

        private int statusCode;

        public ApiException(String message) {
            super(message);
        }

        public ApiException(int statusCode, String message) {
            super(message);
            this.statusCode = statusCode;
        }

        public int getStatusCode() {
            return statusCode;
        }
}
