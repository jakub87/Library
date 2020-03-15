package model;

public class BookDetails extends Book {
    private boolean isLend;
    private Person borrower;

    public BookDetails(String title, int year, Person author) {
        super(title, year, author);
        isLend=false;
    }

    public boolean isLend() {
        return isLend;
    }

    public void setLend(boolean lend) {
        isLend = lend;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }

    @Override
    public String toString() {
        String bookDetailsString = "title: " + title + ", author= " + author + ", year: " + year + ", is lent? " + isLend;
        if(borrower != null) {
            bookDetailsString = bookDetailsString + ", borrower= " + borrower ;
        }

        return bookDetailsString;
    }
}