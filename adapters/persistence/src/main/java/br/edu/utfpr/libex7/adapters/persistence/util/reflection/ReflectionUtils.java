package br.edu.utfpr.libex7.adapters.persistence.util.reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReflectionUtils {

    public static List<Field> getFields(Class clazz, Class<? extends Annotation> annotationClass){
        List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
        return   fields.stream()
                .filter(f -> f.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }
}
