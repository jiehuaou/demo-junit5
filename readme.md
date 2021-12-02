
# spring boot unit test

use **@RunWith(SpringRunner.class) or @RunWith(MockitoJUnitRunner.class)**
when you are using Junit version < 5,

use **@ExtendWith(SpringExtension.class) or @ExtendWith(MockitoExtension.class)**
If you are using Junit version = 5,

use **@SpringBootTest** if you need to bootstracpe the whole spring boot, which is very slow.

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