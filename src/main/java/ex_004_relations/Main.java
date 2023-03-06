package ex_004_relations;


import ex_004_relations.entity.Author;
import ex_004_relations.entity.Book;
import org.jboss.logging.Logger;

import java.util.List;

public class Main {

    private static final Logger LOG = Logger.getLogger( AuthorHelper.class.getName() );

    public static void main(String[] args) {
//        BookHelper helper = new BookHelper();
//
//        List<Book> books = helper.getBookList();
//
//        for (Book book : books) {
//            LOG.debug(book.getName() + " " + book.getAuthor().getName());
//        }

       Author author = new AuthorHelper().getAuthorById(1);

       LOG.debug(author.getName());

       List<Book> authorBooks = author.getBooks();

       for (Book book : authorBooks) {
           LOG.debug(book.getName());
       }

    }

}
