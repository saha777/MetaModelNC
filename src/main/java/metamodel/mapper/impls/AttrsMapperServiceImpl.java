package metamodel.mapper.impls;

import metamodel.dao.AttrsDao;
import metamodel.dao.models.Attrs;
import metamodel.mapper.AttrsMapperService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AttrsMapperServiceImpl implements AttrsMapperService {
    @Autowired
    private AttrsDao attrsDao;

    @Override
    public Map<String, Attrs> getAttrsMap(Integer typeId) {
        List<Attrs> attrs = attrsDao.findByType(typeId);
        Map<String, Attrs> attrsMap = new HashMap<>(4);

        for (Attrs attr : attrs) attrsMap.put(attr.getName(), attr);

        return attrsMap;
    }
}
