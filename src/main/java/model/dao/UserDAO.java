package model.dao;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import lombok.Getter;
import model.entity.User;

@Stateless
public class UserDAO extends AbstractDAO<User> {
    @Getter @PersistenceContext(unitName="socialdb")
    private EntityManager entityManager;

    public UserDAO() {
        super(User.class);
    }

    public List<User> findCarsMatchingName() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
