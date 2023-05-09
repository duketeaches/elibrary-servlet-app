/**
 * 
 */
package duke.learn.elibrary.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Kazi
 *
 */
public class ConnectionProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionProvider.class);
    private static Connection connection;
    private static final String url = "jdbc:mysql://127.0.0.1:3316/elibrary";
    private static final String username = "kazi";
    private static final String password = "kazi";

    public static synchronized Connection getConnection() {
	if (connection == null) {
	    try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(url, username, password);
		if (connection != null) {
		    LOGGER.info("Connected to " + url);
		}
	    } catch (ClassNotFoundException | SQLException e) {
		LOGGER.error("Failed to establish connection to " + url + " with user: " + username + " and password: "
			+ password);
		e.printStackTrace();
	    }
	}
	return connection;
    }
}
