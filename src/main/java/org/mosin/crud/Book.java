package org.mosin.crud;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "test.books")
public class Book implements Serializable {

    private static long serialVersionUID = -8706689714326132798L;
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", length = 100)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "author", length = 100, updatable = false)
    private String author;

    @Column(name = "isbn", length = 20)
    private String isbn;

    @Column(name = "printYear", length = 4)
    private String printYear;

    @Column(name = "readAlready")
    private Boolean readAlready;


    //Important to Hibernate!
    @SuppressWarnings("UnusedDeclaration")
    public Book() {

    }


    public Book(String title, String description, String author, String isbn, String printYear, Boolean readAlready) {
        //this.setId(-1L);
        this.title = title;
        this.description = description;
        this.author = author;
        this.isbn = isbn;
        this.printYear = printYear;
        this.readAlready = readAlready;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPrintYear() {
        return printYear;
    }

    public void setPrintYear(String printYear) {
        this.printYear = printYear;
    }

    public Boolean getReadAlready() {
        return readAlready;
    }

    public void setReadAlready(Boolean readAlready) {
        this.readAlready = readAlready;
    }

}
