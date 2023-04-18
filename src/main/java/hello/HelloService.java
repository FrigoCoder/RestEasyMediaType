package hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;

@ApplicationScoped
@Path("")
public class HelloService {

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GET
    @Path("/exceptional")
    @Produces("application/pdf")
    public Object exceptional() {
        throw new RuntimeException("Whoopsie");
    }

}
