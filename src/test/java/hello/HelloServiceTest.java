package hello;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class HelloServiceTest {

    @Test
    void hello_test() {
        HelloService hello = new HelloService();
        Assertions.assertEquals("Hello World!", hello.hello());
    }

}
