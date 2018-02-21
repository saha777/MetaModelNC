package metamodel.dao.impls;

import config.DaoConfig;
import metamodel.dao.GrantsDao;
import metamodel.dao.models.Role;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfig.class})
public class GrantsDaoImplTest {

    @Autowired
    private GrantsDao grantsDao;

    @Test
    public void isReadableObjTest() {
        Role role = grantsDao.findById(2);
        Integer objId = 8;
        Integer attrId = 32;

        Assert.assertTrue(grantsDao.isReadableObj(role, objId));
        Assert.assertFalse(grantsDao.isReadableObj(role, 256));
        Assert.assertFalse(grantsDao.isReadableAttr(role, objId, attrId));
        Assert.assertTrue(grantsDao.isReadableAttr(role, 58, 2));
    }

    @Test
    public void isWritableObjTest() {
        Role role = grantsDao.findById(2);
        Integer objId = 8;
        Integer attrId = 32;

        Assert.assertFalse(grantsDao.isWritableObj(role, objId));
        Assert.assertFalse(grantsDao.isWritableObj(role, 256));
        Assert.assertFalse(grantsDao.isWritableAttr(role, objId, attrId));
        Assert.assertFalse(grantsDao.isWritableAttr(role, 58, attrId));
        Assert.assertFalse(grantsDao.isWritableAttr(role, 58, 3));
    }

}
