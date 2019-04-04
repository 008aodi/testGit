package com.baizhi.test.Encoder.utils.json;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class JSONValidator {
    private JSONErrorListener listener;
    private CharacterIterator it;
    private char c;
    private int col;

    public JSONValidator(JSONErrorListener listener) {
        this.listener = listener;
    }

    public boolean validate(String input) {
        input = input.trim();
        this.listener.start(input);
        boolean ret = this.valid(input);
        this.listener.end();
        return ret;
    }

    private boolean valid(String input) {
        if ("".equals(input)) {
            return true;
        } else {
            boolean ret = true;
            this.it = new StringCharacterIterator(input);
            this.c = this.it.first();
            this.col = 1;
            if (!this.value()) {
                ret = this.error("value", 1);
            } else {
                this.skipWhiteSpace();
                if (this.c != '\uffff') {
                    ret = this.error("end", this.col);
                }
            }

            return ret;
        }
    }

    private boolean value() {
        return this.literal("true") || this.literal("false") || this.literal("null") || this.string() || this.number() || this.object() || this.array();
    }

    private boolean literal(String text) {
        CharacterIterator ci = new StringCharacterIterator(text);
        char t = ci.first();
        if (this.c != t) {
            return false;
        } else {
            int start = this.col;
            boolean ret = true;

            for(t = ci.next(); t != '\uffff'; t = ci.next()) {
                if (t != this.nextCharacter()) {
                    ret = false;
                    break;
                }
            }

            this.nextCharacter();
            if (!ret) {
                this.error("literal " + text, start);
            }

            return ret;
        }
    }

    private boolean array() {
        return this.aggregate('[', ']', false);
    }

    private boolean object() {
        return this.aggregate('{', '}', true);
    }

    private boolean aggregate(char entryCharacter, char exitCharacter, boolean prefix) {
        if (this.c != entryCharacter) {
            return false;
        } else {
            this.nextCharacter();
            this.skipWhiteSpace();
            if (this.c == exitCharacter) {
                this.nextCharacter();
                return true;
            } else {
                while(true) {
                    if (prefix) {
                        int start = this.col;
                        if (!this.string()) {
                            return this.error("string", start);
                        }

                        this.skipWhiteSpace();
                        if (this.c != ':') {
                            return this.error("colon", this.col);
                        }

                        this.nextCharacter();
                        this.skipWhiteSpace();
                    }

                    if (!this.value()) {
                        return this.error("value", this.col);
                    }

                    this.skipWhiteSpace();
                    if (this.c != ',') {
                        if (this.c == exitCharacter) {
                            this.nextCharacter();
                            return true;
                        }

                        return this.error("comma or " + exitCharacter, this.col);
                    }

                    this.nextCharacter();
                    this.skipWhiteSpace();
                }
            }
        }
    }

    private boolean number() {
        if (!Character.isDigit(this.c) && this.c != '-') {
            return false;
        } else {
            int start = this.col;
            if (this.c == '-') {
                this.nextCharacter();
            }

            if (this.c == '0') {
                this.nextCharacter();
                if (this.c == '.') {
                    this.nextCharacter();
                    if (Character.isDigit(this.c)) {
                        while(Character.isDigit(this.c)) {
                            this.nextCharacter();
                        }
                    }

                    return this.error("number", start);
                } else if (this.c != 'e' && this.c != 'E') {
                    return true;
                } else {
                    this.nextCharacter();
                    if (this.c == '+' || this.c == '-') {
                        this.nextCharacter();
                    }

                    if (Character.isDigit(this.c)) {
                        while(Character.isDigit(this.c)) {
                            this.nextCharacter();
                        }
                    }

                    return this.error("number", start);
                }
            } else {
                if (Character.isDigit(this.c)) {
                    while(Character.isDigit(this.c)) {
                        this.nextCharacter();
                    }
                }

                return this.error("number", start);
            }
        }
    }

    private boolean string() {
        if (this.c != '"') {
            return false;
        } else {
            int start = this.col;
            boolean escaped = false;
            this.nextCharacter();

            for(; this.c != '\uffff'; this.nextCharacter()) {
                if (!escaped && this.c == '\\') {
                    escaped = true;
                } else if (escaped) {
                    if (!this.escape()) {
                        return false;
                    }

                    escaped = false;
                } else if (this.c == '"') {
                    this.nextCharacter();
                    return true;
                }
            }

            return this.error("quoted string", start);
        }
    }

    private boolean escape() {
        int start = this.col - 1;
        if ("\\\"/bfnrtu".indexOf(this.c) < 0) {
            return this.error("escape sequence \\\",\\\\,\\/,\\b,\\f,\\n,\\r,\\t or \\uxxxx", start);
        } else {
            return this.c != 'u' || this.ishex(this.nextCharacter()) && this.ishex(this.nextCharacter()) && this.ishex(this.nextCharacter()) && this.ishex(this.nextCharacter()) ? true : this.error("unicode escape sequence \\uxxxx", start);
        }
    }

    private boolean ishex(char d) {
        return "0123456789abcdefABCDEF".indexOf(this.c) >= 0;
    }

    private char nextCharacter() {
        this.c = this.it.next();
        ++this.col;
        return this.c;
    }

    private void skipWhiteSpace() {
        while(Character.isWhitespace(this.c)) {
            this.nextCharacter();
        }

    }

    private boolean error(String type, int col) {
        if (this.listener != null) {
            this.listener.error(type, col);
        }

        return false;
    }
}

