package hello;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@ApplicationScoped
@Path("")
public class HelloService {

    @GET
    @Path("/hello")
    public String hello() {
        return "Hello World!";
    }

    @GET
    @Path("/textException")
    @Produces(MediaType.TEXT_PLAIN)
    public Object textException() {
        throw new RuntimeException("This is an exception within text/plain");
    }

    @GET
    @Path("/jsonException")
    @Produces(MediaType.APPLICATION_JSON)
    public Object jsonException() {
        throw new RuntimeException("This is an exception within application/json");
    }

    @GET
    @Path("/xmlException")
    @Produces(MediaType.APPLICATION_XML)
    public Object xmlException() {
        throw new RuntimeException("This is an exception within application/xml");
    }

    @GET
    @Path("/pdfException")
    @Produces("application/pdf")
    public Object pdfException() {
        throw new RuntimeException("This is an exception within application/pdf");
    }

}
