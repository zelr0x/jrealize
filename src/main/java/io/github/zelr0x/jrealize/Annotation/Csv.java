package io.github.zelr0x.jrealize.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Specifies field for CSV serialization.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Csv {
    /**
     * Specifies CSV column number.
     * @return CSV column number
     */
    int col();

    /**
     * Specifies if toString() should be used. Without this parameter,
     * every non-primitive non-String field type requires a method
     * marked with @CsvGetter annotation.
     * @return true if toString() should be used to retrieve a field's
     * value instead of @CsvGetter
     */
    boolean useToString() default false;
}
