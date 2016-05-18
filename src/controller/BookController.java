package controller;

import facade.BookFacade;
import model.Book;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import java.sql.Date;
import java.util.List;

@ManagedBean(name="bookController")
@SessionScoped
public class BookController {

    @EJB(name="bFacade")
    private BookFacade bookFacade;

    private String title;
    private Date publicationTime;
    private String author;
    private String status;
    private Date addedTime;
    private Book book;

    private List<Book> books;

    public String createBook() {

        this.book = bookFacade.createBook(title,author,publicationTime);

        return "book";
    }

    public String books(){
        this.books = bookFacade.getAllBooks();

        return "books";
    }

    public String findBook(Long id) {
        book = bookFacade.getBook(id);
        return "book";
    }

    public void changeStatus(Book b) {
        this.bookFacade.changeStatus(b);
    }

    public String libraryBooks() {
        this.books = bookFacade.getAllBooks();
        return "books";
    }

    public String deleteBookFromLibrary(Long id){
        bookFacade.deleteBook(id);
        this.books = bookFacade.getAllBooks();
        return "books";
    }

  /*  public String deleteBookFromLibrary(Long id){
        bookFacade.deleteBook(id);
        this.books = bookFacade.getAllBooks();
        return "prestito";
    }*/

    public String giveBack(Book b){
        bookFacade.changeStatus(b);
        return "prestito";
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

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public BookFacade getBookFacade() {
        return bookFacade;
    }

    public void setBookFacade(BookFacade bookFacade) {
        this.bookFacade = bookFacade;
    }

    public List<Book> getBooks() {return books;}

    public void setBooks(List<Book> books) {
        this.books = books;
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



}
