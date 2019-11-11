/**
 * 
 */
package duke.learn.elibrary.controller;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import duke.learn.elibrary.model.User;

/**
 * @author Kazi
 *
 */
public class AuthenticationFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	    throws IOException, ServletException {
	// check whether user is logged in
	HttpServletRequest httpReuest = (HttpServletRequest) request;
	HttpServletResponse httpServletResponse = (HttpServletResponse) response;

	HttpSession session = httpReuest.getSession(false);

	boolean authenticated = false;
	if (session != null) {
	    User user = (User) session.getAttribute("user");
	    if (user != null) {
		authenticated = true;
		chain.doFilter(request, httpServletResponse);
	    }
	}
	if (!authenticated) {
	    httpServletResponse.sendRedirect("home");
	}
	// allow
	// else do not allow
    }

}
