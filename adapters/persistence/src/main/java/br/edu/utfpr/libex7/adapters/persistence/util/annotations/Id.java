package br.edu.utfpr.libex7.adapters.persistence.util.annotations;

import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

    String columnName() default "";
}
