package jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

import jakarta.enterprise.inject.Produces;

public class ObjectMapperProducer {

    @Produces
    public ObjectMapper objectMapper() {
        return JsonMapper.builder().enable(SerializationFeature.INDENT_OUTPUT).disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).build();
    }

}
