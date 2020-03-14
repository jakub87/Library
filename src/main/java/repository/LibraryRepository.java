package repository;

import model.Book;

public interface LibraryRepository {
    boolean addNewBook(Book book);
    boolean bookExistById(int id);



}
