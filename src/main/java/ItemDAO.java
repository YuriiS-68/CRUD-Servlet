
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

class ItemDAO {

    private static SessionFactory sessionFactory;

    private static final String SQL_GET_ITEM_BY_ID = "SELECT * FROM ITEM WHERE ID = ?";

    Item save(Item item)throws BadRequestException {

        Transaction transaction = null;
        try(Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();

            transaction.begin();

            session.save(item);

            transaction.commit();

        }catch (HibernateException e){
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();

            throw new BadRequestException("Save is failed");
        }
        return item;
    }

    void update(Item item)throws BadRequestException{

        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();

            transaction.begin();

            session.update(item);

            transaction.commit();

        }catch (HibernateException e){
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();

            throw new BadRequestException("Updating is failed");
        }
    }

    void delete(Long id)throws BadRequestException{

        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();

            transaction.begin();

            session.delete(findById(id));

            transaction.commit();

        }catch (HibernateException e){
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();

            throw new BadRequestException("Delete is failed");
        }
    }

    @SuppressWarnings("unchecked")
    Item findById(Long id)throws BadRequestException{

        if (id != null){
            try (Session session = createSessionFactory().openSession()){

                NativeQuery query = session.createNativeQuery(SQL_GET_ITEM_BY_ID);

                return  (Item) query.addEntity(Item.class).setParameter(1, id).getSingleResult();

            }catch (HibernateException e){
                System.err.println(e.getMessage());
            }
        }
        throw new BadRequestException("Item with id: " + id + " is not exist in DB.");
    }

    private static SessionFactory createSessionFactory(){
        if (sessionFactory == null){
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        return sessionFactory;
    }
}
