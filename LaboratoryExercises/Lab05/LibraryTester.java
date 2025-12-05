package LaboratoryExercises.Lab05;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

class Author implements Comparable<Author> {
    private String name;
    private int counter;

    public Author(String name) {
        this.name = name;
        this.counter = 0;
    }

    @Override
    public int compareTo(Author o) {
        int comparison = Integer.compare(o.counter, this.counter);
        if (comparison == 0) {
            return this.name.compareTo(o.name);
        }
        return comparison;
    }

    @Override
    public String toString() {
        return String.format("%s - %d", name, counter);
    }

    public String getName() {
        return name;
    }

    public int getCounter() {
        return counter;
    }

    public void incrementCounter() {
        this.counter += 1;
    }
}

class Book implements Comparable<Book> {
    private String isbn;
    private String title;
    private Author author;
    private int year;
    private int editions;
    private int freeEditions;
    private int timesBorrowed;

    public Book(String isbn, String title, Author author, int year) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.year = year;
        this.editions = 1;
        this.freeEditions = 1;
        this.timesBorrowed = 0;
    }

    public int getEditions() {
        return editions;
    }

    public void setEditions(int editions) {
        this.editions = editions;
    }

    public int getFreeEditions() {
        return freeEditions;
    }

    public void setFreeEditions(int freeEditions) {
        this.freeEditions = freeEditions;
    }

    public int getTimesBorrowed() {
        return timesBorrowed;
    }

    public void setTimesBorrowed(int timesBorrowed) {
        this.timesBorrowed = timesBorrowed;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public int compareTo(Book o) {
        int comparison = Integer.compare(o.timesBorrowed, this.timesBorrowed);
        if (comparison == 0) {
            return Integer.compare(this.year, o.year);
        }
        return comparison;
    }

    @Override
    public String toString() {
        return String.format("%s - \"%s\" by %s (%d), available: %d, total borrows: %d", isbn, title, author.getName(), year, freeEditions, timesBorrowed);
    }
}

class Member implements Comparable<Member> {
    private String id;
    private String name;
    private int borrowedNow;
    private int borrowedTotal;

    public Member(String id, String name) {
        this.id = id;
        this.name = name;
        this.borrowedNow = 0;
        this.borrowedTotal = 0;
    }

    public String getId() {
        return id;
    }

    public int getBorrowedNow() {
        return borrowedNow;
    }

    public void setBorrowedNow(int borrowedNow) {
        this.borrowedNow = borrowedNow;
    }

    public int getBorrowedTotal() {
        return borrowedTotal;
    }

    public void setBorrowedTotal(int borrowedTotal) {
        this.borrowedTotal = borrowedTotal;
    }

    @Override
    public int compareTo(Member o) {
        int comparison = Integer.compare(o.borrowedNow, this.borrowedNow);
        if (comparison == 0) {
            return this.name.compareTo(o.name);
        }
        return comparison;
    }

    @Override
    public String toString() {
        return String.format("%s (%s) - borrowed now: %d, total borrows: %d", name, id, borrowedNow, borrowedTotal);
    }
}

class LibrarySystem {
    private String name;
    private Map<Member, Set<Book>> borrowedBooks;
    private Map<String, Book> books;
    private Map<String, Member> members;
    private Map<Book, List<Member>> waitlist;
    private Map<String, Author> authors;

    public LibrarySystem(String name) {
        this.name = name;
        this.borrowedBooks = new HashMap<>();
        this.books = new TreeMap<>();
        this.members = new TreeMap<>();
        this.waitlist = new LinkedHashMap<>();
        this.authors = new HashMap<>();
    }

    public void registerMember(String id, String fullName) {
        members.put(id, new Member(id, fullName));
        borrowedBooks.put(members.get(id), new HashSet<>());
    }

    public void addBook(String isbn, String title, String authorName, int year) {
        Author author = authors.computeIfAbsent(authorName, Author::new);
        if (books.containsKey(isbn)) {
            Book b = books.get(isbn);
            b.setEditions(b.getEditions() + 1);
            b.setFreeEditions(b.getFreeEditions() + 1);
        } else {
            Book b = new Book(isbn, title, author, year);
            books.put(isbn, b);
        }
    }

