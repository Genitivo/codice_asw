package controller;

import facade.StudentFacade;
import model.Student;
import model.Prestito;

import javax.ejb.EJB;
import javax.ejb.*;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.util.Date;
import java.util.List;

@ManagedBean(name="studentController")
@SessionScoped
public class StudentController {

    @EJB(name="sFacade")
    private StudentFacade studentFacade;

    private String name;
    private Student student;
    private List<Student> students;
    private String university;
    private Date registrationDate;
    private List<Prestito> prestiti;


    public String createStudent(){

        this.registrationDate = new Date();

        this.student = studentFacade.createStudent(name, university, registrationDate);

        return "student";
    }


    public void addPrestito(Student student, Prestito prestito) {
        this.prestiti = studentFacade.addPrestito(student,prestito);
    }

    public String findStudent(Long id){
        this.student = studentFacade.getStudent(id);
        return "student";

    }

    public String allStudents() {
        this.students = studentFacade.getAllStudents();
        return "students";
    }

    public String deleteStudent(Long id){
        studentFacade.deleteStudent(id);
        this.students = studentFacade.getAllStudents();
        return "students";
    }

    /* studentBooks deve essere creato nella classe studentFacade. 
     * Ho messo studentPrestiti invece di studentBooks, in quanto non so come � strutturato il database
     * (se per esempio esiste l'attributo Books)
    */
    public String studentBooks(Student s){
        this.prestiti = studentFacade.studentPrestiti(s);

        return "studentPrestiti";
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> bookList) {
        this.prestiti = bookList;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public void setStudentFacade(StudentFacade studentFacade) {
        this.studentFacade = studentFacade;
    }

    public StudentFacade getStudentFacade() {return studentFacade;}

}
