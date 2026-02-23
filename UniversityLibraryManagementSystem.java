package university.library.management.system;

import java.util.ArrayList;
import java.util.Scanner;

public class UniversityLibraryManagementSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = new ArrayList<>();
        ArrayList<Book> books = new ArrayList<>();
        
        boolean running = true;

        while (running) {
            System.out.println("\n--- Welcome in System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Lend Book to Student");
            System.out.println("4. Display Student Info and Borrowed Books");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter student ID: ");
                    int studentId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter first name: ");
                    String firstName = scanner.nextLine();

                    System.out.print("Enter last name: ");
                    String lastName = scanner.nextLine();

                    Student student = new Student(studentId, firstName, lastName);
                    if (student.getId() != -1 && student.getFirstName() != null && student.getLastName() != null) {
                        students.add(student);
                        System.out.println("Student added successfully!");
                    } else {
                        System.out.println("Error to add student");
                    }
                    break;

                case 2:
                    System.out.print("Enter book ID: ");
                    int bookId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter book title: ");
                    String title = scanner.nextLine();

                    System.out.print("Enter publication year: ");
                    int year = scanner.nextInt();
                    scanner.nextLine();

                    Book book = new Book(bookId, title, year);
                    if (book.getBookId() != -1 && book.getTitle() != null && book.getYear() != 0) {
                        books.add(book);
                        System.out.println("Book added successfully!");
                    } else {
                        System.out.println("Error to add book");
                    }
                    break;

                case 3:
                    System.out.print("Enter student ID: ");
                    int sId = scanner.nextInt();
                    scanner.nextLine();

                    System.out.print("Enter book ID: ");
                    int bId = scanner.nextInt();
                    scanner.nextLine();

                    Student targetStudent = null;
                    for (Student s : students) {
                        if (s.getId() == sId) {
                            targetStudent = s;
                            break;
                        }
                    }

                    Book targetBook = null;
                    for (Book b : books) {
                        if (b.getBookId() == bId) {
                            targetBook = b;
                            break;
                        }
                    }

                    if (targetStudent == null || targetBook == null) {
                        System.out.println("Student or book not found!");
                    } else if (targetStudent.getBorrowedBooks().contains(targetBook)) {
                        System.out.println("This student has already borrowed this book!");
                    } else {
                        targetStudent.borrowBook(targetBook);
                        System.out.println("Book lent successfully!");
                    }
                    break;

                case 4:
                    System.out.print("Enter student ID: ");
                    int studentIdToDisplay = scanner.nextInt();
                    scanner.nextLine();

                    Student displayStudent = null;
                    for (Student s : students) {
                        if (s.getId() == studentIdToDisplay) {
                            displayStudent = s;
                            break;
                        }
                    }

                    if (displayStudent == null) {
                        System.out.println("Error: No student found with ID: " + studentIdToDisplay);
                    } else {
                        System.out.println("\nStudent Information:");
                        System.out.println("ID: " + displayStudent.getId());
                        System.out.println("Full Name: " + displayStudent.getFullName());

                        ArrayList<Book> borrowed = displayStudent.getBorrowedBooks();
                        if (borrowed.isEmpty()) {
                            System.out.println("This student hasn't borrowed any books.");
                        } else {
                            System.out.println("Borrowed Books:");
                            for (Book bookItem : borrowed) {
                                System.out.println("- " + bookItem.getTitle() + " (" + bookItem.getYear() + ")");
                            }
                        }
                    }
                    break;

                case 5:
                    running = false;
                    System.out.println("Program END");
                    break;

                default:
                    System.out.println("Invalid option!");
            }
        }

        scanner.close();
    }
}

class Person {
    private int id;
    private String firstName;
    private String lastName;

    public Person(int id, String firstName, String lastName) {
        setId(id);
        setFirstName(firstName);
        setLastName(lastName);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        } else {
            System.out.println("Error: Invalid ID.");
            this.id = -1;
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName != null && firstName.length() > 2) {
            this.firstName = firstName;
        } else {
            System.out.println("Error: First name must be longer than 2 characters.");
            this.firstName = null;
        }
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName != null && lastName.length() > 2) {
            this.lastName = lastName;
        } else {
            System.out.println("Error: Last name must be longer than 2 characters.");
            this.lastName = null;
        }
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }
}

class Student extends Person {
    private ArrayList<Book> borrowedBooks;

    public Student(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
        this.borrowedBooks = new ArrayList<>();
    }

    public void borrowBook(Book book) {
        borrowedBooks.add(book);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}

class Book {
    private int bookId;
    private String title;
    private int year;

    public Book(int bookId, String title, int year) {
        setBookId(bookId);
        setTitle(title);
        setYear(year);
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        if (bookId > 0) {
            this.bookId = bookId;
        } else {
            System.out.println("Error: Invalid book ID.");
            this.bookId = -1;
        }
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title != null && title.length() > 2) {
            this.title = title;
        } else {
            System.out.println("Error: Title must be longer than 2 characters.");
            this.title = null;
        }
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        if (year > 1000 && year <= 2026) {  // سنة منطقية
            this.year = year;
        } else {
            System.out.println("Error: Invalid year (must be between 1000 and 2026).");
            this.year = 0;
        }
    }
}