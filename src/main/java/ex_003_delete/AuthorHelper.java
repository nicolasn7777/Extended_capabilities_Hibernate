package ex_003_delete;


import ex_003_delete.entity.Author;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class AuthorHelper {

    private SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    public List<Author> getAuthorList(){
        Session session = sessionFactory.openSession();


        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Author.class);

        Root<Author> root = cq.from(Author.class);

        Selection[] selections = {root.get("id"), root.get("name")};

        cq.select(cb.construct(Author.class, selections));


        Query query = session.createQuery(cq);


        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id);
        session.close();
        return author;
    }

    public Author addAuthor(Author author){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(author);

        session.getTransaction().commit();

        session.close();


        return author;

    }

    public void deleteById(long id) {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        Author author = session.get(Author.class, id);

        session.delete(author);

        session.getTransaction().commit();

        session.close();

    }

    public void deleteCriteria(String type)  {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaDelete<Author> cd = cb.createCriteriaDelete(Author.class);

        Root<Author> root = cd.from(Author.class);

        cd.where(cb.like(root.<String>get("name"), type));


        Query query = session.createQuery(cd);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

    public void deleteCriteriaLogic(String type1, String type2)  {
        Session session = sessionFactory.openSession();

        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaDelete<Author> cd = cb.createCriteriaDelete(Author.class);

        Root<Author> root = cd.from(Author.class);

        cd.where(cb.or(
                cb.and(
                        cb.like(root.get("name"), type1),
                        cb.like(root.get("lastName"), type2)
                ),
                cb.equal(root.get("name"), type2)
        ));


        Query query = session.createQuery(cd);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }

}
