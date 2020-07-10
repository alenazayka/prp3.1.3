package web.dao;

import org.springframework.security.core.userdetails.UserDetails;
import web.model.Role;
import web.model.User;

import java.util.List;
import java.util.Set;

public interface UserDao {
    List<User> getAllUsers();
    List<Role> getAllRoles();
    Set<Role> getRoles(String[] ids);
    void add(User user);
    void update(User user);
    User getAllUsers(Long id);
    void delete(Long id);
    public UserDetails getUserByLogin(String username);
/*
    void deleteAll();
*/

}
