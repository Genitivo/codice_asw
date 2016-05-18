package model;

import javax.persistence.*;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name","university","registrationDate"}))
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String university;

    @OneToMany(mappedBy = "student_id", cascade={CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Prestito> prestiti;

    @Temporal(TemporalType.TIMESTAMP)
    private Date registrationDate;

    public Student(){
    }

    public Student(String name, String university, Date registrationDate){
        this.name = name;
        this.university = university;
        this.registrationDate = registrationDate;
        this.prestiti = new LinkedList<>();
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    public Long getId() {return this.id;}

    public String getName() {return name;}

    public Date getRegistrationDate() {return registrationDate;}

    public void setName(String name) {this.name = name;}

    public void setRegistrationDate(Date registrationDate) {this.registrationDate = registrationDate;}


    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

}



