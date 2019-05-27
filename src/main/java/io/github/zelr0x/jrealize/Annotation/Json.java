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
    /**
     * Specifies if toString() should be used. Without this parameter,
     * every non-primitive non-String field type requires a method
     * marked with @JsonGetter annotation.
     * @return true if toString() should be used to retrieve a field's
     * value instead of @JsonGetter
     */
    boolean useToString() default false;
}
