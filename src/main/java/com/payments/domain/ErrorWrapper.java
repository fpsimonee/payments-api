package com.payments.domain;

public class ErrorWrapper {

    private Error error;

    public ErrorWrapper(Error error) {
        this.error = error;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }
}