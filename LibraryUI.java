
// TESTING

// TESTING

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.time.LocalDate;

public class LibraryUI extends JFrame {
    private ArrayList<Book> books = new ArrayList<>();
    private ArrayList<Student> students = new ArrayList<>();
    private ArrayList<LoanBook> loans = new ArrayList<>();

    {
        LibraryDataManager.loadData(books, students, loans);
    }

    // Sample books initialization
    {
        books.add(new Book("1559-5145", "(Re)turn: A Journal of Lacanian Studies", new Author("(Re)turn: A Journal of Lacanian Studies"), new Genre("Academic Journal")));
        books.add(new Book("2296-0597", "0Z7.7", new Author("Universitätsbibliothek Basel"), new Genre("Academic Journal")));
        books.add(new Book("2296-0597", "0Z7.7", new Author("Universitätsbibliothek Basel"), new Genre("Academic Journal")));
        
        // ADD MORE BOOKS BELOW THIS LINE:
        books.add(new Book("978-0451524935", "1984", new Author("George Orwell"), new Genre("Dystopian Fiction")));
        books.add(new Book("978-0061120084", "To Kill a Mockingbird", new Author("Harper Lee"), new Genre("Southern Gothic")));
        books.add(new Book("978-0743273565", "The Great Gatsby", new Author("F. Scott Fitzgerald"), new Genre("Classic Literature")));
        books.add(new Book("978-0544003415", "The Lord of the Rings", new Author("J.R.R. Tolkien"), new Genre("Fantasy")));
        books.add(new Book("978-0439023481", "The Hunger Games", new Author("Suzanne Collins"), new Genre("Young Adult Dystopian")));
        books.add(new Book("978-1400033416", "Beloved", new Author("Toni Morrison"), new Genre("Historical Fiction")));
        books.add(new Book("978-0141439518", "Pride and Prejudice", new Author("Jane Austen"), new Genre("Romance")));
        books.add(new Book("978-0156012195", "The Little Prince", new Author("Antoine de Saint-Exupéry"), new Genre("Philosophical Fiction")));
        books.add(new Book("978-0547249643", "The Book Thief", new Author("Markus Zusak"), new Genre("Historical Fiction")));
        books.add(new Book("978-0062315007", "The Alchemist", new Author("Paulo Coelho"), new Genre("Quest Fiction")));
        books.add(new Book("978-0140283334", "Lord of the Flies", new Author("William Golding"), new Genre("Allegorical Novel")));
        books.add(new Book("978-0141187761", "Animal Farm", new Author("George Orwell"), new Genre("Political Satire")));
        books.add(new Book("978-0316769174", "The Catcher in the Rye", new Author("J.D. Salinger"), new Genre("Coming-of-Age")));
        books.add(new Book("978-1451673319", "Fahrenheit 451", new Author("Ray Bradbury"), new Genre("Dystopian Fiction")));
        books.add(new Book("978-0679783268", "Persuasion", new Author("Jane Austen"), new Genre("Romance")));
        books.add(new Book("978-0143105428", "Brave New World", new Author("Aldous Huxley"), new Genre("Dystopian Fiction")));
        books.add(new Book("978-0060850524", "The Chronicles of Narnia", new Author("C.S. Lewis"), new Genre("Fantasy")));
        books.add(new Book("978-0439139595", "Harry Potter and the Philosopher's Stone", new Author("J.K. Rowling"), new Genre("Fantasy")));
        books.add(new Book("978-0385472579", "Things Fall Apart", new Author("Chinua Achebe"), new Genre("Historical Fiction")));
        books.add(new Book("978-0143105954", "One Hundred Years of Solitude", new Author("Gabriel García Márquez"), new Genre("Magical Realism")));
        // ... (other books remain the same)
    }
    
    private Student currentStudent;
    private JTextArea output;
    private JPanel mainPanel;
    private JPanel loginPanel;
    private CardLayout cardLayout;
    private JTabbedPane tabbedPane;

