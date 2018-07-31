import org.hibernate.HibernateException;

import java.util.List;

class ItemService {

    private ItemDAO itemDAO = new ItemDAO();

    Item save(Item item){

        try {

            itemDAO.save(item);

        }catch (HibernateException e){
            System.err.println("Operation failed");
            throw e;
        }
        return item;
    }

    void update(Item item){

        try {

            itemDAO.update(item);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    void delete(Long id){

        try {

            itemDAO.delete(id);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    Item findById(Long id){

        try {

            return itemDAO.findById(id);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    List<Item> getAllFiles(){

        try {

            return itemDAO.getAllFiles();

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }
}
