package dao.converter.impls;

import dao.converter.Converter;
import metamodel.dao.models.Attrs;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;
import dao.converter.reflections.Reflection;
import entities.anotations.*;
import metamodel.service.MetaModelService;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Field;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ConverterImpl<T> implements Converter<T> {
    @Autowired
    private MetaModelService metaModelService;
    private Reflection<T> reflection;

    public ConverterImpl() {
        reflection = new Reflection<>();
    }

    @Override
    public List<Params> convertTemplateToParams(T object, Integer objectTypeId, Map<String, Attrs> attrsMap) {
        List<Params> params = new ArrayList<>();

        Map<String, Object> fieldsMap = reflection.getFieldsMap(object);
        Objects objects = convertTemplateToObjects(object, objectTypeId, fieldsMap);

        for(Field field : (List<Field>) fieldsMap.get("Params")){
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            String attrName = field.getAnnotation(Param.class).name();
            Type objType = field.getAnnotation(Param.class).type();
            Count count = field.getAnnotation(Param.class).count();
            Attrs attr = attrsMap.get(attrName);

            try {
                switch (count) {
                    case ONE:
                        params.add(convertFieldValueToParam(objects.getObjectId(), attr.getAttrId(), objType, field.get(object)));
                        break;
                    case MANY:
                        params.addAll(convertFieldValuesToListParams(objects.getObjectId(), attr.getAttrId(), objType, field.get(object)));
                        break;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

            field.setAccessible(accessible);
        }
        return params;
    }



    @Override
    public Objects convertTemplateToObjects(T object, Integer objectTypeId) {
        return convertTemplateToObjects(object, objectTypeId, reflection.getFieldsMap(object));
    }

    private Objects convertTemplateToObjects(T object, Integer objectTypeId, Map<String, Object> fieldsMap) {
        Field objectIdField = (Field) fieldsMap.get("ObjectId");
        Field parentIdField = (Field) fieldsMap.get("ParentId");
        Field nameField = (Field) fieldsMap.get("Name");

        Objects objects = new Objects();
        objects.setType(objectTypeId);
        objects.setObjectId((Integer) reflection.getFieldValue(object, objectIdField));
        objects.setParentObjectId((Integer) reflection.getFieldValue(object, parentIdField));
        objects.setName((String) reflection.getFieldValue(object, nameField));
        //objects = objectsDao.findById((Integer) reflection.getFieldValue(object, field));

        return objects;
    }

    @Override
    public T convertDataToTemplate(Objects object, Map<String, Object> paramsMap, Class<T> templateClass) {
        try {
            Object template = templateClass.newInstance();

            Field[] fields = templateClass.getDeclaredFields();

            for (Field field : fields) {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);

                if (field.isAnnotationPresent(ObjectId.class)) {
                    field.set(template, object.getObjectId());
                } else if (field.isAnnotationPresent(ParentId.class)) {
                    field.set(template, object.getParentObjectId());
                } else if (field.isAnnotationPresent(Name.class)) {
                    field.set(template, object.getName());
                } else if (field.isAnnotationPresent(Param.class) && paramsMap.size() != 0) {
                    String attrName = field.getAnnotation(Param.class).name();
                    Type objType = field.getAnnotation(Param.class).type();
                    Count count = field.getAnnotation(Param.class).count();

                    switch (count){
                        case ONE:
                            field.set(template, convertParamToFieldValue(objType, (Params) paramsMap.get(attrName)));
                            break;
                        case MANY:
                            List<Params> list;
                            if(paramsMap.get(attrName) instanceof List) {
                                list = (List<Params>) paramsMap.get(attrName);
                            } else {
                                list = new ArrayList<>(1);
                                list.add((Params) paramsMap.get(attrName));
                            }
                            field.set(template, convertParamsToFieldValues(objType, list));
                            break;
                    }
                }

                field.setAccessible(accessible);
            }
            return  (T) template;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return null;
    }

    private Params convertFieldValueToParam(Integer objId, Integer attrId, Type valueType, Object value) {
        Params params = new Params();
        params.setObjectId(objId);
        params.setAttrId(attrId);
        switch (valueType) {
            case NUMBER:
                params.setNumberValue((Integer) value);
                break;
            case TEXT:
                params.setTextValue((String) value);
                break;
            case DATE:
                params.setDateValue((Date) value);
                break;
        }
        return params;
    }

    private List<Params> convertFieldValuesToListParams(Integer objId, Integer attrId, Type valueType, Object list){
        List<Params> params = new ArrayList<>();
        for(Object object : (List) list){
            params.add(convertFieldValueToParam(objId, attrId, valueType, object));
        }
        return params;
    }

    private Object convertParamToFieldValue(Type type, Params param){
        Object value = null;
        switch (type){
            case NUMBER:
                value = (param == null ? null : param.getNumberValue());
                break;
            case TEXT:
                value = (param == null ? null : param.getTextValue());
                break;
            case DATE:
                value = (param == null ? null : param.getDateValue());
                break;
        }
        return value;
    }

    private Object convertParamsToFieldValues(Type type, List<Params> params){
        List<Object> values = new ArrayList<>();
        for(Params param : params){
            values.add(convertParamToFieldValue(type, param));
        }
        return values;
    }
}
