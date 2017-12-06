package metamodel.dao;


import metamodel.dao.models.ObjectTypes;
import metamodel.dao.models.Objects;

import java.util.List;

public interface ObjectsDao {
    List<Objects> findAll();

    List<Objects> findByParentId(Integer parentId);

    List<Objects> findByType(ObjectTypes type);

    List<Objects> findByType(Integer typeId);

    Objects findById(Integer objectId);

    Objects findByName(String name);

    void deleteById(Integer id);

    Integer save(Objects object);

    void update(Objects object);
}
