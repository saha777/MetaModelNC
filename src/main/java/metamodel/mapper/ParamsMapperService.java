package metamodel.mapper;

import metamodel.dao.models.Role;

import java.util.Map;

public interface ParamsMapperService {
    Map<String, Object> getParamsMap(Integer objectId, Role role);
}
