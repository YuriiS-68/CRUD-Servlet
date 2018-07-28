
class ItemController {

    private ItemService itemService = new ItemService();

    Item save(Item item)throws BadRequestException{

        if (item.getId() == null){

            validationObject(item);

            return itemService.save(item);
        }
        else
            throw new BadRequestException("Item with id " + item.getId() + " can`t be registered in the database");
    }

    void update(Item item)throws BadRequestException{

        validationObject(item);

        itemService.update(item);
    }

    void delete(Long id)throws BadRequestException{

        if (findById(id) != null){

            itemService.delete(id);
        }
        else
            throw new BadRequestException("Item with id " + id + " in the database not found");
    }

    Item findById(Long id)throws BadRequestException{

        if (id != null){

            return itemService.findById(id);
        }
        throw new BadRequestException("Item with id: " + id + " is not exist in DB.");
    }

    private void validationObject(Item item)throws BadRequestException{
        if (item == null)
            throw new NullPointerException("Item is not existing");

        if (item.getName() == null)
            throw new BadRequestException("Object field Name is missing.");

        if (item.getDateCreated() == null)
            throw new BadRequestException("Object field DateCreated is missing.");

        if (item.getLastUpdateDate() == null)
            throw new BadRequestException("Object field LastUpdateDate is missing.");

        if (item.getDescription() == null)
            throw new BadRequestException("Object field Description is missing.");
    }
}
