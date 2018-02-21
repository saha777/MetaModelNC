package metamodel.service;

import metamodel.dao.models.*;
import java.util.List;

public interface MetaModelService {
    List<Objects> findObjectsByTypeId(Integer typeId, Role role);

    List<Objects> findObjectsByTypeName(String typeName, Role role);

    List<Objects> findObjectsByParentId(Integer parentId, Role role);

    Objects findObjectByObjectId(Integer objectId, Role role);

    ObjectTypes findObjectTypeByTypeName(String typeName);

    Integer saveObject(Objects object, Role role);

    void saveParams(List<Params> params, Role role);

    void updateObject(Objects object, Role role);

    void updateParams(List<Params> params, Role role);

    void updateParams(Params param, Role role);

    void deleteParamsByObjectId(Integer objectId, Role role);

    void deleteObjectById(Integer objectId, Role role);
}
