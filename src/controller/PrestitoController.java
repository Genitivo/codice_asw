package controller;

import facade.PrestitoFacade;
import facade.*;
import model.Book;
import model.Prestito;
import model.Student;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@ManagedBean(name="prestitoController")
@SessionScoped
public class PrestitoController {

    @EJB(name="pFacade")
    private PrestitoFacade prestitoFacade;

    private Date creationTime;
    private Date closeTime;
    private List<Book> books;
    private List<Prestito> prestiti;
    private Prestito prestito;
    private Student student_id;
    private Book book_id;

    @ManagedProperty(value="#{studentController}")
    private StudentController studentController;

    @ManagedProperty(value="#{bookController}")
    private BookController bookController;

    public PrestitoController(){
        this.books = new ArrayList<Book>();
    }


    public String createPrestito(Student s){
        this.student_id = s;
        this.creationTime = new Date();
        this.prestito = prestitoFacade.createPrestito(this.creationTime, this.student_id);

        return "newPrestito";
    }

    public String closePrestito(){

        this.prestitoFacade.closePrestito(prestito, books);

        this.prestitoFacade.addPrestito(student_id, prestito);

        this.books.clear();


        return "prestito";
    }

    public String endPrestito(){
        this.closeTime = new Date();
        this.prestitoFacade.endPrestito(prestito,closeTime);
        return "prestito";
    }

    public String allPrestiti(){
        this.prestiti = prestitoFacade.allPrestiti();
        return "prestiti";
    }

    public String findPrestito(Long id){
        this.prestito = this.prestitoFacade.getPrestito(id);
        return "prestito";
    }

    public String addBook(Book b){
        this.bookController.changeStatus(b);
        this.books.add(b);
        return "books";
    }

    public boolean checkPrestito(){
        for(Book b: this.books){
            if(!b.getStatus().equals("Libero"))
                return false;
        }
        return true;
    }


    public Book getBook_id() {
        return book_id;
    }

    public Date getCloseTime() {
        return closeTime;
    }

    public void setCloseTime(Date closeTime) {
        this.closeTime = closeTime;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
    }

    public Student getStudent_id() {
        return student_id;
    }

    public void setStudent_id(Student student_id) {
        this.student_id = student_id;
    }

    public void setBook_id(Book book_id) {
        this.book_id = book_id;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    public void setPrestiti(List<Prestito> prestiti) {
        this.prestiti = prestiti;
    }

    public Prestito getPrestito() {
        return prestito;
    }

    public void setPrestito(Prestito prestito) {
        this.prestito = prestito;
    }

    public PrestitoFacade getPrestitoFacade() {
        return prestitoFacade;
    }

    public void setPrestitoFacade(PrestitoFacade prestitoFacade) {
        this.prestitoFacade = prestitoFacade;
    }

    public StudentController getStudentController() {
        return studentController;
    }

    public void setStudentController(StudentController studentController) {
        this.studentController = studentController;
    }

    public BookController getBookController() {
        return bookController;
    }

    public void setBookController(BookController bookController) {
        this.bookController = bookController;
    }
}