/**
 * 
 */
package duke.learn.elibrary.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import duke.learn.elibrary.model.Book;
import duke.learn.elibrary.repository.BookRepository;
import duke.learn.elibrary.repository.BookRepositoryImpl;

/**
 * @author Kazi
 *
 */
public class BookService {

    private BookRepository bookRepository;

    private static final String file_upload_path = "/opt/elibrary/files";

    /**
     * 
     */
    public BookService() {
	bookRepository = new BookRepositoryImpl();

    }

    public void getBooks(HttpServletRequest request, HttpServletResponse response)
	    throws ServletException, IOException {
	String query = request.getParameter("query");
	query = query == null ? "" : query;
	List<Book> books = bookRepository.getBooks(query);
	request.setAttribute("books", books);
	request.getRequestDispatcher("pages/book-gallery.jsp").forward(request, response);
    }

    public void addBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	if (ServletFileUpload.isMultipartContent(request)) {
	    try {
		String finalFileName = null;
		String bookName = null;
		String author = null;
		Integer year = null;
		String isbn = null;
		String imagePath = null;
		String contentPath = null;
		List<FileItem> uploadedFiles = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
		for (FileItem fileItem : uploadedFiles) {
		    if (fileItem != null) {
			if (!fileItem.isFormField()) {
			    String fieldName = fileItem.getFieldName();
			    String name = fileItem.getName();
			    finalFileName = file_upload_path + File.separator + System.currentTimeMillis() + "_" + name;
			    File destinationFile = new File(finalFileName);
			    File parent = destinationFile.getParentFile();
			    parent.mkdirs();
			    fileItem.write(destinationFile);
			    switch (fieldName) {
			    case "image":
				imagePath = finalFileName;
				break;
			    case "content":
				contentPath = finalFileName;
				break;
			    default:
				break;
			    }
			} else {
			    String fieldName = fileItem.getFieldName();
			    switch (fieldName) {
			    case "name":
				bookName = fileItem.getString();
				break;
			    case "author":
				author = fileItem.getString();
				break;
			    case "year":
				String yearString = fileItem.getString();
				if (yearString != null && !yearString.isEmpty()) {
				    year = Integer.parseInt(yearString);
				}
				break;
			    case "isbn":
				isbn = fileItem.getString();
				break;
			    default:
				break;
			    }
			}
		    }
		}
		// Get the data of book

		Book book = new Book(null, bookName, author, year, isbn, imagePath, contentPath);
		bookRepository.addUpdateBook(book);
		request.setAttribute("message", "book added successfully");
	    } catch (Exception e) {
		request.setAttribute("message",
			"File Upload Failed due to " + e.getClass().getName() + ": " + e.getMessage());
	    }
	    response.sendRedirect("books");
	}
    }

}
