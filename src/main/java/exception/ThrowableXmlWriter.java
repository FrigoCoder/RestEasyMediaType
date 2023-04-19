package exception;

import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import lombok.SneakyThrows;

@Produces(MediaType.APPLICATION_XML)
@Provider
public class ThrowableXmlWriter implements MessageBodyWriter<Throwable> {

    @Inject
    private XmlMapper xmlMapper;

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return Throwable.class.isAssignableFrom(clazz);
    }

    @Override
    @SneakyThrows
    public void writeTo(Throwable entity, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) {
        xmlMapper.writeValue(outputStream, entity);
    }

}
