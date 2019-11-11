/**
 * 
 */
package duke.learn.elibrary.model;

/**
 * @author Kazi
 *
 */
public class Person {

    private Integer personId;
    private String name;
    private Integer age;
    private String contactNumber;

    /**
     * 
     */
    public Person() {
	super();
    }

    /**
     * @param personId
     * @param name
     * @param age
     * @param contactNumber
     */
    public Person(Integer personId, String name, Integer age, String contactNumber) {
	super();
	this.personId = personId;
	this.name = name;
	this.age = age;
	this.contactNumber = contactNumber;
    }

    public Integer getPersonId() {
	return personId;
    }

    public void setPersonId(Integer personId) {
	this.personId = personId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Integer getAge() {
	return age;
    }

    public void setAge(Integer age) {
	this.age = age;
    }

    public String getContactNumber() {
	return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
	this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
	return "Person [personId=" + personId + ", name=" + name + ", age=" + age + ", contactNumber=" + contactNumber
		+ "]";
    }

}
