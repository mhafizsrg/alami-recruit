package com.alami.recruit.exception;

public class ParamException extends RuntimeException {

    public ParamException() {
        super(MaskedException.PARAM_EMPTY.getLabel());
    }
}
