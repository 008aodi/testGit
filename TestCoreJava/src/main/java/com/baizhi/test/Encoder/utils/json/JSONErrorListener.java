package com.baizhi.test.Encoder.utils.json;

public interface JSONErrorListener {
    void start(String var1);

    void error(String var1, int var2);

    void end();
}
