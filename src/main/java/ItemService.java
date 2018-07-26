
class ItemService {

    private ItemDAO itemDAO = new ItemDAO();

    Item save(Item item)throws BadRequestException{

        itemDAO.save(item);

        return item;
    }

    void update(Item item){

        itemDAO.update(item);
    }

    void delete(Long id)throws BadRequestException{

        itemDAO.delete(id);
    }

    Item findById(Long id){

        return itemDAO.findById(id);
    }
}
