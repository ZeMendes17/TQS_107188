# Notes

## JUnit 5

In this first lab, we will use JUnit 5 to write unit tests for Java Projects, and we will use Maven as a build tool.

### Key Points

- Unit testing is when you (as a programmer) write test code to verify units of (production) code. A unit is a small-scoped, coherent subset of a much larger solution. A true “unit” should not depend on the behavior of other (collaborating) modules.

- Unit tests help the developers to (i) understand the module contract (what to construct); (ii) document the intended use of a component; (iii) prevent regression errors; (iv) increase confidence in the code.

- JUnit and TestNG are popular frameworks for unit testing in Java.

### How to use JUnit 5

#### 1. Add the JUnit 5 dependency to the `pom.xml` file

```xml
<!-- JUnit 5 dependency for tests-->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.2</version>
    <scope>test</scope>
</dependency>
```

#### 2. Add the Maven Surefire and Failsafe plugins to the `pom.xml` file

```xml
<build>
    <plugins>
        <plugin>
            <artifactId>maven-surefire-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
        <plugin>
            <artifactId>maven-failsafe-plugin</artifactId>
            <version>3.1.2</version>
        </plugin>
    </plugins>
</build>
```

### How to write JUnit 5 tests

To write a JUnit 5 unit test, we need to follow these steps:

1. Since we are using Maven, we use the `src/test/java` directory to store our test classes;

2. We use the `@Test` annotation to mark a method as a test method;

3. We can use the `@BeforeEach` and `@AfterEach` annotations to mark methods that should be run before and after each test method;

4. We can use the `@BeforeAll` and `@AfterAll` annotations to mark methods that should be run before and after all test methods;

5. We can use the `@DisplayName` annotation to provide a custom display name for the test class or test method;

6. We can use the `@Disabled` annotation to disable a test class or test method;

7. In order to assert the expected result of a test, we use the `Assertions` class that can be imported from `org.junit.jupiter.api.Assertions`. This has method to check for equality, nullity, exceptions, if a condition is true/false, etc.