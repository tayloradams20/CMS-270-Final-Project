// David Austen

// Taylor Adams

// Josh Mitchell

import java.io.*;
import java.util.*;

public class LibraryDataManager {

    private static final String DATA_FILE = "library_data.txt"; // you can put a full shared path here

    public static void saveData(List<Book> books, List<Student> students, List<LoanBook> loans) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(DATA_FILE))) {
            writer.println("#BOOKS");
            for (Book b : books) {
                writer.printf("%s|%s|%s|%s|%b%n",
                        b.getId(), b.getTitle(), b.getAuthor().getName(),
                        b.getGenre().getName(), b.isAvailable());
            }

            writer.println("#STUDENTS");
            for (Student s : students) {
                writer.printf("%s|%s%n", s.getId(), s.getName());
            }

            writer.println("#LOANS");
            for (LoanBook l : loans) {
                writer.printf("%s|%s|%s%n",
                        l.getBook().getId(), l.getStudent().getId(), l.getLoanDate());
            }

            System.out.println("Data saved successfully!");
        } catch (IOException e) {
            System.err.println("Error saving data: " + e.getMessage());
        }
    }

    public static void loadData(List<Book> books, List<Student> students, List<LoanBook> loans) {
        File file = new File(DATA_FILE);
        if (!file.exists()) {
            System.out.println("No data file found — starting with an empty library.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            String section = "";

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("#")) {
                    section = line;
                    continue;
                }

                String[] parts = line.split("\\|");
                switch (section) {
                    case "#BOOKS" -> {
                        String id = parts[0];
                        String title = parts[1];
                        String author = parts[2];
                        String genre = parts[3];
                        boolean available = Boolean.parseBoolean(parts[4]);
                        Book book = new Book(id, title, new Author(author), new Genre(genre));
                        book.setAvailable(available);
                        books.add(book);
                    }
                    case "#STUDENTS" -> {
                        String id = parts[0];
                        String name = parts[1];
                        students.add(new Student(id, name));
                    }
                    case "#LOANS" -> {
                        String bookId = parts[0];
                        String studentId = parts[1];
                        String date = parts[2];

                        Book book = books.stream().filter(b -> b.getId().equals(bookId)).findFirst().orElse(null);
                        Student student = students.stream().filter(s -> s.getId().equals(studentId)).findFirst().orElse(null);

                        if (book != null && student != null) {
                            loans.add(new LoanBook(book, student, date));
                        }
                    }
                }
            }

            System.out.println("✅ Data loaded successfully!");
        } catch (IOException e) {
            System.err.println("❌ Error loading data: " + e.getMessage());
        }
    }
}
