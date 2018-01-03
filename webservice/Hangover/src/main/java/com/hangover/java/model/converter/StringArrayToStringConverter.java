package com.hangover.java.model.converter;

import org.apache.commons.lang.StringUtils;

import javax.persistence.AttributeConverter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ashqures
 * Date: 8/26/16
 * Time: 1:38 PM
 * To change this template use File | Settings | File Templates.
 */
public class StringArrayToStringConverter implements AttributeConverter<ArrayList,String> {

    @Override
    public String convertToDatabaseColumn(ArrayList arrayList) {
        return arrayList == null ? null : StringUtils.join(arrayList, ",");
    }

    @Override
    public ArrayList<String> convertToEntityAttribute(String dbData) {
        ArrayList<String> data = null;//new ArrayList<String>();
        if (!StringUtils.isBlank(dbData)){
            data =  new ArrayList<String>(Arrays.asList(dbData.split(",")));
        }
       return data;
    }
}
