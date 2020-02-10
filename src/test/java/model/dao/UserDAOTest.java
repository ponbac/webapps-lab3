package model.dao;

import javax.ejb.EJB;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import model.entity.User;

@RunWith(Arquillian.class)
public class UserDAOTest {
    @Deployment
    public static WebArchive createDeployment() {
        return ShrinkWrap.create(WebArchive.class)
            .addClasses(UserDAO.class, User.class)
            .addAsResource("META-INF/persistence.xml")
            .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @EJB
    private UserDAO userDAO;

    @Before
    public void init() {
        userDAO.create(new User("IFF780", "Renault Clio", "Image1"));
        userDAO.create(new User("LTP520", "Volvo 760GT", "Image2"));
        userDAO.create(new User("XOL345", "Isuzu Traga", "Image3"));
    }

    @Test
    public void checkThatFindCarsMatchingNameMatchesCorrectly() {
        Assert.assertTrue(true); /* Some better condition */
    }
}
