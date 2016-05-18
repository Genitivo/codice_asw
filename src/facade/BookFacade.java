package facade;

import model.Book;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.sql.Date;
import java.util.List;

@Stateless(name="bFacade")
public class BookFacade {

    @PersistenceContext(unitName = "libreria_asw")
    private EntityManager em;

    public Book createBook(String title, String author, Date publicationTime) {

        Book book = new Book(title,author,publicationTime);
        em.persist(book);
        return book;
    }

    public Book getBook(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> getAllBooks() {
        return em.createQuery("SELECT book FROM Book book", Book.class).getResultList();
    }

    public void deleteBook(Long id){
        em.remove(getBook(id));
    }


    public void changeStatus(Book b) {
        if(b.getStatus().equals("Libero"))
            b.setStatus("Prenotato");
        else
            b.setStatus("Libero");

        em.merge(b);
    }
}