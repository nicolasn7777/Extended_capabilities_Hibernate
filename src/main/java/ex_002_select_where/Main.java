package ex_002_select_where;

import ex_002_select_where.entity.Author;
import org.jboss.logging.Logger;

import java.util.List;

public class Main {

    private static final Logger LOG = Logger.getLogger( AuthorHelper.class.getName() );

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();
        Author[] authors = {new Author("Kidruk"), new Author("Jadan"), new Author("Shevchenko")};

        for (Author author : authors) {
            ah.addAuthor(author);
        }

        List<Author> authorList = ah.getAuthorListSelection("Jad");

        for (Author author : authorList) {
            LOG.debug(author.getId() + " " + author.getName() + " " + author.getLastName());
        }

//        Author author = ah.getAuthorById(5);
//        LOG.debug(author.getName());
    }

}
