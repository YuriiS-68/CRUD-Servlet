import java.util.List;

class ItemService {

    private ItemDAO itemDAO = new ItemDAO();

    Item save(Item item){

        return itemDAO.save(item);
    }

    void update(Item item){

        itemDAO.update(item);
    }

    void delete(Long id){

        itemDAO.delete(id);
    }

    Item findById(Long id){

        return itemDAO.findById(id);
    }

    List<Item> getAllFiles(){

        return itemDAO.getAllFiles();
    }
}
