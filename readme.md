
# spring boot unit test

use **@RunWith(SpringRunner.class) or @RunWith(MockitoJUnitRunner.class)**
when you are using Junit version < 5,

use **@ExtendWith(SpringExtension.class) or @ExtendWith(MockitoExtension.class)**
If you are using Junit version = 5,

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
    public void attackWithSwordTest() throws Exception {
        Mockito.when(someDependency.getData()).thenReturn("hello");

        assertEquals("someManager will invoke someDependency.getData()", someManager.showData());
    }

}
```

use **@SpringBootTest** if you need to bootstracpe the whole spring boot, which is very slow.

# Difference between @Mock and @InjectMocks

- @Mock creates a mock implementation for the classes you need.
- @InjectMock creates an instance of the class and injects the mocks that are marked with the annotations @Mock into it.

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
