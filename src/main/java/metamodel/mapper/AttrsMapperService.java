package metamodel.mapper;

import metamodel.dao.models.Attrs;

import java.util.Map;

public interface AttrsMapperService {
    Map<String, Attrs> getAttrsMap(Integer typeId);
}
