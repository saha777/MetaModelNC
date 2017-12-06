package dao;

import config.DaoConfig;
import config.MetaModelConfig;
import metamodel.dao.models.Params;
import metamodel.dao.ParamsDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.lang.reflect.Member;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MetaModelConfig.class})
public class ParamsDaoImplTest {
    @Autowired
    private ParamsDao dao;

    @Test
    public void findAllTest(){
        List<Params> params = dao.findAll();

        for(Params param : params){
            System.out.println(param.toString());
        }
    }
}
