package metamodel.service;

import metamodel.dao.AttrsDao;
import metamodel.dao.ParamsDao;
import metamodel.dao.models.Attrs;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.List;
import java.util.Map;

public interface MetaModelService {
    Map<String, Attrs> getAttrsMap(Integer typeId, Integer grant);

    Map<String, Object> getParamsMap(Integer typeId, Integer grant);

    List<Objects> findObjectsByTypeName(String typeName, Integer grant);

    List<Objects> findObjectsByParentId(Integer parentId, Integer grant);

    Objects findObjectByObjectId(Integer objectId, Integer grant);

    ObjectTypes findObjectTypeByTypeName(String typeName);

    Integer saveObject(Objects object, Integer grant);

    void saveParams(List<Params> params, Integer grant);

    void updateObject(Objects object, Integer grant);

    void updateParams(List<Params> params, Integer grant);

    void updateParams(Params param, Integer grant);

    void deleteParamsByObjectId(Integer objectId, Integer grant);

    void deleteObjectById(Integer objectId, Integer grant);
}
