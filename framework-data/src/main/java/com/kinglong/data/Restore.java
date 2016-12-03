package com.kinglong.data;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Restore
 *
 * @author lanjl
 * @version 2016/12/2
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Restore {

    public final static String USE_DEFAULT_NAME = "";

    public String value() default USE_DEFAULT_NAME;
}
