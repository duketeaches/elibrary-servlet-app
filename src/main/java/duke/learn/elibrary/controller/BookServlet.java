/**
 * 
 */
package duke.learn.elibrary.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import duke.learn.elibrary.service.BookService;

/**
 * @author Kazi
 *
 */
public class BookServlet extends HttpServlet {

    private static final long serialVersionUID = 5064574720518754494L;

    private BookService bookService;

    /**
     * 
     */
    public BookServlet() {
	bookService = new BookService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	bookService.getBooks(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	bookService.addBook(req, resp);
    }
}
