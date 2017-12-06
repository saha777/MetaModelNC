package metamodel.dao;


import metamodel.dao.models.ObjectTypes;

import java.util.List;

public interface ObjectTypesDao {
    List<ObjectTypes> findAll();

    ObjectTypes findByName(String name);

    ObjectTypes findById(Integer id);

    void deleteById(Integer id);

    void deleteByName(String name);

    void save(ObjectTypes type);

    void update(ObjectTypes type);
}
