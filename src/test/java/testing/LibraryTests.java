package testing;

import model.Book;
import model.Person;
import org.junit.jupiter.api.*;
import service.Library;


class LibraryTests {

    Library library = new Library();

    public void init() {
        Book book1 = new Book("aaa",2000,new Person("bbb","ccc")); //id=1
        Book book2 = new Book("bbb",2002,new Person("xxx","ccc")); //id=2
        Book book3 = new Book("ccc",2010,new Person("zzz","ccc")); //id=3
        Book book4 = new Book("ddd",1960,new Person("fgh","ccc")); //id=4
        Book book5 = new Book("eee",2020,new Person("vbn","ccc")); //id=5
        Book book6 = new Book("fff",2009,new Person("bnm","ccc")); //id=6
        library.addNewBook(book1);
        library.addNewBook(book2);
        library.addNewBook(book3);
        library.addNewBook(book4);
        library.addNewBook(book5);
        library.addNewBook(book6);
    }


    @DisplayName("check if books has been added")
    @Test
    public void addNewBookTest() {
        Book book1 = new Book("aaa",2000,new Person("bbb","ccc"));
        Book book2 = new Book("bbb",2010,new Person("ddd","aaa"));
        Book book3 = new Book("ccc",2020,new Person("eee","fff"));

        Assertions.assertAll(
                () -> Assertions.assertEquals(0,library.getBooks().size()),
                () -> Assertions.assertTrue(library.addNewBook(book1)),
                () -> Assertions.assertTrue(library.addNewBook(book2)),
                () -> Assertions.assertEquals(2,library.getBooks().size()),
                () -> Assertions.assertTrue(library.addNewBook(book3)),
                () -> Assertions.assertEquals(3,library.getBooks().size())
        );
    }

    @DisplayName("check if book by id exists")
    @Test
    public void bookExistByIdTest() {
        init();

        Assertions.assertAll(
                () -> Assertions.assertTrue(library.bookExistById(1)),
                () -> Assertions.assertTrue(library.bookExistById(3)),
                () -> Assertions.assertTrue(library.bookExistById(6)),
                () -> Assertions.assertFalse(library.bookExistById(0)),
                () -> Assertions.assertFalse(library.bookExistById(7))
        );
    }


   // main main;


//
//    @Test
//    void dodajTest() {
////        Assertions.assertEquals(10,main.dodaj(5,5));
////        Assertions.assertEquals(15,main.dodaj(10,5));
//
//        Assertions.assertAll(
//                () -> Assertions.assertEquals(10,main.dodaj(5,5)),
//                () -> Assertions.assertEquals(20,main.dodaj(15,5))
//
//        );
//
//    }
//
////    assertAll(
////                ()-> assertEquals(4,liczby.dodaj(2,2)),
////            ()-> assertEquals(5,liczby.dodaj(3,2)),
////            ()-> assertEquals(1,liczby.dodaj(2,-1))
////
////            );

}