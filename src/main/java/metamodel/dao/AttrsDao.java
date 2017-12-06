package metamodel.dao;

import metamodel.dao.models.Attrs;
import metamodel.dao.models.ObjectTypes;

import java.util.List;

public interface AttrsDao {
    List<Attrs> findAll();

    List<Attrs> findByType(ObjectTypes type);

    List<Attrs> findByType(Integer typeId);

    Attrs findById(Integer attrId);

    Attrs findByTypeAndName(Integer type, String name);

    Attrs findByName(String name);

    void deleteById(Integer id);

    void save(Attrs object);

    void update(Attrs object);
}
