/**
 * 
 */
package duke.learn.elibrary.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duke.learn.elibrary.model.Person;
import duke.learn.elibrary.model.User;
import duke.learn.elibrary.repository.UserRepository;
import duke.learn.elibrary.repository.UserRepositoryImpl;

/**
 * @author Kazi
 *
 */
public class UserService {

    private UserRepository userRepository;

    public UserService() {
	userRepository = new UserRepositoryImpl();
    }

    public void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession();
	String username = req.getParameter("username");
	String password = req.getParameter("password");
	boolean success = false;
	// validate username and password from database
	if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
	    User user = userRepository.getUser(username);
	    if (user != null) {
		if (user.getPassword().equals(password)) {
		    session.setAttribute("user", user);
		    session.removeAttribute("message");
		    success = true;
		    // We use redirect to prevent form re-submission after post request
		    resp.sendRedirect("home");
		}
	    }
	}
	if (!success) {
	    session.setAttribute("message", "Invalid Username password");
	    resp.sendRedirect("home");
	}
    }

    public void getUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String userId = req.getParameter("userId");
	Integer uId = userId != null && !userId.isEmpty() ? Integer.parseInt(userId) : null;
	if (uId != null) {
	    User user = userRepository.getUser(uId);
	    req.setAttribute("user", user);
	}
	req.getRequestDispatcher("home").forward(req, resp);
    }

    public void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String name = req.getParameter("name");
	String email = req.getParameter("email");
	String contactNumber = req.getParameter("contactNumber");
	String ageString = req.getParameter("age");
	String username = req.getParameter("username");
	String password = req.getParameter("password");
	Integer age = null;
	if (ageString != null && !ageString.isEmpty()) {
	    age = Integer.parseInt(ageString);

	}
	Person person = new Person(null, name, age, contactNumber);
	User user = new User(null, null, person, username, password, email, false);
	userRepository.addUpdateUser(user);
	resp.sendRedirect("home");

    }

}
