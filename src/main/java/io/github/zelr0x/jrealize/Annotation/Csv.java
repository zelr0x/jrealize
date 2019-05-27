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
}