    public LibraryUI() {
        setTitle("Rollins College Library Management System");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        // Use CardLayout to switch between login and main application
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        createLoginPanel();
        createApplicationPanel();
        
        add(mainPanel);
        
        // Show login panel first
        cardLayout.show(mainPanel, "Login");
        
        // Set application icon
        ImageIcon icon = new ImageIcon();
        setIconImage(icon.getImage());
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(new BorderLayout());
        loginPanel.setBackground(Color.WHITE); // White background for main area
        
        // Header with Rollins branding
        JPanel headerPanel = createRollinsHeaderPanel();
        loginPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Login Form
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridBagLayout());
        formPanel.setBackground(Color.WHITE); // White background
        formPanel.setBorder(BorderFactory.createEmptyBorder(30, 100, 50, 100));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel loginTitle = new JLabel("Student Login / Register", JLabel.CENTER);
        loginTitle.setFont(new Font("Segoe UI", Font.BOLD, 24));
        loginTitle.setForeground(new Color(0, 51, 102)); // Rollins blue text
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(loginTitle, gbc);
        
        JLabel idLabel = new JLabel("Student ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idLabel.setForeground(new Color(0, 51, 102)); // Rollins blue text
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(idLabel, gbc);
        
        JTextField idField = new JTextField(20);
        idField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        idField.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(idField, gbc);
        
        JLabel nameLabel = new JLabel("Name (for new students):");
        nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameLabel.setForeground(new Color(0, 51, 102)); // Rollins blue text
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(nameLabel, gbc);
        
        JTextField nameField = new JTextField(20);
        nameField.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        nameField.setPreferredSize(new Dimension(200, 35));
        gbc.gridx = 1;
        gbc.gridy = 2;
        formPanel.add(nameField, gbc);
        
        JButton loginBtn = new JButton("Login / Register");
        loginBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginBtn.setBackground(new Color(0, 51, 102)); // Rollins blue button
        loginBtn.setForeground(Color.WHITE); // White text
        loginBtn.setFocusPainted(false);
        loginBtn.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        formPanel.add(loginBtn, gbc);
        
        loginPanel.add(formPanel, BorderLayout.CENTER);
        
        // Footer
        JPanel footerPanel = new JPanel();
        footerPanel.setBackground(new Color(0, 51, 102)); // Rollins blue footer
        footerPanel.setPreferredSize(new Dimension(900, 40));
        JLabel footerLabel = new JLabel("© 2025 Rollins College Library Management System", JLabel.CENTER);
        footerLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        footerLabel.setForeground(Color.WHITE); // White text
        footerPanel.add(footerLabel);
        loginPanel.add(footerPanel, BorderLayout.SOUTH);
        
        // Login button action
        loginBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            if (id.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter a Student ID", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            performLogin(id, nameField.getText().trim());
            idField.setText("");
            nameField.setText("");
        });
        
