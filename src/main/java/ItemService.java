import org.hibernate.HibernateException;

import java.util.List;

class ItemService {

    private ItemDAO itemDAO = new ItemDAO();

    Item save(Item item){

        try {

            itemDAO.save(item);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
        return item;
    }

    void update(Item item){

        try {

            itemDAO.update(item);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
    }

    void delete(Long id){

        try {

            itemDAO.delete(id);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
    }

    Item findById(Long id){

        try {

            return itemDAO.findById(id);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
    }

    List<Item> getAllFiles(){

        try {

            return itemDAO.getAllFiles();

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
    }
}
