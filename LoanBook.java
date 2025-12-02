// David Austen

// Taylor Adams

// Josh Mitchell


public class LoanBook {
    private Book book;
    private Student student;
    private String loanDate;

    public LoanBook(Book book, Student student, String loanDate) {
        this.book = book;
        this.student = student;
        this.loanDate = loanDate;
        this.book.setAvailable(false);
        
    }

    public Book getBook() { 
        return book; 
    }
    public Student getStudent() { 
        return student; 
    }
    public String getLoanDate() { 
        return loanDate; 
    }

    @Override
    public String toString() {
        return student.getName() + " borrowed \"" + book.getTitle() + "\" on " + loanDate;
    }
}
