package dao.converter.reflections;

import entities.anotations.*;
import entities.anotations.ObjectId;
import org.reflections.Reflections;
import org.reflections.scanners.*;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;

import java.lang.reflect.Field;
import java.util.*;

public class Reflection<T> {

    public Map<String, Object> getFieldsMap(T object){
        Map<String, Object> fieldsMap = new HashMap<>(4);
        List<Field> paramsFields = new ArrayList<>();
        Field[] objectFields = object.getClass().getDeclaredFields();
        for(Field field : objectFields) {
            if(field.isAnnotationPresent(Param.class))
                paramsFields.add(field);
            else if(field.isAnnotationPresent(ObjectId.class))
                fieldsMap.put("ObjectId", field);
            else if(field.isAnnotationPresent(ParentId.class))
                fieldsMap.put("ParentId", field);
            else if(field.isAnnotationPresent(Name.class))
                fieldsMap.put("Name", field);
        }
        fieldsMap.put("Params", paramsFields);
        return fieldsMap;
    }

    public Object getFieldValue(T object, Field field){
        Object value = null;
        boolean accesible = field.isAccessible();
        field.setAccessible(true);
        try {
            value = field.get(object);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        field.setAccessible(accesible);
        return value;
    }

}
