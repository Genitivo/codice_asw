package model;

/**
 * Created by Michele on 28/04/16.
 */
import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name = "prestiti")
@NamedQuery(name = "findAllPrestiti", query = "SELECT p FROM Prestito p")
public class Prestito {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    private Date creationTime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date endTime;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Book> books;

    @ManyToOne
    private Student student_id;

    public Prestito(){}

    public Prestito(Date creationTime, Student student_id) {
        this.creationTime = creationTime;
        this.student_id = student_id;
        this.books = new LinkedList<>();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Long getId() {
        return id;
    }

    public Student getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Student student) {
        this.student_id = student;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }
}
