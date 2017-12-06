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

    private Reflections getReflections() {
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        return new Reflections(new ConfigurationBuilder()
                .setScanners(
                        new SubTypesScanner(false /* don't exclude Object.class */),
                        new ResourcesScanner(),
                        new MethodAnnotationsScanner(),
                        new FieldAnnotationsScanner(),
                        new TypeAnnotationsScanner()
                )
                .setUrls(
                        ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0]))
                )
                .filterInputsBy(
                        new FilterBuilder().include(FilterBuilder.prefix("entities"))
                )
        );
    }

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

//    public Class<?> getClassStructure(ObjectType objectType){
//        Reflections reflections = getReflections();
//
//        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(EntityType.class);
//
//        Iterator<Class<?>> iterator = classes.iterator();
//        Class<?> eClass = this.getClass();
//
//        while(iterator.hasNext()) {
//            Class<?> curClass = iterator.next();
//            EntityType entityType = curClass.getAnnotation(EntityType.class);
//            if(entityType.type() == objectType)
//                eClass = curClass;
//        }
//        return eClass;
//    }

}
