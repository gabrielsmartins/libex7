package br.edu.utfpr.libex7.adapters.persistence.util.annotations;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Table {
    String name() default "";
}
