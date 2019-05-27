package io.github.zelr0x.EmployeePojo;

import io.github.zelr0x.jrealize.Serializer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.List;
import java.util.stream.IntStream;

public class SerializerTest {
    private final static List<Employee> EMPLOYEES = List.of(
            new Employee("John", "Doe",
                    Position.RANK1,
                    new PhoneNumber("01234567890")),
            new Employee("Jane", "Doe",
                    Position.RANK2,
                    new PhoneNumber("09876543210"))
    );
    private final static String FILENAME = "employees.csv";
    private final static String DEFAULT_DIR = "csv";

    @Before
    public void setUp() {
        final var file = new File(DEFAULT_DIR, FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    @Test
    public void writeCsv() {
        final var file = new File(DEFAULT_DIR, FILENAME);
        Assert.assertFalse(file.exists());
        Assert.assertEquals(file.length(), 0);

        Serializer.writeCsv(EMPLOYEES, DEFAULT_DIR, FILENAME);
        final var length = file.length();
        Assert.assertNotEquals(length, 0);

        final var repeats = 100;
        IntStream.range(0, repeats).forEach(x ->
                Serializer.writeCsv(EMPLOYEES, DEFAULT_DIR, FILENAME));
        Assert.assertEquals(file.length(), length * (repeats + 1));
    }
}
