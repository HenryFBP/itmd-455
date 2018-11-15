package me.henryfbp.sqlitelab;

public class Book {
    private Long id;

    private String title;

    private String author;

    public Book() {
    }

    public Book(String title, String author) {
        super();
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public Book setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Book setTitle(String title) {
        this.title = title;
        return this;

    }

    public String getAuthor() {
        return author;
    }

    public Book setAuthor(String author) {
        this.author = author;
        return this;
    }
    //getters & setters

    @Override
    public String toString() {
        return String.format("%s [id=%02d, title=%s, author=%s]",
                getClass().getSimpleName(), id, title, author);
    }

    public Boolean equals(Book b) {
        return b.getTitle().equals(getTitle()) &&
                b.getAuthor().equals(getAuthor());
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Book) {
            return ((Book) o).equals(this);
        } else {
            return o.equals(this);
        }
    }
}
