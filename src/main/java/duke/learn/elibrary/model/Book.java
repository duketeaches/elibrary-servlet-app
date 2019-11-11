/**
 * 
 */
package duke.learn.elibrary.model;

/**
 * @author Kazi
 *
 */
public class Book {

    private Integer bookId;
    private String name;
    private String author;
    private Integer year;
    private String isbn;
    private String imagePath;
    private String storagePath;

    /**
     * 
     * @param bookId
     * @param name
     * @param author
     * @param year
     * @param isbn
     * @param imagePath
     * @param storagePath
     */
    public Book(Integer bookId, String name, String author, Integer year, String isbn, String imagePath,
	    String storagePath) {
	super();
	this.bookId = bookId;
	this.name = name;
	this.author = author;
	this.year = year;
	this.isbn = isbn;
	this.imagePath = imagePath;
	this.storagePath = storagePath;
    }

    /**
     * 
     */
    public Book() {
	super();
	// TODO Auto-generated constructor stub
    }

    public Integer getBookId() {
	return bookId;
    }

    public void setBookId(Integer bookId) {
	this.bookId = bookId;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getAuthor() {
	return author;
    }

    public void setAuthor(String author) {
	this.author = author;
    }

    public Integer getYear() {
	return year;
    }

    public void setYear(Integer year) {
	this.year = year;
    }

    public String getIsbn() {
	return isbn;
    }

    public void setIsbn(String isbn) {
	this.isbn = isbn;
    }

    @Override
    public String toString() {
	return "Book [bookId=" + bookId + ", name=" + name + ", author=" + author + ", year=" + year + ", isbn=" + isbn
		+ ", imagePath=" + imagePath + ", storagePath=" + storagePath + "]";
    }

    public String getImagePath() {
	return imagePath;
    }

    public void setImagePath(String imagePath) {
	this.imagePath = imagePath;
    }

    public String getStoragePath() {
	return storagePath;
    }

    public void setStoragePath(String storagePath) {
	this.storagePath = storagePath;
    }

}
