package com.baizhi.AES;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface EncryptField {
    String[] includes() default {};

    boolean encrypt() default true;

    boolean global() default false;

    String encryptKey() default "";

    boolean common() default true;
}
