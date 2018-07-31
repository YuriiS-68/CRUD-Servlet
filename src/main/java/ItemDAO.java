
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

import java.util.List;

class ItemDAO {

    private static SessionFactory sessionFactory;

    private static final String SQL_GET_ITEM_BY_ID = "SELECT * FROM ITEM WHERE ID = ?";
    private static final String SQL_GET_ITEM_ALL = "SELECT * FROM ITEM";

    Item save(Item item){

        Transaction transaction = null;
        try(Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();

            transaction.begin();

            session.save(item);

            transaction.commit();

        }catch (HibernateException e){

            if (transaction != null) {
                transaction.rollback();
            }

            System.err.println("Operation failed");
            throw e;
        }
        return item;
    }

    @SuppressWarnings("unchecked")
    List<Item> getAllFiles(){

        List<Item> items = null;

        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(SQL_GET_ITEM_ALL);
            items = query.addEntity(Item.class).list();

        }catch (HibernateException e){

            System.err.println("Operation failed");
            throw e;
        }
        return items;
    }

    void update(Item item){

        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();

            transaction.begin();

            session.update(item);

            transaction.commit();

        }catch (HibernateException e){

            if (transaction != null)
                transaction.rollback();

            System.err.println("Operation failed");
            throw e;
        }
    }

    void delete(Long id){

        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();

            transaction.begin();

            session.delete(findById(id));

            transaction.commit();

        }catch (HibernateException e){

            if (transaction != null)
                transaction.rollback();

            System.err.println("Operation failed");
            throw e;
        }
    }

    @SuppressWarnings("unchecked")
    Item findById(Long id){

        Item item;

        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(SQL_GET_ITEM_BY_ID);

            item = (Item) query.addEntity(Item.class).setParameter(1, id).getSingleResult();

        }catch (HibernateException e){

            System.err.println("Operation failed");
            throw e;
        }
        return item;
    }

    private static SessionFactory createSessionFactory(){
        if (sessionFactory == null){
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
