package testing;

import model.Book;
import model.Person;
import org.junit.jupiter.api.*;
import service.Library;

import java.util.ArrayList;
import java.util.List;

class LibraryTests {

   static Library library = Library.INSTANCE;

    static Person borrower = new Person("firstName", "lastName");
    static List <Book> listTempTesting = new ArrayList<>();
    static List <Book> listTempTesting2 = new ArrayList<>();
    static List <Book> listTempTesting3 = new ArrayList<>();

    @BeforeAll
    public static void init() { // random data for testing
        Book [] books = new Book[15];
        books[0] = new Book("Hamlet",1970, new Person("William", "Shakespeare"));
        books[1] = new Book("Lolita",2001, new Person("Vladimir", "Nabokov"));
        books[2] = new Book("The Odyssey",2003, new Person("Vladimir", "Nabokov"));
        books[3] = new Book("Ulysses",2002, new Person("James", "Joyce"));
        books[4] = new Book("Moby Dick",2004, new Person("Herman", "Melville"));
        books[5] = new Book("War and Peace",2007, new Person("Walt", "Whitman"));
        books[6] = new Book("Leaves of Grass",2020, new Person("Walt", "Whitman"));
        books[7] = new Book("The Magic Mountain",2003, new Person("Thomas", "Mann"));
        books[8] = new Book("The Most Wanted: Return to Rockport part III",2012, new Person("Andreas", "Lansky"));
        books[9] = new Book("Gone With the Wind",1970, new Person("Margaret", "Mitchell"));
        books[10] = new Book("Lord of the Flies",2003, new Person("William", "Golding"));
        books[11] = new Book("Emma",2003, new Person("Jane", "Austen"));
        books[12] = new Book("On the Road",1998, new Person("Jack", "Kerouac"));
        books[13] = new Book("The Most Wanted: Return to Rockport",2009, new Person("Andreas", "Lansky"));
        books[14] = new Book("The Most Wanted: Return to Rockport part II",2010, new Person("Andreas", "Lansky"));

        for (Book book : books) {
            library.addNewBook(book);
        }

        listTempTesting.add(books[13]);
        listTempTesting.add(books[14]);
        listTempTesting.add(books[8]);

        listTempTesting2.add(books[3]);

        listTempTesting3.add(books[0]);
        listTempTesting3.add(books[9]);
    }

    @Test
    public void addNewBookTest() {
        Book book1 = new Book("test1",2000, new Person("name1","surname1"));
        Book book2 = new Book("test2",2010, new Person("name2","surname2"));
        Book book3 = new Book("test2",2020, new Person("name3","surname3"));

        Assertions.assertAll(
                () -> Assertions.assertEquals(15, library.getQuantityOfBooks()),
                () -> Assertions.assertTrue(library.addNewBook(book1)),
                () -> Assertions.assertTrue(library.addNewBook(book2)),
                () -> Assertions.assertEquals(17, library.getQuantityOfBooks()),
                () -> Assertions.assertTrue(library.addNewBook(book3)),
                () -> Assertions.assertEquals(18, library.getQuantityOfBooks())
        );
    }

    @Test
    public void bookExistByIdTest() {
        Assertions.assertAll(
                () -> Assertions.assertTrue(library.bookExistById(1)),
                () -> Assertions.assertTrue(library.bookExistById(15)),
                () -> Assertions.assertFalse(library.bookExistById(0)),
                () -> Assertions.assertFalse(library.bookExistById(-9)),
                () -> Assertions.assertFalse(library.bookExistById(20))
        );
    }

    @Test
    public void lentBookByIdTest() {
        Assertions.assertAll(
                () -> Assertions.assertFalse(library.lentBookById(0, borrower)),
                () -> Assertions.assertFalse(library.lentBookById(-3, borrower)),
                () -> Assertions.assertTrue(library.lentBookById(7, borrower)),
                () -> Assertions.assertFalse(library.lentBookById(7, borrower))
        );
    }

    @Test
    public void removeBookByIdTest() {
        library.lentBookById(10, borrower);
        Assertions.assertAll(
                () -> Assertions.assertFalse(library.removeBookById(10)),
                () -> Assertions.assertTrue(library.removeBookById(1)),
                () -> Assertions.assertFalse(library.removeBookById(-9)),
                () -> Assertions.assertFalse(library.removeBookById(20))
        );
    }

    @Test
    public void searchBookByTitleTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByTitle("test")),
                () -> Assertions.assertTrue(listTempTesting.containsAll(library.searchBookByTitle("the most wanted"))),
                () -> Assertions.assertEquals(listTempTesting2, library.searchBookByTitle("ulysses"))
        );
    }

    @Test
    public void searchBookByYearTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByYear(2030)),
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByYear(-2030)),
                () -> Assertions.assertEquals(listTempTesting2, library.searchBookByYear(2002)),
                () -> Assertions.assertEquals(listTempTesting3, library.searchBookByYear(1970))
        );
    }

    @Test
    public void searchBookByAuthorTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByAuthor(new Person("test","test"))),
                () -> Assertions.assertEquals(listTempTesting2, library.searchBookByAuthor(new Person("James", "Joyce"))),
                () -> Assertions.assertTrue(listTempTesting.containsAll(library.searchBookByAuthor(new Person("andreas", "Lansky"))))
        );
    }

    @Test
    public void searchBookByTitleAndAuthorTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByTitleAndAuthor("title", new Person("test","test"))),
                () -> Assertions.assertTrue(listTempTesting.containsAll(library.searchBookByTitleAndAuthor("the most wanted: return to rockport", new Person("andreas","lansky"))))
        );
    }

    @Test
    public void searchBookByTitleAndYearTest() {
        Assertions.assertAll(
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByTitleAndYear("title",1980)),
                () -> Assertions.assertEquals(new ArrayList<>(), library.searchBookByTitleAndYear("title",-1999)),
                () -> Assertions.assertEquals(listTempTesting2, library.searchBookByTitleAndYear("ulysses",2002))
        );
    }

    @Test
    public void showBookDetailsByIdTest() {
        library.lentBookById(12, borrower);
        Assertions.assertAll(
                () -> Assertions.assertEquals("Not found.",library.showBookDetailsById(0)),
                () -> Assertions.assertEquals("title: Hamlet, author= first name: William, last name: Shakespeare, year: 1970, is lent? false",library.showBookDetailsById(1)),
                () -> Assertions.assertEquals("title: Emma, author= first name: Jane, last name: Austen, year: 2003, is lent? true, borrower= first name: firstName, last name: lastName",library.showBookDetailsById(12))
          );
    }
}