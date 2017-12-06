
import config.DaoConfig;
import config.MetaModelConfig;
import metamodel.dao.ObjectTypesDao;
import metamodel.dao.models.ObjectTypes;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DaoConfig.class, MetaModelConfig.class);

        List<ObjectTypes> objectTypes = context.getBean(ObjectTypesDao.class).findAll();

        for (ObjectTypes obj : objectTypes){
            System.out.println(obj.toString());
        }
    }
}
