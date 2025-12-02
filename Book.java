// David Austen

// Taylor Adams

// Josh Mitchell

public class Book {
    private String id;
    private String title;
    private Author author;
    private Genre genre;
    private boolean available = true;

    public Book(String id, String title, Author author, Genre genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public String getId() { 
        return id; 
    }
    public String getTitle() { 
        return title; 
    }
    public Author getAuthor() { 
        return author; 
    }
    public Genre getGenre() { return genre; }
    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }

    @Override
    public String toString() {
        return "[" + id + "] " + title + " by " + author.getName() +
               " (" + genre.getName() + ")" +
               (available ? " [Available]" : " [On Loan]");
    }
}
