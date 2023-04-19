package exception;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;

@Produces(MediaType.TEXT_PLAIN)
@Provider
public class ThrowableTextWriter implements MessageBodyWriter<Throwable> {

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return Throwable.class.isAssignableFrom(clazz);
    }

    @Override
    public void writeTo(Throwable entity, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) {
        try (PrintStream ps = new PrintStream(outputStream)) {
            entity.printStackTrace(ps);
        }
    }

}
