package service;

import model.Book;
import model.Person;

public class TempMain {

    public static void main(String[] args) {

        Library library = new Library();
        //System.out.println("test");
        Book book1 = new Book("aaa",2000,new Person("bbb","ccc")); //id=1
        Book book2 = new Book("bbb",2002,new Person("xxx","ccc")); //id=2
        Book book3 = new Book("ccc",2010,new Person("zzz","ccc")); //id=3
        Book book4 = new Book("ddd",2010,new Person("bbb","ccc")); //id=4
        Book book5 = new Book("ddd czesc II",2020,new Person("bbb","ccc")); //id=5
        Book book6 = new Book("fff",2009,new Person("bnm","ccc")); //id=6
        library.addNewBook(book1);
        library.addNewBook(book2);
        library.addNewBook(book3);
        library.addNewBook(book4);
        library.addNewBook(book5);
        library.addNewBook(book6);

//        library.lentBookById(2,new Person("s","s"));
//        library.showAllBooksDetailsById(2);

//        library.showAllBooks();
//        library.lentBookById(2,new Person("s","s"));
        //library.showAllBooks();

        System.out.println(library.searchBookByTitleAndAuthor("DDD",new Person("bbb","ccc")));

    }

}
