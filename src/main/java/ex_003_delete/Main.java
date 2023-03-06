package ex_003_delete;


import ex_003_delete.entity.Author;
import org.jboss.logging.Logger;

import java.util.List;

public class Main {

    private static final Logger LOG = Logger.getLogger( AuthorHelper.class.getName() );

    public static void main(String[] args) {
        AuthorHelper ah = new AuthorHelper();

        //ah.deleteById(100);
        //ah.deleteCriteria();
        ah.deleteCriteriaLogic("Mykola", "Gogol");

        List<Author> authors = ah.getAuthorList();

        for (Author author : authors) {
            LOG.debug(author.getId() + " " + author.getName());
        }

    }

}
