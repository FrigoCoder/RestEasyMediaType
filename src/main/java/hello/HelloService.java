package hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@ApplicationScoped
@Path("")
public class HelloService {

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello World!";
    }

}