    public void borrowBook(String memberId, String isbn) {
        if (!books.containsKey(isbn) || !members.containsKey(memberId))
            return;
        Book book = books.get(isbn);
        Member member = members.get(memberId);
        if (book.getFreeEditions() > 0) {
            borrowedBooks.get(member).add(book);
            book.setFreeEditions(book.getFreeEditions() - 1);
            book.setTimesBorrowed(book.getTimesBorrowed() + 1);
            member.setBorrowedNow(member.getBorrowedNow() + 1);
            member.setBorrowedTotal(member.getBorrowedTotal() + 1);
            authors.put(book.getAuthor().getName(), book.getAuthor());
            book.getAuthor().incrementCounter();
            return;
        }
        waitlist.computeIfAbsent(book, v -> new ArrayList<>());
        if (!waitlist.get(book).contains(member)) {
            waitlist.get(book).add(member);
        }
    }

    public void returnBook(String memberId, String isbn) {
        Book book = books.get(isbn);
        Member member = members.get(memberId);
        borrowedBooks.get(member).remove(book);
        book.setFreeEditions(book.getFreeEditions() + 1);
        member.setBorrowedNow(member.getBorrowedNow() - 1);
        List<Member> list = waitlist.get(book);
        if (list != null && !list.isEmpty()) {
            Member next = list.remove(0);
            borrowBook(next.getId(), isbn);
            if (list.isEmpty())
                waitlist.remove(book);
        }
    }

    public void printMembers() {
        List<Member> m = members.values().stream().sorted().collect(Collectors.toList());
        for (Member member : m) {
            System.out.println(member);
        }
    }

    public void printBooks() {
        List<Book> b = books.values().stream().sorted().collect(Collectors.toList());
        for (Book book : b) {
            System.out.println(book);
        }
    }

    public void printBookCurrentBorrowers(String isbn) {
        Set<String> borrowers = new TreeSet<>();
        for (Member member : borrowedBooks.keySet()) {
            if (borrowedBooks.get(member).contains(books.get(isbn))) {
                borrowers.add(member.getId());
            }
        }
        StringBuilder sb = new StringBuilder();
        for (String borrower : borrowers) {
            sb.append(borrower + ", ");
        }
        System.out.println(sb.toString().substring(0, sb.toString().length() - 2));
    }

    public void printTopAuthors() {
        Set<Author> authorSet = new TreeSet<>(authors.values());
        for (Author author : authorSet) {
            System.out.println(author);
        }
    }
}

public class LibraryTester {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try {
            String libraryName = br.readLine();
            //   System.out.println(libraryName); //test
            if (libraryName == null) return;

            libraryName = libraryName.trim();
            LibrarySystem lib = new LibrarySystem(libraryName);

            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.equals("END")) break;
                if (line.isEmpty()) continue;

                String[] parts = line.split(" ");

                switch (parts[0]) {

                    case "registerMember": {
                        lib.registerMember(parts[1], parts[2]);
                        break;
                    }

                    case "addBook": {
                        String isbn = parts[1];
                        String title = parts[2];
                        String author = parts[3];
                        int year = Integer.parseInt(parts[4]);
                        lib.addBook(isbn, title, author, year);
                        break;
                    }

                    case "borrowBook": {
                        lib.borrowBook(parts[1], parts[2]);
                        break;
                    }

                    case "returnBook": {
                        lib.returnBook(parts[1], parts[2]);
                        break;
                    }

                    case "printMembers": {
                        lib.printMembers();
                        break;
                    }

                    case "printBooks": {
                        lib.printBooks();
                        break;
                    }

                    case "printBookCurrentBorrowers": {
                        lib.printBookCurrentBorrowers(parts[1]);
                        break;
                    }

                    case "printTopAuthors": {
                        lib.printTopAuthors();
                        break;
                    }

                    default:
                        break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
