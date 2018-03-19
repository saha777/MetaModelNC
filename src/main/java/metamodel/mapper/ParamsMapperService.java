package metamodel.mapper;

import metamodel.dao.models.Params;
import metamodel.dao.models.Role;

import java.util.Map;

public interface ParamsMapperService {
    Map<String, Object> getParamsMap(Integer objectId, Role role);
    Map<String, Params> getParamsMap(Integer objectId);
}
