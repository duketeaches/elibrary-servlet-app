/**
 * 
 */
package duke.learn.elibrary.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import duke.learn.elibrary.model.Book;

/**
 * @author Kazi
 *
 */
public class BookRepositoryImpl implements BookRepository {

    private Connection connection;

    public BookRepositoryImpl() {
	connection = ConnectionProvider.getConnection();

    }

    @Override
    public Book addUpdateBook(Book book) {
	try {
	    if (book != null) {
		Integer bookId = book.getBookId();
		if (bookId != null) {
		    // update book
		    String query = "update books set   name=?, author=?, year=?, isbn=?, image_path=?, storage_path=? where book_id = ?";
		    PreparedStatement statement = connection.prepareStatement(query);
		    statement.setString(1, book.getName());
		    statement.setString(2, book.getAuthor());
		    statement.setInt(3, book.getYear());
		    statement.setString(4, book.getIsbn());
		    statement.setString(5, book.getImagePath());
		    statement.setString(6, book.getStoragePath());
		    statement.setInt(7, book.getBookId());
		    int count = statement.executeUpdate();
		    System.out.println("Update " + count + " books");
		} else {
		    // insert a new record
		    String query = "insert into books (name, author, year, isbn, image_path, storage_path) values (?,?,?,?,?,?)";
		    PreparedStatement statement = connection.prepareStatement(query,
			    PreparedStatement.RETURN_GENERATED_KEYS);
		    statement.setString(1, book.getName());
		    statement.setString(2, book.getAuthor());
		    statement.setInt(3, book.getYear());
		    statement.setString(4, book.getIsbn());
		    statement.setString(5, book.getImagePath());
		    statement.setString(6, book.getStoragePath());
		    statement.executeUpdate();
		    ResultSet keySet = statement.getGeneratedKeys();
		    if (keySet.next()) {
			bookId = keySet.getInt(1);
		    }
		    book.setBookId(bookId);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return book;
    }

    @Override
    public Book getBook(Integer bookId) {
	Book book = new Book();
	try {
	    String query = "select * from books where book_id = ?";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setInt(1, bookId);
	    ResultSet rs = statement.executeQuery();
	    book = convertToBook(rs);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return book;
    }

    @Override
    public Book getBook(String isbn) {
	Book book = new Book();
	try {
	    String query = "select * from books where isbn = ?";
	    PreparedStatement statement = connection.prepareStatement(query);
	    statement.setString(1, isbn);
	    ResultSet rs = statement.executeQuery();
	    book = convertToBook(rs);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return book;
    }

    @Override
    public List<Book> getBooks(String query) {
	List<Book> books = null;
	try {
	    String sql = "select * from books where name like ? or author like ? or isbn like ?";
	    PreparedStatement statement = connection.prepareStatement(sql);
	    statement.setString(1, "%" + query + "%");
	    statement.setString(2, "%" + query + "%");
	    statement.setString(3, "%" + query + "%");
	    ResultSet rs = statement.executeQuery();
	    books = convertToBooks(rs);
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return books;
    }

    @Override
    public void deleteBook(Integer bookId) {
	try {
	    if (bookId != null) {
		PreparedStatement preparedStatement = connection.prepareStatement("delete from books where book_id= ?");
		preparedStatement.setInt(1, bookId);
		int count = preparedStatement.executeUpdate();
		System.out.println("Deleted " + count + " book row");
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}

    }

    private Book convertToBook(ResultSet rs) {
	List<Book> books = convertToBooks(rs);
	if (books != null && !books.isEmpty()) {
	    return books.get(0);
	}
	return null;
    }

    private List<Book> convertToBooks(ResultSet rs) {
	List<Book> books = new ArrayList<>();
	try {
	    if (rs != null) {
		while (rs.next()) {
		    Integer bookId = rs.getInt("book_id");
		    String name = rs.getString("name");
		    String author = rs.getString("author");
		    Integer year = rs.getInt("year");
		    String isbn = rs.getString("isbn");
		    String imageath = rs.getString("image_path");
		    String storagePath = rs.getString("storage_path");
		    books.add(new Book(bookId, name, author, year, isbn, imageath, storagePath));
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return books;
    }

    public static void main(String[] args) {
	BookRepositoryImpl repo = new BookRepositoryImpl();
	// Book book = new Book(null, "Java Concurrency", "Duke", 2012, "5343654KKJH");
	// book = repo.addUpdateBook(book);
	// book = repo.getBook(book.getBookId());
	// System.out.println(book);
	// book = repo.getBook(book.getIsbn());
	// System.out.println(book);
	// // repo.deleteBook(book.getBookId());
	// book = repo.getBook(book.getBookId());

	List<Book> books = repo.getBooks("");
	books.forEach(System.out::println);

    }

}
