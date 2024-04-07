package web.dao;

import javax.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

@Repository
public class UserDaoImp implements UserDao{
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.persist(user);
    }

    @Override
    public List<User> getAllUsers() {
//        EntityManager entityManager = entityManagerFactory.createEntityManager();
        TypedQuery<User> query = entityManager.createQuery("from User", User.class);
        return query.getResultList();
    }

    @Override
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public void updateUserById(long id, User updatedUser) {
        User dataUser = entityManager.find(User.class, id);

        dataUser.setName(updatedUser.getName());
        dataUser.setSurname(updatedUser.getSurname());
        dataUser.setAge(updatedUser.getAge());

        User mergedUser = entityManager.merge(dataUser);
    }

    @Override
    public void removeUserById(long id) {
        User removedUser = entityManager.find(User.class, id);
        entityManager.remove(removedUser);
    }
}
