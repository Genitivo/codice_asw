package model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "books")
@NamedQuery(name = "findAllBooks", query = "SELECT b FROM Book b")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date addedTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date publicationTime;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String status;

    public Book(){
    }

    public Book(String title, String author,Date publicationTime) {
        this.publicationTime =  new Date();
        this.addedTime = new Date();
        this.title = title;
        this.author = author;
        this.status = "Libero";

    }

    public Date getAddedTime() {
        return addedTime;
    }

    public void setAddedTime(Date addedTime) {
        this.addedTime = addedTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public Date getPublicationTime() {
        return publicationTime;
    }

    public void setPublicationTime(Date publicationTime) {
        this.publicationTime = publicationTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   /* public Student getStudent() {
        return student_id;
    }

    public void setStudent(Student student) {
        this.student_id = student;
    }
*/
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public String toString() {
        return "Book{" +
                "addedTime=" + addedTime +
                ", id=" + id +
                ", publicationTime=" + publicationTime +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}