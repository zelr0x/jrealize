package io.github.zelr0x.jrealize.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies field for JSON serialization.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface Json {
    /**
     * The name of the JSON field (overrides the name of the field in a class).
     * @return the name of the field
     */
    String name() default "";
}
