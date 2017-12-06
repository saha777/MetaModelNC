package dao;

import config.MetaModelConfig;
import metamodel.dao.models.ObjectTypes;
import metamodel.dao.ObjectTypesDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MetaModelConfig.class})
public class ObjectTypesDaoImplTest {

    @Autowired
    private ObjectTypesDao dao;

    @Before
    public void daoIsNullTest(){
        System.out.println(dao == null ? "dao is null" : "dao is not null");
    }

    @Test
    public void findAllTest() {
        List<ObjectTypes> objectTypes = dao.findAll();

        for (ObjectTypes obj : objectTypes){
            System.out.println(obj.toString());
        }
    }

    @Test
    public void deleteByName() {
        dao.deleteByName("Department");
    }

    @Test
    public void deleteById() {

    }

    @Test
    public void save() {
        ObjectTypes type = new ObjectTypes();
        //type.setTypeId(8);
        type.setName("Department");
        dao.save(type);
    }

    @Test
    public void update() {

    }
}
