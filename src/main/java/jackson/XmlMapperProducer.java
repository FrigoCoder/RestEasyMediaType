package jackson;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import jakarta.enterprise.inject.Produces;
import jakarta.enterprise.inject.Typed;

public class XmlMapperProducer {

    @Produces
    @Typed(XmlMapper.class)
    public XmlMapper xmlMapper() {
        return new XmlMapper();
    }

}
