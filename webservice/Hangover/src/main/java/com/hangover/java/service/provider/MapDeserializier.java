package com.hangover.java.service.provider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/1/16
 * Time: 10:30 PM
 * To change this template use File | Settings | File Templates.
 */
public class MapDeserializier extends JsonDeserializer<Map<String, String>> {


    private final Logger logger = LoggerFactory.getLogger(MapDeserializier.class);

    @Override
    public Map<String, String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {

        Map<String, String> map = new HashMap<String, String>();
        try {
            logger.info("Inside deserialize");
            ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
            String json =  mapper.readTree(jsonParser).toString();
            map = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
            logger.info("instJson-" + json);
            //XStream xstream = new XStream(new JettisonMappedXmlDriver());
            //xstream.registerConverter(new InetAddressConverter());
            //Object obj = xstream.fromXML(json);
            //baseEntity.setInst((BaseEntity)obj);
        } catch (Exception e) {
            logger.error("deserialize", e, true);
        }
        return map;
    }
}

