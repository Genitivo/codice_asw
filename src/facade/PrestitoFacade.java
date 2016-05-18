package facade;

import model.Book;
import model.Prestito;
import model.Student;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Michele on 28/04/16.
 */

@Stateless(name="pFacade")
public class PrestitoFacade {

    @PersistenceContext(unitName = "libreria_asw")
    private EntityManager em;

    public Prestito createPrestito(Date creationTime, Student student_id) {
        Prestito p = new Prestito(creationTime, student_id);
        return p;
    }

    public Prestito getPrestito(Long id){
        return em.find(Prestito.class, id);
    }

    public List<Prestito> allPrestiti() {
        return em.createNamedQuery("findAllPrestiti").getResultList();
    }

    public void endPrestito(Prestito prestito, Date endTime) {

        for(Book b : prestito.getBooks()) {
            if (b.getStatus().equals("Prenotato")) {
                b.setStatus("Libero");
                em.merge(b);
            }
        }

        prestito.setEndTime(endTime);
        em.merge(prestito);
    }

    public void addPrestito(Student student, Prestito prestito) {
        if(student.getPrestiti()!=null)
            student.getPrestiti().add(prestito);
        else {
            List<Prestito> prestiti = new ArrayList<>();
            prestiti.add(prestito);
            student.setPrestiti(prestiti);
        }
            em.merge(student);
    }

    public void closePrestito(Prestito prestito, List<Book> books) {
        prestito.setBooks(books);

        em.persist(prestito);
    }
}
