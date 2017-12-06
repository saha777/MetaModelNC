package dao.converter;

import metamodel.dao.models.Attrs;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.List;
import java.util.Map;

public interface Converter<T> {

    List<Params> convertTemplateToParams(T object, Integer objectTypeId, Map<String, Attrs> attrsMap);

    Objects convertTemplateToObjects(T object, Integer objectTypeId);

    T convertDataToTemplate(Objects object, Map<String, Object> paramsMap, Class<T> templateClass);
}
