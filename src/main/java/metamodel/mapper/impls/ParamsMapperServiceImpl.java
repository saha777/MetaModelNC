package metamodel.mapper.impls;

import metamodel.dao.AttrsDao;
import metamodel.dao.GrantsDao;
import metamodel.dao.ParamsDao;
import metamodel.dao.models.Attrs;
import metamodel.dao.models.Params;
import metamodel.dao.models.Role;
import metamodel.mapper.ParamsMapperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParamsMapperServiceImpl implements ParamsMapperService {
    private ParamsDao paramsDao;
    private AttrsDao attrsDao;
    private GrantsDao grantsDao;

    @Autowired
    public ParamsMapperServiceImpl(ParamsDao paramsDao, AttrsDao attrsDao, GrantsDao grantsDao) {
        this.paramsDao = paramsDao;
        this.attrsDao = attrsDao;
        this.grantsDao = grantsDao;
    }

    // remake
    @Override
    public Map<String, Object> getParamsMap(Integer objectId, Role role) {
        List<Params> params = paramsDao.findByObjects(objectId);
        Map<String, Object> paramsMap = new HashMap<>();

        for (Params param : params) {

            if (!grantsDao.isReadableAttr(role, objectId, param.getAttrId())) continue;

            //Attrs attr = attrsDao.findById(param.getAttrId());

            if (paramsMap.containsKey(param.getAttrName())) {

                Object temp = paramsMap.get(param.getAttrName());

                List<Params> paramValues;

                if (temp instanceof List) {
                    paramValues = (List<Params>) temp;
                } else {
                    paramValues = new ArrayList<>();
                    paramValues.add((Params) temp);
                }

                paramValues.add(param);
                paramsMap.put(param.getAttrName(), paramValues);

                continue;
            }

            paramsMap.put(param.getAttrName(), param);
        }

        return paramsMap;
    }
}
