package model;

import java.util.Objects;

public class Book {
    protected String title;
    protected int year;
    protected Person author;

    public Book(String title, int year, Person author) {
        this.title = title;
        this.year = year;
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Person getAuthor() {
        return author;
    }

    public void setAuthor(Person author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return year == book.year &&
                Objects.equals(title, book.title) &&
                Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, year, author);
    }

    @Override
    public String toString() {
        return "title=" + title + ", year=" + year +", author=" + author;
    }
}
