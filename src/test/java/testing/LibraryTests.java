package testing;

import model.Book;
import model.Person;
import org.junit.jupiter.api.*;
import service.Library;


import java.util.ArrayList;
import java.util.List;


class LibraryTests {

    Library library = new Library();

    public void init() {
        Book book1 = new Book("aaa",2000,new Person("bbb","ccc")); //id=1
        Book book2 = new Book("bbb",2002,new Person("xxx","ccc")); //id=2
        Book book3 = new Book("ccc",2010,new Person("zzz","ccc")); //id=3
        Book book4 = new Book("ddd",1960,new Person("fgh","ccc")); //id=4
        Book book5 = new Book("Ddd part II",2020,new Person("vbn","ccc")); //id=5
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

    @DisplayName("check if book by id can be removed")
    @Test
    public void removeBookByIdTest() {
        init();

        Assertions.assertAll(
                () -> Assertions.assertTrue(library.removeBookById(1)),
                () -> Assertions.assertTrue(library.removeBookById(6)),
                () -> Assertions.assertFalse(library.removeBookById(0)),
                () -> Assertions.assertFalse(library.removeBookById(-9)),
                () -> Assertions.assertFalse(library.removeBookById(7))
        );
    }

    @DisplayName("check if book can be lend")
    @Test
    public void lentBookByIdTest() {
        init();
        Person borrower = new Person("firstName","lastName");

        Assertions.assertAll(
                () -> Assertions.assertFalse(library.lentBookById(0,borrower)),
                () -> Assertions.assertFalse(library.lentBookById(7,borrower)),
                () -> Assertions.assertTrue(library.lentBookById(1,borrower)),
                () -> Assertions.assertFalse(library.lentBookById(1,borrower)),
                () -> Assertions.assertTrue(library.lentBookById(5,borrower))
        );
    }

    @DisplayName("return books which contain specific title")
    @Test
    public void searchBookByTitleTest() {
        init();

        Book book2 = new Book("bbb",2002,new Person("xxx","ccc"));
        List <Book> temp = new ArrayList<>();
        temp.add(book2);

        List <Book> dupa = library.searchBookByTitle("bbb");

        Assertions.assertAll(
                () -> Assertions.assertEquals(new ArrayList<>(),library.searchBookByTitle("test"))
        );

    }


    @Test
    public void searchBookByYearTest() {
        init();

        List<Book> listTemp = new ArrayList<>();
        listTemp.add(new Book("Ddd part II",2020,new Person("vbn","ccc")));

        Assertions.assertAll(
                () -> Assertions.assertEquals(listTemp,library.searchBookByYear(2020))
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