/**
 * 
 */
package duke.learn.elibrary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duke.learn.elibrary.service.UserService;

/**
 * @author Kazi
 *
 */
public class LoginServlet extends HttpServlet {

    private UserService userService;

    /**
     * 
     */
    public LoginServlet() {
	userService = new UserService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	HttpSession session = req.getSession();
	session.removeAttribute("message");
	req.getRequestDispatcher("pages/home.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	userService.login(req, resp);
    }
}
