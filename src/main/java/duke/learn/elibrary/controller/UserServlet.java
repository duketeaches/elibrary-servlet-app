/**
 * 
 */
package duke.learn.elibrary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import duke.learn.elibrary.service.UserService;

/**
 * @author Kazi
 *
 */
public class UserServlet extends HttpServlet {

    private static final long serialVersionUID = 5705889481736688447L;

    private UserService userService;

    /**
     * 
     */
    public UserServlet() {
	userService = new UserService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	userService.addUser(req, resp);
    }
}
