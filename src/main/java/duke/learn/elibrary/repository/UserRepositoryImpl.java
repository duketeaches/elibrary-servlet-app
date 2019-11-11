/**
 * 
 */
package duke.learn.elibrary.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import duke.learn.elibrary.model.Person;
import duke.learn.elibrary.model.User;

/**
 * @author Kazi
 *
 */
public class UserRepositoryImpl implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserRepositoryImpl.class);
    private Connection connection;

    public UserRepositoryImpl() {

	connection = ConnectionProvider.getConnection();
    }

    @Override
    public User addUpdateUser(User user) {
	try {
	    if (user != null) {
		Person person = user.getPerson();
		addUpdatePerson(person);
		Integer userId = user.getUserId();
		if (userId != null) {
		    // update - because user id is created during insert
		    String sql = "update user set username=?, password=?, email=? where user_id=?";
		    PreparedStatement statement = connection.prepareStatement(sql);
		    statement.setString(1, user.getUsername());
		    statement.setString(2, user.getPassword());
		    statement.setString(3, user.getEmail());
		    statement.setInt(4, userId);
		    int count = statement.executeUpdate();
		    LOGGER.info("Updated " + count + " User row with data: " + user);
		} else {
		    // Insert a new record
		    String sql = "insert into user (person_id, username, password, email) values (?,?,?,?)";
		    PreparedStatement statement = connection.prepareStatement(sql,
			    PreparedStatement.RETURN_GENERATED_KEYS);
		    statement.setInt(1, person.getPersonId());
		    statement.setString(2, user.getUsername());
		    statement.setString(3, user.getPassword());
		    statement.setString(4, user.getEmail());
		    user.setPersonId(person.getPersonId());
		    int count = statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
		    userId = rs.next() ? rs.getInt(1) : 0;
		    user.setUserId(userId);
		    LOGGER.info("Inserted " + count + " user with data: " + user);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return user;

    }

    private Person addUpdatePerson(Person person) {
	try {
	    if (person != null) {
		Integer personId = person.getPersonId();
		if (personId != null) {
		    // update
		    String query = "update person set name=?, age=?, contact_number=? where person_id = ?";
		    PreparedStatement statement = connection.prepareStatement(query,
			    PreparedStatement.RETURN_GENERATED_KEYS);
		    statement.setString(1, person.getName());
		    statement.setInt(2, person.getAge());
		    statement.setString(3, person.getContactNumber());
		    statement.setInt(4, personId);
		    int count = statement.executeUpdate();
		    LOGGER.info("Updated " + count + " person row with data: " + person);
		} else {
		    // inserts
		    String query = "insert into person (name, age, contact_number) values (?,?,?)";
		    PreparedStatement statement = connection.prepareStatement(query,
			    PreparedStatement.RETURN_GENERATED_KEYS);
		    statement.setString(1, person.getName());
		    statement.setInt(2, person.getAge());
		    statement.setString(3, person.getContactNumber());
		    int count = statement.executeUpdate();
		    ResultSet rs = statement.getGeneratedKeys();
		    personId = rs.next() ? rs.getInt(1) : 0;
		    person.setPersonId(personId);
		    LOGGER.info("Inserted " + count + " person with data: " + person);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return person;
    }

    @Override
    public User getUser(Integer userId) {
	User user = null;
	try {
	    if (userId != null) {
		String query = "Select * from user where user_id=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, userId);
		ResultSet rs = statement.executeQuery();
		List<User> users = convertToUsers(rs, true);
		if (users != null && !users.isEmpty()) {
		    user = users.get(0);
		}
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return user;
    }

    @Override
    public User getUser(String username) {
	User user = null;
	try {
	    if (username != null) {
		String query = "Select * from user where username=?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, username);
		ResultSet rs = statement.executeQuery();
		List<User> users = convertToUsers(rs, true);
		if (users != null && !users.isEmpty()) {
		    user = users.get(0);
		}
	    }
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	return user;
    }

    @Override
    public List<User> getUsers() {
	List<User> users = null;
	try {
	    String query = "Select * from user  ";
	    PreparedStatement statement = connection.prepareStatement(query);
	    ResultSet rs = statement.executeQuery();
	    users = convertToUsers(rs, true);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	return users;
    }

    @Override
    public void deleteUser(Integer userId) {
	try {
	    if (userId != null) {
		PreparedStatement preparedStatement = connection.prepareStatement("delete from user where user_id= ?");
		preparedStatement.setInt(1, userId);
		int count = preparedStatement.executeUpdate();
		LOGGER.info("Deleted " + count + " user row");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private List<User> convertToUsers(ResultSet rs, boolean withPerson) throws SQLException {
	List<User> users = new ArrayList<>();
	while (rs.next()) {
	    Integer userId = rs.getInt("user_id");
	    Integer personId = rs.getInt("person_id");
	    String username = rs.getString("username");
	    String password = rs.getString("password");
	    String email = rs.getString("email");
	    boolean admin = rs.getBoolean("admin");
	    Person person = null;
	    if (withPerson) {
		PreparedStatement statement = connection.prepareStatement("select * from person where person_id=?");
		statement.setInt(1, personId);
		ResultSet ps = statement.executeQuery();
		List<Person> persons = convertToPerson(ps);
		if (persons != null && !persons.isEmpty()) {
		    person = persons.get(0);
		}
	    }
	    users.add(new User(userId, personId, person, username, password, email, admin));
	}
	return users;
    }

    private List<Person> convertToPerson(ResultSet rs) throws SQLException {
	List<Person> persons = new ArrayList<>();
	while (rs.next()) {
	    Integer personId = rs.getInt("person_id");
	    String name = rs.getString("name");
	    Integer age = rs.getInt("age");
	    String contactNumber = rs.getString("contact_number");
	    persons.add(new Person(personId, name, age, contactNumber));
	}
	return persons;
    }

    public static void main(String[] args) {
	UserRepositoryImpl repo = new UserRepositoryImpl();
	// Person person = new Person(null, "test2fsdfdsafsadf", 23, "4423");
	// User user = new User(null, null, person, "tes44545te24434", "rest2",
	// "sdadfasfsadfasf");
	// new UserRepositoryImpl().addUpdateUser(user);
	// new UserRepositoryImpl().deleteUser(18);
	User user = repo.getUser(6);
	System.out.println(user);

    }

}
