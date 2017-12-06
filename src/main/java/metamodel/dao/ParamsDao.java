package metamodel.dao;

import metamodel.dao.models.Attrs;
import metamodel.dao.models.Objects;
import metamodel.dao.models.Params;

import java.util.List;

public interface ParamsDao {
    List<Params> findAll();

    List<Params> findByObjects(Objects object);

    List<Params> findByObjects(Integer objectId);

    List<Params> findByObjectsAndAttrs(Objects object, Attrs attr);

    List<Params> findByObjectsAndAttrs(Integer objectId, Integer attrId);

    Params findOneByObjectsAndAttrs(Integer objectId, Integer attrId);

    void deleteByObjectId(Integer objectId);

    void save(List<Params> params);

    void save(Params object, Integer objectId);

    void save(Params object);

    void update(Params param);

    void update(List<Params> params);
}
