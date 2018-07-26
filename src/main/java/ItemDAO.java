
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.NativeQuery;

class ItemDAO {

    private static SessionFactory sessionFactory;

    private static final String SQL_GET_ITEM_BY_ID = "SELECT * FROM ITEM WHERE ID = ?";

    Item save(Item item)throws BadRequestException{

        Transaction transaction = null;
        try(Session session = createSessionFactory().openSession()) {
            transaction = session.getTransaction();
            transaction.begin();

            if (item.getId() == null){

                session.save(item);

                transaction.commit();
            }
            else
                throw new BadRequestException("Item with id " + item.getId() + " can`t be registered in the database");

        }catch (HibernateException e){
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
        }
        return item;
    }

    void update(Item item){

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
        }
    }

    void delete(Long id)throws BadRequestException{

        Transaction transaction = null;
        try (Session session = createSessionFactory().openSession()){
            transaction = session.getTransaction();
            transaction.begin();

            if (findById(id) != null){
                session.delete(findById(id));

                transaction.commit();
            }
            else
                throw new BadRequestException("Item with id " + id + " in the database not found");
        }catch (HibernateException e){
            System.err.println(e.getMessage());

            if (transaction != null)
                transaction.rollback();
        }
    }

    @SuppressWarnings("unchecked")
    Item findById(Long id){

        Item item;

        try (Session session = createSessionFactory().openSession()){

            NativeQuery query = session.createNativeQuery(SQL_GET_ITEM_BY_ID);
            item = (Item) query.addEntity(Item.class).setParameter(1, id).getSingleResult();

        }catch (HibernateException e){
            System.err.println(e.getMessage());

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
