package com.hangover.java.service.provider;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hangover.java.model.BaseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 6/1/16
 * Time: 9:44 PM
 * To change this template use File | Settings | File Templates.
 */
public class CustomDeserializer extends JsonDeserializer<BaseEntity> {


    private final Logger logger = LoggerFactory.getLogger(CustomDeserializer.class);
    @Override
    public BaseEntity deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException,
            JsonProcessingException {
        BaseEntity baseEntity = null;//new BaseEntity();
        try
        {
            logger.info("Inside deserialize");
            ObjectMapper mapper = (ObjectMapper) jsonParser.getCodec();
            String json =  mapper.readTree(jsonParser).toString();

            logger.info("instJson-" + json);
            /*XStream xstream = new XStream(new JettisonMappedXmlDriver());
            //xstream.registerConverter(new InetAddressConverter());
            Object obj = xstream.fromXML(json);*/
            //baseEntity.setInst((BaseEntity)obj);
        }
        catch(Exception e)
        {
            logger.error("deserialization", e, true);
        }
        return baseEntity;
    }
}
