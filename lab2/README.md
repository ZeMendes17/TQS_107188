# Notes

## Mocking Dependencies (with Mockito)

In this lab we will use **JUnit5** in conjunction with **Mockito** that allow us to do Mock Injections.

### What are Mocking Dependencies?

Mocking Dependencies are used to "fake" (mock) the behaviour of a Class when needed. It is very useful while testing since it allows to mock the behaviour of a Class that:

- is not yet available
- the data we are trying to get varies a lot
- the response is slow (has latency)
- it has costs related (API's with costs per request)

### How to use them with Mockito

Using **Mockito** we can import the *@Mock* and *@InjectMocks* annotations that allows us to inject mocking dependencies. To use them we need to use the *@ExtendWith(MockitoExtension.class)* annotation in the Testing Class.

- *@Mock* is used on the Class that we want to mock
- *@InjectMocks* is used in the Class that depends on the Class that we are mocking

#### How to Load the Mock with the proper Expectations

Now we have to mock the behaviour expected from the mocked Class. This is done by copying what the class should returned if it was in use. To do this we use the *when* and *thenReturn* methods provided by Mockito. Example:

```java
when(stockmarketService.lookUpPrice("TSLA")).thenReturn(4.0);
when(stockmarketService.lookUpPrice("NVDA")).thenReturn(2.5);
```
 In this example we are mocking the behaviour of the stock market that has a high variability. When looking for a stock price we will get the values present in the *thenReturn*.
 When the method *stockmarketService.lookUpPrice* is called for that stock it will return the mocked value.

#### Verify the use of the mock

To verify how the mock was used and if it was used correctly we can use the *verify()* that Mockito offers. Example:

```java
verify(stockmarketService, times(2)).lookUpPrice(anyString());
```

In this example we verify if the *lookUpPrice()* method was called exactly 2 times, which should return True.

## Integration Tests (with the failsafe plugin)

In **Integration Tests** we use the real implementation of the modules in the test, not the mocks.

### How to Create Integration Tests

We need to create this tests in a diferent package from the Unit tests (inside the test package of course). Also the Test needs to end in **"IT"** (for example: "AdressResolverIT") indicating that it is an integration test.

### How to Run

To run the integration tests we cannot run the same command as for the unit tests. If we do the following command:

```bash
$ mvn test
```

This will only run the Unit Tests. To run the integration tests with this plugin we do:

```bash
$ mvn install failsafe:integration-test
```

This will run all the tests, thus it takes more time to complete. First it runs the Unit Tests and then the Integration Tests.
