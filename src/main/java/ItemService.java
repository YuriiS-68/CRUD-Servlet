
class ItemService {

    private ItemDAO itemDAO = new ItemDAO();

    Item save(Item item)throws BadRequestException{

        return itemDAO.save(item);
    }

    void update(Item item)throws BadRequestException{

        itemDAO.update(item);
    }

    void delete(Long id)throws BadRequestException{

        itemDAO.delete(id);
    }

    Item findById(Long id)throws BadRequestException{

        return itemDAO.findById(id);
    }
}
