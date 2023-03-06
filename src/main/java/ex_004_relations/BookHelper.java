package ex_004_relations;


import ex_004_relations.entity.Author;
import ex_004_relations.entity.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.Query;
import javax.persistence.criteria.*;
import java.util.List;

public class BookHelper {

    private SessionFactory sessionFactory;

    public BookHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    List<Book> getBookList() {
        Session session = sessionFactory.openSession();

        CriteriaBuilder cb = session.getCriteriaBuilder();

        CriteriaQuery cq = cb.createQuery(Book.class);

        Root<Author> root = cq.from(Book.class);

        cq.select(root);

        Query query = session.createQuery(cq);

        List<Book> bookList = query.getResultList();

        session.close();

        return bookList;
    }

    public Book getBookById(long id) {
        Session session = sessionFactory.openSession();
        Book book = (Book) session.get(Book.class, id);
        return book;
    }

    public Book addBook(Book book){

        Session session = sessionFactory.openSession();

        session.beginTransaction();

        session.save(book);

        session.getTransaction().commit();

        session.close();

        return book;

    }

    public Book setNewBookById(long id, String newName) {
        Session session = sessionFactory.openSession();
        Book book = session.get(Book.class, id);
        book.setName(newName);
        session.beginTransaction();
        session.save(book);
        session.getTransaction().commit();
        session.close();
        return book;
    }
    public void deleteById(long id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Book book = session.get(Book.class, id);
        session.delete(book); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();

    }
    public void deleteByAuthor(Author author) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();

        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaDelete<Book> cd = cb.createCriteriaDelete(Book.class);

        Root<Book> root = cd.from(Book.class);

        cd.where(cb.equal(root.get("author"), author));

        Query query = session.createQuery(cd);
        query.executeUpdate();

        session.getTransaction().commit();

        session.close();
    }
}
