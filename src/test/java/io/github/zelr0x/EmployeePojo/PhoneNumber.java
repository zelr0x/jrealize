package io.github.zelr0x.EmployeePojo;

import io.github.zelr0x.jrealize.Annotation.CsvGetter;

public class PhoneNumber {
    private String value;

    public PhoneNumber(final String value) {
        this.value = value;
    }

    @CsvGetter
    public String get() {
        return value;
    }
}
