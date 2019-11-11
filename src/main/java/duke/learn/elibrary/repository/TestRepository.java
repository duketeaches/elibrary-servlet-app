package duke.learn.elibrary.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import duke.learn.elibrary.model.Book;

public class TestRepository {
    private static Connection connection = ConnectionMngr.getConnection();

    static int counter = 2;

    // get book
    // id name ....
    // 1 java....

    // set.getInt(2)

    // id
    // 20
    // set.getInt(1)
    public static Integer insertBook(Book book) {

	Integer id = book.getBookId();
	// int i = 0;
	// i++;
	// 1. get value of i
	// 2. add 1 to i
	// 3. store new value in i
	// counter.getAndIncrement();
	// counter++;
	if (id != null) {

	} else {
	    String query = "insert into books (name, author, year, isbn, image_path, storage_path) values (?,?,?,?,?,?)";
	    try {
		PreparedStatement statement = connection.prepareStatement(query,
			PreparedStatement.RETURN_GENERATED_KEYS);
		statement.setString(1, book.getName());
		statement.setString(2, book.getAuthor());
		statement.setInt(3, book.getYear());
		statement.setString(4, book.getIsbn());
		statement.setString(5, book.getImagePath());
		statement.setString(6, book.getStoragePath());

		statement.executeUpdate();

		ResultSet keyset = statement.getGeneratedKeys();
		while (keyset.next()) {
		    id = keyset.getInt(1);
		}

	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}

	return id;
    }

    public static Book getBook(Integer id) {
	Book book = null;
	if (id != null) {
	    String query = "Select * from books where book_id = ?";
	    try {
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1, id);

		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
		    book = new Book();
		    book.setBookId(resultSet.getInt(1));
		    book.setName(resultSet.getString(2));
		    book.setAuthor(resultSet.getString(3));
		    book.setYear(resultSet.getInt(4));
		    book.setIsbn(resultSet.getString(5));
		    book.setImagePath(resultSet.getString(6));
		    book.setStoragePath(resultSet.getString(7));

		}
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	}
	return book;

    }

    public static void main(String[] args) {
	Book book = new Book(null, "Nitesh", "test", 1020, "4354999", "/d/fdfj", "/c/testing");
	Integer id = insertBook(book);
	System.out.println("id = " + id);
	Book book2 = getBook(id);
	System.out.println(book2);
    }

    public static class ConnectionMngr {

	private static final String url = "jdbc:mysql://127.0.0.1:3306/elibrary";
	private static final String username = "kazi";
	private static final String password = "kazi";

	private static Connection connection;

	public static synchronized Connection getConnection() {
	    if (connection == null) {
		try {
		    Class.forName("com.mysql.jdbc.Driver");
		    connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {
		}
	    }

	    return connection;
	}
    }

}
