package model;

import repository.LibraryRepository;

public class BookDetails extends Book {
    private boolean isLend;
    private Person borrower;

    public BookDetails(String title, int year, Person author, boolean isLend, Person borrower) {
        super(title, year, author);
        this.isLend = isLend;
        this.borrower = borrower;
    }

    public BookDetails(String title, int year, Person author) {
        super(title, year, author);
        isLend=false;
        // borrower=new Person();
    }

    public boolean isLend() {
        return isLend;
    }

    public void setLend(boolean lend) {
        isLend = lend;
    }

    public Person getBorrower() {
        return borrower;
    }

    public void setBorrower(Person borrower) {
        this.borrower = borrower;
    }




    @Override
    public String toString() {
        String result = "title: " + title + ", author= " + author + ", year: " +year+ ", is lent? " +isLend;

        if(borrower != null) {
            result = result + ", borrower= " +borrower ;
        }

        return result;

        //   return "title: " + title + ", author= " + author + ", year: " +year+ ", is lent: " +isLend+ ", borrower= " +borrower ;

//        return "BookDetails{" +
//                "is available=" + isLend +
//                ", borrower=" + borrower +
//                ", title='" + title + '\'' +
//                ", year=" + year +
//                ", author=" + author +
//                '}';
    }


}
