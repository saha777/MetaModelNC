package metamodel.service;

import metamodel.dao.models.*;
import java.util.List;

public interface MetaModelService {
    List<Objects> findObjectsByTypeId(Integer typeId);

    List<Objects> findObjectsByTypeName(String typeName);

    List<Objects> findObjectsByParentId(Integer parentId);

    Objects findObjectByObjectId(Integer objectId);

    ObjectTypes findObjectTypeByTypeName(String typeName);

    Integer saveObject(Objects object);

    void saveParams(List<Params> params);

    void updateObject(Objects object);

    void updateParams(List<Params> params);

    void updateParams(Params param);

    void deleteParamsByObjectId(Integer objectId);

    void deleteObjectById(Integer objectId);
}
