package com.baizhi.test.Encoder;

public class YlzEncoderException extends Exception {
    private static final long serialVersionUID = -238091758285157331L;
    private String errCode;
    private String errMsg;

    public YlzEncoderException() {
    }

    public YlzEncoderException(String message, Throwable cause) {
        super(message, cause);
    }

    public YlzEncoderException(String message) {
        super(message);
    }

    public YlzEncoderException(Throwable cause) {
        super(cause);
    }

    public YlzEncoderException(String errCode, String errMsg) {
        super(errCode + ":" + errMsg);
        this.errCode = errCode;
        this.errMsg = errMsg;
    }

    public String getErrCode() {
        return this.errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }
}