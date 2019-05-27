package io.github.zelr0x.jrealize;

import io.github.zelr0x.jrealize.Annotation.Csv;
import io.github.zelr0x.jrealize.Annotation.CsvGetter;
import io.github.zelr0x.jrealize.Annotation.Util;

import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Functional interface responsible for serialization.
 */
@FunctionalInterface
public interface Formatter {
    String DEFAULT_DELIMITER = ",";

    /**
     * Serialize object in appropriate format.
     * @param o an object to serialize
     * @return a String representation of that object
     */
    String format(Object o);

    /**
     * Factory of functions conforming to Formatter functional interface.
     * @param format Format to use
     * @param delimiter delimiter to use (for delimited formats)
     * @return appropriate formatter function
     */
    static Formatter getFormatter(final Format format,
                                  final String delimiter) {
        return (format == Format.CSV)
                ? obj -> toCsvString(obj, delimiter)
                : getFormatter(format);
    }

    /**
     * Factory of functions conforming to Formatter functional interface.
     * @param format Format to use
     * @return appropriate formatter function
     */
    static Formatter getFormatter(final Format format) {
        switch (format) {
            case JSON:
                return Formatter::toJsonString;
            case XML:
                return Formatter::toXmlString;
            default:
                return obj -> toCsvString(obj, DEFAULT_DELIMITER);
        }
    }

    /**
     * Represents all values suitable for serialization from a specified object
     * as a csv string separated with a specified delimiter.
     * @param obj an object in which to gather values
     * @param delimiter a delimiter to use
     * @return csv string representation of an object
     */
    private static String toCsvString(final Object obj,
                                      final String delimiter) {
        return getCsvFieldValues(obj)
                .collect(Collectors
                        .joining(delimiter))
                + System.lineSeparator();
    }

    /**
     * Retrieves all values suitable for serialization from a specified object.
     * @param obj an object in which to gather values
     * @return Stream of values of fields marked with @Csv annotation
     */
    private static Stream<String> getCsvFieldValues(final Object obj) {
        return Util.getAnnotatedFields(obj, Csv.class)
                .sorted(Comparator.comparingInt(
                        f -> f.getAnnotation(Csv.class).col()))
                .map(f -> Util.fieldToString(obj, f, CsvGetter.class));
    }

    /**
     * Represents all values suitable for serialization from a specified object
     * as a json string.
     * @param obj an object in which to gather values
     * @return json string representation of an object
     */
    private static String toJsonString(final Object obj) {
        // add JSON joining with commas between objects
        // instead of line separator
        return getJsonFieldValues(obj)
                .collect(Collectors.joining(","))
                + System.lineSeparator();
    }

    /**
     * Retrieves all values suitable for serialization from a specified object.
     * @param obj an object in which to gather values
     * @return Stream of values of fields marked with @Json annotation
     */
    private static Stream<String> getJsonFieldValues(final Object obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Represents all values suitable for serialization from a specified object
     * as a json string.
     * @param obj an object in which to gather values
     * @return xml string representation of an object
     */
    private static String toXmlString(final Object obj) {
        throw new UnsupportedOperationException();
    }

    /**
     * Retrieves all values suitable for serialization from a specified object.
     * @param obj an object in which to gather values
     * @return Stream of values of fields marked with @Xml annotation
     */
    private static Stream<String> getXmlFieldValues(final Object obj) {
        throw new UnsupportedOperationException();
    }
}
