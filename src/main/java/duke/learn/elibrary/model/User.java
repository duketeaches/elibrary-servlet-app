package duke.learn.elibrary.model;

/**
 * 
 * @author Kazi
 *
 */
public class User {

    private Integer userId;
    private Integer personId;
    private Person person;
    private String username;
    private String password;
    private String email;
    private boolean admin;

    /**
     * 
     */
    public User() {
	super();
    }

    /**
     * @param userId
     * @param personId
     * @param person
     * @param username
     * @param password
     * @param email
     * @param admin
     */
    public User(Integer userId, Integer personId, Person person, String username, String password, String email,
	    boolean admin) {
	super();
	this.userId = userId;
	this.personId = personId;
	this.person = person;
	this.username = username;
	this.password = password;
	this.email = email;
	this.admin = admin;
    }

    public Integer getUserId() {
	return userId;
    }

    public void setUserId(Integer userId) {
	this.userId = userId;
    }

    public Integer getPersonId() {
	return personId;
    }

    public void setPersonId(Integer personId) {
	this.personId = personId;
    }

    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    @Override
    public String toString() {
	return "User [userId=" + userId + ", personId=" + personId + ", person=" + person + ", username=" + username
		+ ", password=" + password + ", email=" + email + ", admin=" + admin + "]";
    }

    public boolean isAdmin() {
	return admin;
    }

    public void setAdmin(boolean admin) {
	this.admin = admin;
    }

}
