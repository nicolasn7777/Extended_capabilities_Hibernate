package ex_004_relations;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.jboss.logging.Logger;

public class HibernateUtil {

    private static SessionFactory factory;
    private static final Logger LOG = Logger.getLogger( HibernateUtil.class.getName() );

    static {
        try {
             factory = new Configuration()
                    .configure()
                    .buildSessionFactory();
        } catch (HibernateException e) {
            LOG.error(e);
        }
    }

    public static SessionFactory getSessionFactory() {
        return factory;
    }

}
