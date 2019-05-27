package io.github.zelr0x.jrealize.Annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks method as a serialization-getter for the class.
 * It is required to have exactly one such method in each user-defined
 * class if it's toString() is not suitable for serialization.
 * If not getter is specified, toString() will be used instead.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface JsonGetter {
}
