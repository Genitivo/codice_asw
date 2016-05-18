package facade;

import model.Book;
import model.Student;
import model.Prestito;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Stateless(name="sFacade")
public class StudentFacade {

    @PersistenceContext(unitName = "libreria_asw")
    private EntityManager em;

    public Student createStudent(String name, String university, Date registrationDate) {

        Student student = new Student(name, university, registrationDate);

        em.persist(student);

        return student;
    }

    public Student getStudent(Long id) {
        return em.find(Student.class,id);
    }

    public List<Student> getAllStudents() {
        return em.createQuery("SELECT student FROM Student student", Student.class).getResultList();
    }

    public void deleteStudent(Long id) {
        Student c = getStudent(id);
        em.remove(c);
    }

    public List<Prestito> studentPrestiti(Student s) {
        Query query = em.createQuery("SELECT p FROM Prestito p WHERE p.student_id=s:s");
        query.setParameter("s",s);

        return query.getResultList();
    }

	public List<Prestito> addPrestito(Student s,Prestito prestito) {
		s.getPrestiti().add(prestito);
        em.merge(s);

		return null;
	}

}