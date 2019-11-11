/**
 * 
 */
package duke.learn.elibrary.repository;

import java.util.List;

import duke.learn.elibrary.model.User;

/**
 * @author Kazi
 *
 */
public interface UserRepository {

    User addUpdateUser(User user);

    User getUser(Integer userId);

    User getUser(String username);

    List<User> getUsers();

    void deleteUser(Integer userId);

}
