package io.github.zelr0x.jrealize.Annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Annotations utilities.
 */
public final class Util {
    private static final long MAX_GETTERS = 1;
    private static final String ILLEGAL_ACCESS_MESSAGE =
            "Unknown error. Please report the details to the maintainer";

    /**
     * Prevents instantiation.
     */
    private Util() {
        throw new AssertionError();
    }

    /**
     * Retrieves all fields marked with a specified annotation
     * from a specified object's class.
     * @param obj an object in which class to gather fields
     * @param annotation an annotation to look for in fields of the obj
     * @return Stream of fields of obj annotated with annotation
     */
    public static Stream<Field> getAnnotatedFields(final Object obj,
            final Class<? extends Annotation> annotation) {
        return Arrays.stream(obj.getClass().getDeclaredFields())
                .peek(f -> f.setAccessible(true))
                .filter(f -> f.isAnnotationPresent(annotation));
    }

    /**
     * Retrieves a value suitable for serialization from a specified field
     * of a specified object.
     * @param obj an instance of a class that has a specified field
     * @param field a field which value to retrieve
     * @param getter a getter annotation to look for in the class of the obj
     * @return a value of the field of obj
     */
    public static String fieldToString(final Object obj, final Field field,
            final Class<? extends Annotation> getter) {
        Object fieldValue;
        try {
            fieldValue = field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new IllegalAccessError(ILLEGAL_ACCESS_MESSAGE);
        }

        while (!isSerializable(fieldValue)) {
            fieldValue = serialize(fieldValue, getter);
        }
        return fieldValue.toString();
    }

    /**
     * Invoke a method of a target marked with a specified annotation
     * and return the result.
     * @param target an object to use as implicit parameter of method
     *               invocation i.e. as "this"
     * @param annotation an annotation to look for in methods of the target
     * @return the result of method invocation on the target
     */
    private static Object serialize(final Object target,
            final Class<? extends Annotation> annotation) {
        if (isSerializable(target)) return target.toString();

        final var annotatedMethods = getAnnotatedMethods(target, annotation)
                .toArray(Method[]::new);

        final var annotatedMethodCount = annotatedMethods.length;
        if (annotatedMethodCount > MAX_GETTERS) {
            throw new RuntimeException("Class can only have "
                    + MAX_GETTERS
                    + " methods marked with serialization getter annotation");
        } else if (annotatedMethodCount == 0) return target.toString();

        return invoke(target, annotatedMethods[0]);
    }

    /**
     * Returns a stream of methods marked with a specified annotation
     * in a class of provided object.
     * @param o an object in which class to find annotated methods
     * @param annotation an annotation to look for
     * @return a stream of methods annotated with annotation
     */
    private static Stream<Method> getAnnotatedMethods(final Object o,
            final Class<? extends Annotation> annotation) {
        return Arrays.stream(o.getClass()
                .getDeclaredMethods())
                .filter(m -> m.isAnnotationPresent(annotation));
    }

    /**
     * Invoke a method of a target and return the result.
     * @param target an object to use as implicit parameter of method
     *               invocation i.e. as "this"
     * @param method a method to call on the target
     * @return the result of method invocation on the target
     */
    private static Object invoke(final Object target, final Method method) {
        Object res;
        try {
            res = method.invoke(target);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            throw new IllegalAccessError(ILLEGAL_ACCESS_MESSAGE);
        }
        return res;
    }

    /**
     * Checks if a field contains value suitable for serialization.
     * A value is considered suitable for serialization if it at least one of:
     * - of primitive type
     * - of type String
     * @param obj an object to check
     * @return true if the value is primitive or String
     */
    private static boolean isSerializable(final Object obj) {
        final var clazz = obj.getClass();
        return clazz.isPrimitive() || clazz.equals(String.class);
    }
}
