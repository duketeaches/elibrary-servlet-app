/**
 * 
 */
package duke.learn.elibrary.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import duke.learn.elibrary.model.Book;
import duke.learn.elibrary.repository.BookRepository;
import duke.learn.elibrary.repository.BookRepositoryImpl;

/**
 * @author Kazi
 *
 */
public class DownloadServlet extends HttpServlet {

    BookRepository bookRepository;

    public DownloadServlet() {
	bookRepository = new BookRepositoryImpl();

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	String idString = req.getParameter("id");
	String bookDownload = req.getParameter("content");
	if (idString != null && !idString.isEmpty()) {
	    Integer id = Integer.parseInt(idString);
	    Book book = bookRepository.getBook(id);
	    if (book != null) {
		String filepath = null;

		if (bookDownload != null) {
		    filepath = book.getStoragePath();
		} else {
		    filepath = book.getImagePath();
		}

		if (filepath != null) {
		    File image = new File(filepath);
		    FileInputStream fileIn = new FileInputStream(image);
		    ServletOutputStream out = resp.getOutputStream();

		    byte[] outputByte = new byte[4096];
		    // copy binary contect to output stream
		    while (fileIn.read(outputByte, 0, 4096) != -1) {
			out.write(outputByte, 0, 4096);
		    }
		    fileIn.close();
		    out.flush();
		    out.close();
		}
	    }
	}
    }
}
