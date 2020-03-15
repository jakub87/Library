package service;

import model.Book;
import model.BookDetails;
import model.Person;
import repository.LibraryRepository;

import java.util.*;

public class Library implements LibraryRepository {
    private int id;
    private Map<Integer, BookDetails> books;

    public static final Library INSTANCE = new Library();

    private Library() {
        id=1;
        books = new HashMap<>();
    }

    public int getQuantityOfBooks(){  // needs to testing
        return books.size();
    } //this method is only needed for testing purpose

    @Override
    public boolean addNewBook(Book book) {
        books.put(id++,new BookDetails(book.getTitle(), book.getYear(), book.getAuthor()));
        return true;
    }

    @Override
    public boolean bookExistById(int id) {
        Optional<BookDetails> bookDetails = Optional.ofNullable(books.get(id));
        if (bookDetails.isPresent()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean removeBookById(int id) {
        if (bookExistById(id) && !books.get(id).isLend()) {
            books.remove(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean lentBookById(int id, Person borrower) {
        if (bookExistById(id) && !books.get(id).isLend()) {
            books.get(id).setLend(true);
            books.get(id).setBorrower(borrower);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void showAllBooks() {
        int quantityAvailableBooks = (int) books.entrySet().stream().filter(bookDetails -> !bookDetails.getValue().isLend()).count();
        int quantityLendBooks = books.size() - quantityAvailableBooks;

        books.forEach((id,bookDetails) -> System.out.println(bookDetails));

        System.out.println("Books available: " + quantityAvailableBooks);
        System.out.println("Books lent: " + quantityLendBooks);
    }

    @Override
    public List<Book> searchBookByTitle(String title) {
        List<Book> bookListByTitle = new ArrayList<>();
        books.values().stream()
                      .filter(bookDetails -> bookDetails.getTitle().toLowerCase().contains(title.toLowerCase()))
                      .forEach(bookDetails -> bookListByTitle.add(new Book(bookDetails.getTitle(), bookDetails.getYear(), bookDetails.getAuthor())));

        return bookListByTitle;
    }

    @Override
    public List<Book> searchBookByYear(int year) {
        List<Book> bookListByYear = new ArrayList<>();
        books.values().stream()
                      .filter(bookDetails -> bookDetails.getYear() == year)
                      .forEach(bookDetails -> bookListByYear.add(new Book(bookDetails.getTitle(), bookDetails.getYear(), bookDetails.getAuthor())));

        return bookListByYear;
    }

    @Override
    public List<Book> searchBookByAuthor(Person author) { // I assume that firstName and lastname of author are case sensitive
        List<Book> bookListByAuthor = new ArrayList<>();
        books.values().stream()
                      .filter(bookDetails -> bookDetails.getAuthor().equals(author))
                      .forEach(bookDetails -> bookListByAuthor.add(new Book(bookDetails.getTitle(), bookDetails.getYear(), bookDetails.getAuthor())));

        return bookListByAuthor;
    }

    @Override
    public List<Book> searchBookByTitleAndAuthor(String title, Person author) { // I assume that firstName and lastname of author are case sensitive
        List<Book> bookListByTitleAndAuthor = new ArrayList<>();
        books.values().stream()
                .filter(bookDetails -> bookDetails.getAuthor().equals(author) && bookDetails.getTitle().toLowerCase().contains(title.toLowerCase()))
                .forEach(bookDetails -> bookListByTitleAndAuthor.add(new Book(bookDetails.getTitle(), bookDetails.getYear(), bookDetails.getAuthor())));

        return bookListByTitleAndAuthor;
    }

    @Override
    public List<Book> searchBookByTitleAndYear(String title, int year) {
        List<Book> bookListByTitleAndYear = new ArrayList<>();
        books.values().stream()
                .filter(bookDetails -> bookDetails.getYear() == year && bookDetails.getTitle().toLowerCase().contains(title.toLowerCase()))
                .forEach(bookDetails -> bookListByTitleAndYear.add(new Book(bookDetails.getTitle(), bookDetails.getYear(), bookDetails.getAuthor())));

        return bookListByTitleAndYear;
    }

    @Override
    public String showBookDetailsById(int id) {
        if (bookExistById(id)) {
            return "" + books.get(id);
        } else {
            return "Not found.";
        }
    }
}