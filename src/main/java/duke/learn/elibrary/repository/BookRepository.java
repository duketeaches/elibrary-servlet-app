/**
 * 
 */
package duke.learn.elibrary.repository;

import java.util.List;

import duke.learn.elibrary.model.Book;

/**
 * @author Kazi
 *
 */
public interface BookRepository {

    Book addUpdateBook(Book book);

    Book getBook(Integer bookId);

    Book getBook(String isbn);

    List<Book> getBooks(String query);

    void deleteBook(Integer bookId);

}
