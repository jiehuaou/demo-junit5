
# spring boot unit test

use **@RunWith(SpringRunner.class) or @RunWith(MockitoJUnitRunner.class)**
when you are using Junit version < 5,

use **@ExtendWith(SpringExtension.class) or @ExtendWith(MockitoExtension.class)**
If you are using Junit version = 5,

```java
@RunWith(MockitoJUnitRunner.class) // JUnit 4
// @ExtendWith(MockitoExtension.class) for JUnit 5
public class SomeManagerTest {}
```

use **@SpringBootTest** if you need to bootstracpe the whole spring boot, which is very slow.

# Difference between @Mock and @InjectMocks

- @Mock creates a mock implementation for the classes you need.
- @InjectMock creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.

While unit testing our business logic, in this example the SomeManager, it does not matter which application framework we use. We can achieve this mocking behavior using @Mock whether we use Spring Boot or any other framework like Jakarta EE, Quarkus, Micronaut, Helidon, etc.

```java
@RunWith(MockitoJUnitRunner.class) // JUnit 4
// @ExtendWith(MockitoExtension.class) for JUnit 5
public class SomeManagerTest {

    @InjectMocks
    private SomeManager someManager;

    @Mock
    private SomeDependency someDependency; // this will be injected into someManager
 
     // tests...
     @Test
    public void attackWithSwordTest()  {
        Mockito.when(someDependency.getData()).thenReturn("hello");
        
        // someManager will invoke someDependency.getData()
        assertEquals("hello", someManager.showData());
    }

}
```

# Using @MockBean 
we can create a custom Spring Context for our test. Most of the time we either populate the full Spring Context (@SpringBootTest) or use a sliced context (e.g. @WebMvcTest or @DataJpaTest).  
```java
@WebMvcTest(StockController.class)
class StockControllerTest {
 
  @MockBean
  private StockService stockService;
 
  @Autowired
  private MockMvc mockMvc;
 
  @Test
  void shouldReturnStockPriceFromService()  {
    when(stockService.getLatestPrice("AMZN"))
      .thenReturn(BigDecimal.TEN);
 
    this.mockMvc
      .perform(get("/api/stocks?stockCode=AMZN"))
      .andExpect(status().isOk());
  }
}
```
The @MockBean annotation is part of Spring Test and will place a mock of type StockService inside the Spring Test Context. We can then define the behavior of this mock using the well-known Mockito stubbing setup: when().thenReturn().

You can use this annotation whenever our test deals with a Spring Context.

# Custom Validation
use ProxyFactory with MethodBeforeAdvice (MyValidationAdvice),
 to trigger the validation
```java
public class MyValidationAdvice implements MethodBeforeAdvice {}
```

# normal Validation
```java
@Service
@Validated
public class MyService {
    public String doSomething(@Valid CatalogueItem c){
        return "Valid";
    }
}
```

# @AutoConfigureMockMvc vs @WebMvcTest

## @AutoConfigureMockMvc, 
the full Spring application context is started but without the server. need to work with @SpringBootTest.
```java
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerHeavyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage()  {
        this.mockMvc.perform(get("/hello/333")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}

```
    
## @WebMvcTest, 
can narrow the tests to only the web layer, you need to inject the dependency bean via @MockBean.
```java
@WebMvcTest(controllers = {HelloController.class})
public class HelloControllerLightTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService;

    @Test
    public void shouldReturnDefaultMessage()  {
        // when
        Mockito.when(helloService.greet("333")).thenReturn("Hello, 333");
        // then
        this.mockMvc.perform(get("/hello/333")).andDo(print()).andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}
```

# heavy way to test controller
the full Spring application context is started but without the server.
```java
@SpringBootTest
@AutoConfigureMockMvc
public class HelloControllerHeavyTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage()  {
        this.mockMvc.perform(get("/hello/333"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}
```

# light way to test controller
only load web layer, but you need to inject the dependency bean.
```java
@WebMvcTest(controllers = {HelloController.class})
public class HelloControllerLightTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HelloService helloService; // controller depends on this service

    @Test
    public void shouldReturnDefaultMessage()  {
        // when
        Mockito.when(helloService.greet("333")).thenReturn("Hello, 333");
        // then
        this.mockMvc.perform(get("/hello/333"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Hello, 333")));
    }
}
```

# MockMvc and WebTestClient

__MockMvc__ isn't loaded for the __WebFlux__ configuration in Spring Boot. You need to use __WebTestClient__ instead. 

So replace AutoConfigureMockMvc with AutoConfigureWebTestClient and utilize the the webTestClient methods in its place.


# ConditionalOnProperty and Testing


@Configuration
```java
...
@Bean(name = "my-repository")
@ConditionalOnProperty(name = "new.config.use", havingValue = "true")
public MyRepository getRepository1(SubRepository subRepository){
        return new MyRepositoryNew(subRepository);
        }

@Bean(name = "my-repository")
@ConditionalOnProperty(name = "new.config.use", havingValue = "false")
public MyRepository getRepository2(SubRepository subRepository){
        return new MyRepositoryOld(subRepository);
        }
```
For testing with Autowire, use TestPropertySource to specify condition property 
```java
@ExtendWith({ SpringExtension.class })
@ContextConfiguration(classes = TestConfig.class)
@TestPropertySource(properties = {"new.config.use=false"})
public class TestRepoOld {

	@Autowired
	@Qualifier("my-repository")
	private MyRepository repo;

	@Test
	void testOldRepo() {
		String text = repo.hello();
		Assertions.assertEquals("Old->world", text);
	}
}
```