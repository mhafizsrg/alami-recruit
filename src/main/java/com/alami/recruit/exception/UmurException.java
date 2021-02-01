package com.alami.recruit.exception;

public class UmurException extends RuntimeException {

    public UmurException() {
        super(MaskedException.UMUR_LIMIT.getLabel());
    }
}
