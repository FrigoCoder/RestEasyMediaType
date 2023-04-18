package exception;

import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;
import jakarta.ws.rs.ext.Provider;
import lombok.SneakyThrows;

@Provider
public class ThrowableWriter implements MessageBodyWriter<Throwable> {

    @Inject
    private ObjectMapper objectMapper;

    @Inject
    private XmlMapper xmlMapper;

    @Override
    public boolean isWriteable(Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType) {
        return Throwable.class.isAssignableFrom(clazz);
    }

    @Override
    public void writeTo(Throwable entity, Class<?> clazz, Type type, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) {
        if (mediaType.toString().contains(MediaType.TEXT_PLAIN)) {
            textPlain(entity, outputStream);
        } else if (mediaType.toString().contains(MediaType.APPLICATION_JSON)) {
            applicationJson(entity, outputStream);
        } else if (mediaType.toString().contains(MediaType.APPLICATION_XML)) {
            applicationXml(entity, outputStream);
        } else {
            defaultMediaType(entity, multivaluedMap, outputStream);
        }
    }

    @SneakyThrows
    private void textPlain(Throwable entity, OutputStream outputStream) {
        try (PrintStream ps = new PrintStream(outputStream)) {
            entity.printStackTrace(ps);
        }
    }

    @SneakyThrows
    private void applicationJson(Throwable entity, OutputStream outputStream) {
        objectMapper.writeValue(outputStream, entity);
    }

    @SneakyThrows
    private void applicationXml(Throwable entity, OutputStream outputStream) {
        xmlMapper.writeValue(outputStream, entity);
    }

    @SneakyThrows
    private void defaultMediaType(Throwable entity, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) {
        multivaluedMap.remove(HttpHeaders.CONTENT_TYPE);
        multivaluedMap.putSingle(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        objectMapper.writeValue(outputStream, entity);
    }

}
