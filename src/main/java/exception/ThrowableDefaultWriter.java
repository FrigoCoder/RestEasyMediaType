package exception;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import lombok.SneakyThrows;

@Produces(MediaType.WILDCARD)
@Provider
public class ThrowableDefaultWriter implements MessageBodyWriter<Throwable> {

    @Inject
    private ObjectMapper objectMapper;

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return Throwable.class.isAssignableFrom(clazz);
    }

    @Override
    @SneakyThrows
    public void writeTo(Throwable entity, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) {
        multivaluedMap.remove(HttpHeaders.CONTENT_TYPE);
        multivaluedMap.putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        objectMapper.writeValue(outputStream, entity);
    }

}
