package dao;

import config.MetaModelConfig;
import metamodel.dao.models.Objects;
import metamodel.dao.ObjectsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MetaModelConfig.class})
public class ObjectsDaoImplTest {

    @Autowired
    private ObjectsDao objectsDao;

    @Test
    public void findAllTest() {
        List<Objects> objects = objectsDao.findAll();

        for (Objects obj : objects){
            System.out.println(obj.toString());
        }
    }

}
