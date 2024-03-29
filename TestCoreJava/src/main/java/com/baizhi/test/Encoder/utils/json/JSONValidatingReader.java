package com.baizhi.test.Encoder.utils.json;

public class JSONValidatingReader extends JSONReader {
    public static final Object INVALID = new Object();
    private JSONValidator validator;

    public JSONValidatingReader(JSONValidator validator) {
        this.validator = validator;
    }

    public JSONValidatingReader(JSONErrorListener listener) {
        this(new JSONValidator(listener));
    }

    public JSONValidatingReader() {
        this((JSONErrorListener)(new StdoutStreamErrorListener()));
    }

    public Object read(String string) {
        return !this.validator.validate(string) ? INVALID : super.read(string);
    }
}
