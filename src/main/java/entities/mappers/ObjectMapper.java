package entities.mappers;

import entities.AbstractObject;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.Map;

public interface ObjectMapper<T> {
    T mapObject(Objects objects, Map<String, Params> paramsMap);
    T mapObject(MetaObject metaObject);
    MetaObject getMetaObject(AbstractObject object);
}
