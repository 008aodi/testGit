package com.baizhi.test.Encoder.utils.json;

public class ExceptionErrorListener extends BufferErrorListener {
    public ExceptionErrorListener() {
    }

    public void error(String type, int col) {
        super.error(type, col);
        throw new IllegalArgumentException(this.buffer.toString());
    }
}
