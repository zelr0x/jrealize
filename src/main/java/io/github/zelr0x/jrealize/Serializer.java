package io.github.zelr0x.jrealize;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Collection;
import java.util.stream.Stream;

/**
 * Serializes objects and writes them to files.
 */
public class Serializer {
    private static final String WORKING_DIR = System.getProperty("user.dir");

    private final String workingDir;

    /**
     * Constructs new Serializer object which allows to set working directory.
     * Working directory is a directory in which all generated files and
     * directories will be placed. Serializer works just fine with only
     * static methods but in such case the working directory is set to user.dir
     * - the directory in which the program is ran. user.dir can be overridden
     * with -Duser.dir JVM flag.
     * @param workingDir a directory to use as a working directory
     */
    public Serializer(final String workingDir) {
        this.workingDir = workingDir;
    }

    /**
     * Writes given collection of objects to a specified file.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     * @param delimiter String to use as a delimiter - common choices
     *                  for CSV are a comma (default) and a tabulation ("\t")
     */
    public static void writeCsv(final Collection<?> collection,
            final String directory, final String filename,
            final Charset charset, final String delimiter) {
        write(collection, Formatter.getFormatter(Format.CSV, delimiter),
                WORKING_DIR, directory, filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     * @param delimiter String to use as a delimiter - common choices
     *                  for CSV are a comma (default) and a tabulation ("\t")
     */
    public void csv(final Collection<?> collection, final String directory,
            final String filename, final Charset charset,
            final String delimiter) {
        write(collection, Formatter.getFormatter(Format.CSV, delimiter),
                workingDir, directory, filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param delimiter String to use as a delimiter - common choices
     *                  for CSV are a comma (default) and a tabulation ("\t")
     */
    public static void writeCsv(final Collection<?> collection,
            final String directory, final String filename,
            final String delimiter) {
        write(collection, Formatter.getFormatter(Format.CSV, delimiter),
                WORKING_DIR, directory, filename, StandardCharsets.UTF_8);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param delimiter String to use as a delimiter - common choices
     *                  for CSV are a comma (default) and a tabulation ("\t")
     */
    public void csv(final Collection<?> collection, final String directory,
                    final String filename, final String delimiter) {
        write(collection, Formatter.getFormatter(Format.CSV, delimiter),
                workingDir, directory, filename, StandardCharsets.UTF_8);
    }

    /**
     * Writes given collection of objects to a specified file.
     * Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     */
    public static void writeCsv(final Collection<?> collection,
            final String directory, final String filename,
            final Charset charset) {
        write(collection, Formatter.getFormatter(Format.CSV),
                WORKING_DIR, directory, filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     */
    public void csv(final Collection<?> collection, final String directory,
                    final String filename, final Charset charset) {
        write(collection, Formatter.getFormatter(Format.CSV),
                workingDir, directory, filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset. Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     */
    public static void writeCsv(final Collection<?> collection,
            final String directory, final String filename) {
        write(collection, Formatter.getFormatter(Format.CSV),
                WORKING_DIR, directory, filename, StandardCharsets.UTF_8);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset. Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     */
    public void csv(final Collection<?> collection, final String directory,
                    final String filename) {
        write(collection, Formatter.getFormatter(Format.CSV),
                workingDir, directory, filename, StandardCharsets.UTF_8);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset. Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     * @param delimiter String to use as a delimiter - common choices
     *                  for CSV are a comma (default) and a tabulation ("\t")
     */
    public static void writeCsv(final Collection<?> collection,
            final String filename, final Charset charset,
            final String delimiter) {
        write(collection, Formatter.getFormatter(Format.CSV, delimiter),
                WORKING_DIR, "csv", filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset. Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     * @param delimiter String to use as a delimiter - common choices
     *                  for CSV are a comma (default) and a tabulation ("\t")
     */
    public void csv(final Collection<?> collection, final String filename,
                    final Charset charset, final String delimiter) {
        write(collection, Formatter.getFormatter(Format.CSV, delimiter),
                workingDir, "csv", filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset. Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param filename name of the file in which to write the iterable
     */
    public static void writeCsv(final Collection<?> collection,
                                final String filename) {
        write(collection, Formatter.getFormatter(Format.CSV),
                WORKING_DIR, "csv", filename, StandardCharsets.UTF_8);
    }

    /**
     * Writes given collection of objects to a specified file.
     * UTF-8 is used as a charset. Comma is used as a CSV delimiter.
     * @param collection collection of objects to write
     * @param filename name of the file in which to write the iterable
     */
    public void csv(final Collection<?> collection, final String filename) {
        write(collection, Formatter.getFormatter(Format.CSV),
                workingDir, "csv", filename, StandardCharsets.UTF_8);
    }

    /**
     * Writes given collection of objects to a specified file.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     */
    private static void writeJson(final Collection<?> collection,
            final String directory, final String filename,
            final Charset charset) {
        write(collection, Formatter.getFormatter(Format.JSON),
                WORKING_DIR, directory, filename, charset);

    }

    /**
     * Writes given collection of objects to a specified file.
     * @param collection collection of objects to write
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the iterable
     * @param charset charset to use. Default is UTF-8
     */
    private void json(final Collection<?> collection, final String directory,
                      final String filename, final Charset charset) {
        write(collection, Formatter.getFormatter(Format.JSON),
                workingDir, directory, filename, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * @param <T> parent type of items in a collection
     * @param collection collection of objects to write
     * @param formatter a function used to serialize each object
     *                  in a specified collection
     * @param workingDirectory working directory to use
     * @param directory name of the directory in which all created
     *                  files will be placed. Default is working directory
     * @param filename name of the file in which to write the collection
     * @param charset charset to use. Default is UTF-8
     */
    private static <T> void write(final Collection<? extends T> collection,
            final Formatter formatter, final String workingDirectory,
            final String directory, final String filename,
            final Charset charset) {
        final var newDirectory = new File(workingDirectory, directory);
        if (!(newDirectory.exists() || newDirectory.mkdir())) {
            throw new Error("Cannot read directory "
                    + workingDirectory + File.separator + directory);
        }
        final var file = Paths.get(directory, filename);
        final var contents = collection.stream()
                .map(formatter::format);
        write(contents, file, charset);
    }

    /**
     * Writes given collection of objects to a specified file.
     * @param contents Stream of objects that have to be written to a file
     * @param filePath path in which to look for a file
     * @param charset charset to use. Default is UTF-8
     */
    private static void write(final Stream<String> contents,
            final Path filePath, final Charset charset) {
        ensureFilePath(filePath);
        try (var writer = Files.newBufferedWriter(filePath, charset,
                StandardOpenOption.APPEND)) {
            contents.forEach(str -> {
                try {
                    writer.append(str);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Checks if specified path leads to a file.
     * If it doesn't - creates that file.
     * @param filePath path in which to look for a file
     */
    private static void ensureFilePath(final Path filePath) {
        if (Files.notExists(filePath)) {
            try {
                Files.createFile(filePath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
