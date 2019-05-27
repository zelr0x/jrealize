# jrealize
Java annotation-based serializer
##### Note: only CSV is supported for now
Created just for the sake of practicing with custom annotations and reflection


## API
Annotate your class' fields that have to be serialized with appropriate format e.g. `@Csv`

`int col()` annotation parameter has to be provided for CSV:
```Java
@Csv(col = 1)
private String firstName;
```
If the type of the field is primitive (e.g. `int`, `char`, etc.) or `String`, no further actions are needed. Other types may have inappropriate `toString()` implementations so there is one more step - implement a representation method with no parameters that returns String (like `toString()`) and annotate it with getter:
```Java
@CsvGetter
public String get() {
    return value;
}
```
If you decide to override `toString()` and use it as a representation method, don't annotate any other method with getter - annotated getters have the highest priority. Annotating `toString()` itself is redundant as it is used as a fallback when no getter is provided. Only one annotated getter per class is allowed. Otherwise you'll get a RuntimeException with a message about multiple getters.
```Java
@CsvGetter // <- redundant
@Override
public String toString() {
    return value;
}
```

To initiate a serialization process, call static methods like `Serializer.writeCsv()` with according parameters. For example, if `Employee` class has properly annotated fields, the following will write serialized version of the list `employees` into file `./csv/employees.csv`
```Java
final var employees = List.of(
        new Employee("John", "Doe", Position.RANK1, new PhoneNumber("01234567890")),
        new Employee("Jane", "Doe", Position.RANK2, new PhoneNumber("09876543210"))
);
final var dir = "csv";
final var filename = "employees.csv";
Serializer.writeCsv(employees, dir, filename);
```

Default working directory is `"user.dir"` (the folder in which the program is ran). It can be overridden by creating an instance of Serializer and passing new folder as a parameter to its constructor. To initiate the serialization with an instance, call non-static methods like `serializerInstance.csv()`:
```Java
final var employees = List.of(
        new Employee("John", "Doe", Position.RANK1, new PhoneNumber("01234567890")),
        new Employee("Jane", "Doe", Position.RANK2, new PhoneNumber("09876543210"))
);
final var wd = "csv";
final var serializer = new Serializer(wd);
serializer.csv(employees, filename);
```

