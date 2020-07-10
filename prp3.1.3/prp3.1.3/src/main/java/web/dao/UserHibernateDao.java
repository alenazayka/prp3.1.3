package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class UserHibernateDao implements UserDao {

    private final EntityManager currentSession;

    public UserHibernateDao(EntityManager entityManager) {
        this.currentSession = entityManager;
    }

    @Override
    public List<User> getAllUsers() {
        return currentSession.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public List<Role> getAllRoles() {
        TypedQuery<Role> query= currentSession.createQuery("select r from Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Set<Role> getRoles(String[] ids) {
        TypedQuery<Role> query= currentSession.createQuery("select r from Role r where r.id = :id", Role.class);
        Set<Role> roles = new HashSet<>();
        Arrays.stream(ids).forEach(roleId -> {query.setParameter("id", Long.parseLong(roleId)); roles.add(query.getSingleResult());});
        return roles;
    }

    @Override
    public void add(User user) {
        currentSession.persist(user);
    }

    @Override
    public void update(User user) {
        currentSession.merge(user);
    }

    @Override
    public User getAllUsers(Long id) {
        TypedQuery<User> query= currentSession.createQuery("select u from User u where u.id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void delete(Long id) {
        currentSession.remove(getAllUsers(id));
    }

    @Override
    public UserDetails getUserByLogin(String login) {
        TypedQuery<User> query= currentSession.createQuery("select u from User u WHERE u.login= :login", User.class);
        query.setParameter("login", login);
        return query.getSingleResult();
    }


}
