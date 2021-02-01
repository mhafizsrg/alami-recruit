package com.alami.recruit.exception;

public class GajiException extends RuntimeException {

    public GajiException() {
        super(MaskedException.GAJI_MINUS.getLabel());
    }
}
