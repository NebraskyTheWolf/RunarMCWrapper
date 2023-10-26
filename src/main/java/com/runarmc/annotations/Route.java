package com.runarmc.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Route {
    String method();
    String route();

    boolean arguments() default false;

    Class result();
}
