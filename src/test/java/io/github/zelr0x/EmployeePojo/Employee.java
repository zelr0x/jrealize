package io.github.zelr0x.EmployeePojo;

import io.github.zelr0x.jrealize.Annotation.Csv;

public class Employee {
    @Csv(col = 1)
    private String firstName;
    @Csv(col = 2)
    private String lastName;
    @Csv(col = 3, useToString = true)
    private Position position;
    @Csv(col = 4)
    private PhoneNumber number;

    public Employee(final String firstName, final String lastName,
                    final Position position, final PhoneNumber number) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.number = number;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(final String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(final Position position) {
        this.position = position;
    }

    public PhoneNumber getNumber() {
        return number;
    }

    public void setNumber(final PhoneNumber number) {
        this.number = number;
    }
}
