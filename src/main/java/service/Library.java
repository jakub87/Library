package service;

import model.Book;
import model.BookDetails;
import repository.LibraryRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Library implements LibraryRepository {
    private int id;
    private Map<Integer, BookDetails> books;

    public Library() {
        id=1;
        books = new HashMap<>();
    }

    @Override
    public boolean addNewBook(Book book) {
        books.put(id++,new BookDetails(book.getTitle(),book.getYear(),book.getAuthor()));

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


    public Map<Integer, BookDetails> getBooks() {
        return books;
    }
}