        mainPanel.add(loginPanel, "Login");
    }

    private JPanel createRollinsHeaderPanel() {
        JPanel headerPanel = new JPanel(new BorderLayout());
        headerPanel.setBackground(new Color(0, 51, 102)); // Rollins blue background
        headerPanel.setPreferredSize(new Dimension(900, 120));
        headerPanel.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.YELLOW)); // Yellow border
        
        // Logo panel
        JPanel logoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        logoPanel.setBackground(new Color(0, 51, 102)); // Rollins blue background
        logoPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        
        // Create Rollins logo with yellow text on blue background
        JLabel logoLabel = new JLabel("ROLLINS COLLEGE");
        logoLabel.setFont(new Font("Georgia", Font.BOLD, 28));
        logoLabel.setForeground(Color.YELLOW); // Yellow text
        
        logoPanel.add(logoLabel);
        
        // Library title panel
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(new Color(0, 51, 102)); // Rollins blue background
        titlePanel.setBorder(BorderFactory.createEmptyBorder(15, 0, 15, 20));
        
        JLabel titleLabel = new JLabel("Olin Library Management System");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titleLabel.setForeground(Color.YELLOW); // Yellow text
        
        titlePanel.add(titleLabel);
        
        headerPanel.add(logoPanel, BorderLayout.WEST);
        headerPanel.add(titlePanel, BorderLayout.EAST);
        
        return headerPanel;
    }

    private void createApplicationPanel() {
        JPanel appPanel = new JPanel(new BorderLayout());
        appPanel.setBackground(Color.WHITE); // White background for main area
        
        // Header with Rollins branding and user info
        JPanel headerPanel = createRollinsHeaderPanel();
        
        // Add user info to the header
        JPanel userPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        userPanel.setOpaque(false);
        userPanel.setBorder(BorderFactory.createEmptyBorder(5, 0, 5, 20));
        
        JLabel userLabel = new JLabel();
        userLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        userLabel.setForeground(Color.YELLOW); // Yellow text (on blue header)
        userPanel.add(userLabel);
        
        JButton logoutBtn = new JButton("Logout");
        logoutBtn.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        logoutBtn.setBackground(Color.YELLOW); // Yellow button
        logoutBtn.setForeground(new Color(0, 51, 102)); // Blue text
        logoutBtn.setFocusPainted(false);
        logoutBtn.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        userPanel.add(logoutBtn);
        
        // Add user panel to header
        ((BorderLayout) headerPanel.getLayout()).addLayoutComponent(userPanel, BorderLayout.SOUTH);
        headerPanel.add(userPanel, BorderLayout.SOUTH);
        
        appPanel.add(headerPanel, BorderLayout.NORTH);
        
        // Create tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.setBackground(Color.WHITE);
        tabbedPane.setForeground(new Color(0, 51, 102)); // Blue text for tabs
        
        // Create panels for each tab
        createBooksTab();
        createLoanTab();
        createReturnTab();
        createSearchTab();
        createMyLoansTab();
        
        appPanel.add(tabbedPane, BorderLayout.CENTER);
        
        // Logout action
        logoutBtn.addActionListener(e -> {
            currentStudent = null;
            output.setText("");
            cardLayout.show(mainPanel, "Login");
        });
        
        // Store user label reference for updating
        appPanel.putClientProperty("userLabel", userLabel);
        
        mainPanel.add(appPanel, "Application");
    }

    private void createBooksTab() {
        JPanel booksPanel = new JPanel(new BorderLayout());
        booksPanel.setBackground(Color.WHITE);
        booksPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Output area for books
        output = new JTextArea();
        output.setEditable(false);
        output.setFont(new Font("Consolas", Font.PLAIN, 14));
        output.setBackground(Color.WHITE);
        output.setForeground(Color.BLACK);
        
        JScrollPane scrollPane = new JScrollPane(output);
        scrollPane.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(new Color(0, 51, 102)),
            "Library Books"
        ));
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(Color.WHITE);
        
        JButton showBooksBtn = new JButton("Show All Books");
        showBooksBtn.setBackground(new Color(0, 51, 102));
        showBooksBtn.setForeground(Color.WHITE);
        showBooksBtn.addActionListener(e -> showBooks());
        
        JButton addBookBtn = new JButton("Add New Book");
        addBookBtn.setBackground(new Color(0, 51, 102));
        addBookBtn.setForeground(Color.WHITE);
        addBookBtn.addActionListener(e -> addBook());
        
        buttonPanel.add(showBooksBtn);
        buttonPanel.add(addBookBtn);
        
        booksPanel.add(buttonPanel, BorderLayout.NORTH);
        booksPanel.add(scrollPane, BorderLayout.CENTER);
        
        tabbedPane.addTab("Books", booksPanel);
    }

    private void createLoanTab() {
        JPanel loanPanel = new JPanel(new BorderLayout());
        loanPanel.setBackground(Color.WHITE);
        loanPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("Loan a Book");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);
        
        JLabel idLabel = new JLabel("Book ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(idLabel, gbc);
        
        JTextField bookIdField = new JTextField(20);
        bookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(bookIdField, gbc);
        
        JButton loanBtn = new JButton("Loan Book");
        loanBtn.setBackground(new Color(0, 51, 102));
        loanBtn.setForeground(Color.WHITE);
        loanBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(loanBtn, gbc);
        
        // Result area
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(240, 240, 240));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(BorderFactory.createTitledBorder("Loan Result"));
        
        loanBtn.addActionListener(e -> {
            if (currentStudent == null) {
                resultArea.setText("Please log in first.\n");
                return;
            }
            
            String id = bookIdField.getText().trim();
            if (id.isEmpty()) {
                resultArea.setText("Please enter a Book ID.\n");
                return;
            }
            
            Book book = findBook(id);
            if (book == null) {
                resultArea.setText("❌ Book not found.\n");
                return;
            }
            if (!book.isAvailable()) {
                resultArea.setText("❌ Book is already on loan.\n");
                return;
            }

            loans.add(new LoanBook(book, currentStudent, LocalDate.now().toString()));
            resultArea.setText("✓ " + currentStudent.getName() + " borrowed \"" + book.getTitle() + "\"\n");
            LibraryDataManager.saveData(books, students, loans);
            bookIdField.setText("");
        });
        
        loanPanel.add(formPanel, BorderLayout.NORTH);
        loanPanel.add(resultScroll, BorderLayout.CENTER);
        
        tabbedPane.addTab("Loan Book", loanPanel);
    }

    private void createReturnTab() {
        JPanel returnPanel = new JPanel(new BorderLayout());
        returnPanel.setBackground(Color.WHITE);
        returnPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("Return a Book");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);
        
        JLabel idLabel = new JLabel("Book ID:");
        idLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        idLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(idLabel, gbc);
        
        JTextField bookIdField = new JTextField(20);
        bookIdField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(bookIdField, gbc);
        
        JButton returnBtn = new JButton("Return Book");
        returnBtn.setBackground(new Color(0, 51, 102));
        returnBtn.setForeground(Color.WHITE);
        returnBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(returnBtn, gbc);
        
        // Result area
        JTextArea resultArea = new JTextArea(5, 30);
        resultArea.setEditable(false);
        resultArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultArea.setBackground(new Color(240, 240, 240));
        JScrollPane resultScroll = new JScrollPane(resultArea);
        resultScroll.setBorder(BorderFactory.createTitledBorder("Return Result"));
        
        returnBtn.addActionListener(e -> {
            String id = bookIdField.getText().trim();
            if (id.isEmpty()) {
                resultArea.setText("Please enter a Book ID.\n");
                return;
            }
            
            Book book = findBook(id);
            if (book != null && !book.isAvailable()) {
                book.setAvailable(true);
                loans.removeIf(loan -> loan.getBook().getId().equals(book.getId()));
                resultArea.setText("✓ Returned book: " + book.getTitle() + "\n");
                LibraryDataManager.saveData(books, students, loans);
                bookIdField.setText("");
            } else {
                resultArea.setText("❌ Book not found or not on loan.\n");
            }
        });
        
        returnPanel.add(formPanel, BorderLayout.NORTH);
        returnPanel.add(resultScroll, BorderLayout.CENTER);
        
        tabbedPane.addTab("Return Book", returnPanel);
    }

    private void createSearchTab() {
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.setBackground(Color.WHITE);
        searchPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBackground(Color.WHITE);
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel titleLabel = new JLabel("Search Books");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);
        
        JLabel searchLabel = new JLabel("Search (title, author, genre):");
        searchLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        searchLabel.setForeground(new Color(0, 51, 102));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        formPanel.add(searchLabel, gbc);
        
        JTextField searchField = new JTextField(20);
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        gbc.gridx = 1;
        gbc.gridy = 1;
        formPanel.add(searchField, gbc);
        
        JButton searchBtn = new JButton("Search");
        searchBtn.setBackground(new Color(0, 51, 102));
        searchBtn.setForeground(Color.WHITE);
        searchBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        formPanel.add(searchBtn, gbc);
        
        // Results area
        JTextArea resultsArea = new JTextArea(15, 30);
        resultsArea.setEditable(false);
        resultsArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        resultsArea.setBackground(Color.WHITE);
        JScrollPane resultsScroll = new JScrollPane(resultsArea);
        resultsScroll.setBorder(BorderFactory.createTitledBorder("Search Results"));
        
        searchBtn.addActionListener(e -> {
            String query = searchField.getText().trim();
            if (query.isEmpty()) {
                resultsArea.setText("Please enter a search term.\n");
                return;
            }
            
            query = query.toLowerCase();
            resultsArea.setText("--- SEARCH RESULTS ---\n\n");
            boolean found = false;
            
            for (Book b : books) {
                if (b.getTitle().toLowerCase().contains(query) ||
                    b.getAuthor().getName().toLowerCase().contains(query) ||
                    b.getGenre().getName().toLowerCase().contains(query)) {
                    resultsArea.append(b + "\n\n");
                    found = true;
                }
            }
            
            if (!found) resultsArea.append("No matches found.\n");
        });
        
        searchPanel.add(formPanel, BorderLayout.NORTH);
        searchPanel.add(resultsScroll, BorderLayout.CENTER);
        
        tabbedPane.addTab("Search", searchPanel);
    }

    private void createMyLoansTab() {
        JPanel myLoansPanel = new JPanel(new BorderLayout());
        myLoansPanel.setBackground(Color.WHITE);
        myLoansPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JTextArea loansArea = new JTextArea(15, 30);
        loansArea.setEditable(false);
        loansArea.setFont(new Font("Consolas", Font.PLAIN, 12));
        loansArea.setBackground(Color.WHITE);
        JScrollPane loansScroll = new JScrollPane(loansArea);
        loansScroll.setBorder(BorderFactory.createTitledBorder("My Current Loans"));
        
        JButton refreshBtn = new JButton("Refresh My Loans");
        refreshBtn.setBackground(new Color(0, 51, 102));
        refreshBtn.setForeground(Color.WHITE);
        refreshBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        refreshBtn.addActionListener(e -> {
            if (currentStudent == null) {
                loansArea.setText("Please log in first.\n");
                return;
            }
            
            loansArea.setText("--- MY LOANS ---\n\n");
            boolean hasLoans = false;
            
            for (LoanBook loan : loans) {
                if (loan.getStudent().getId().equals(currentStudent.getId())) {
                    loansArea.append(loan + "\n\n");
                    hasLoans = true;
                }
            }
            
            if (!hasLoans) {
                loansArea.append("You have no books on loan.\n");
            }
        });
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(Color.WHITE);
        buttonPanel.add(refreshBtn);
        
        myLoansPanel.add(buttonPanel, BorderLayout.NORTH);
        myLoansPanel.add(loansScroll, BorderLayout.CENTER);
        
        tabbedPane.addTab("My Loans", myLoansPanel);
    }

    private void performLogin(String id, String name) {
        Student found = findStudent(id);
        if (found != null) {
            currentStudent = found;
            output.append("Welcome back, " + currentStudent.getName() + "!\n");
        } else {
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Please enter your name for registration", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            currentStudent = new Student(id, name);
            students.add(currentStudent);
            output.append("Registered new student: " + name + " (ID: " + id + ")\n");
        }
        
        // Update user label in application panel
        JPanel appPanel = (JPanel) mainPanel.getComponent(1);
        JLabel userLabel = (JLabel) appPanel.getClientProperty("userLabel");
        if (userLabel != null) {
            userLabel.setText("Logged in as: " + currentStudent.getName() + " (" + currentStudent.getId() + ")");
        }
        
        // Switch to application view
        cardLayout.show(mainPanel, "Application");
        output.append("Use the tabs above to manage books.\n");
    }

    // Rest of the methods remain the same but simplified
    private void addBook() {
        JTextField idField = new JTextField();
        JTextField titleField = new JTextField();
        JTextField authorField = new JTextField();
        JTextField genreField = new JTextField();
        
        Object[] message = {
            "Book ID:", idField,
            "Title:", titleField,
            "Author:", authorField,
            "Genre:", genreField
        };
        
        int option = JOptionPane.showConfirmDialog(this, message, "Add New Book", 
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        
        if (option == JOptionPane.OK_OPTION) {
            String id = idField.getText().trim();
            String title = titleField.getText().trim();
            String authorName = authorField.getText().trim();
            String genreName = genreField.getText().trim();
            
            if (id.isEmpty() || title.isEmpty() || authorName.isEmpty() || genreName.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            books.add(new Book(id, title, new Author(authorName), new Genre(genreName)));
            output.append("✓ Added book: " + title + "\n");
            LibraryDataManager.saveData(books, students, loans);
        }
    }

    private void showBooks() {
        output.setText(""); // Clear previous content
        output.append("--- ALL BOOKS ---\n\n");
        if (books.isEmpty()) {
            output.append("No books available.\n");
        } else {
            for (Book b : books) {
                output.append(b + "\n\n");
            }
        }
    }

    private Book findBook(String id) {
        for (Book b : books)
            if (b.getId().equalsIgnoreCase(id)) return b;
        return null;
    }

    private Student findStudent(String id) {
        for (Student s : students)
            if (s.getId().equalsIgnoreCase(id)) return s;
        return null;
    }
}