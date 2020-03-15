package repository;

import model.Book;
import model.Person;

import java.util.List;

public interface LibraryRepository {
    boolean addNewBook(Book book);
    boolean bookExistById(int id);
    boolean removeBookById(int id);
    boolean lentBookById(int id, Person borrower);
    void showAllBooks();
    String showBookDetailsById(int id);
    List<Book> searchBookByTitle(String title);
    List<Book> searchBookByYear(int year);
    List<Book> searchBookByAuthor(Person author);
    List<Book> searchBookByTitleAndAuthor(String title, Person author);
    List<Book> searchBookByTitleAndYear(String title, int year);
}