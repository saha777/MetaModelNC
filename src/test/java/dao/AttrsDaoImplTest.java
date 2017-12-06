package dao;

import config.DaoConfig;
import config.MetaModelConfig;
import metamodel.dao.models.Attrs;
import metamodel.dao.AttrsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MetaModelConfig.class})
public class AttrsDaoImplTest {

    @Autowired
    private AttrsDao dao;

    @Test
    public void findAllTest(){
        List<Attrs> attrs = dao.findAll();

        for (Attrs attr : attrs){
            System.out.println(attr.toString());
        }
    }
}
