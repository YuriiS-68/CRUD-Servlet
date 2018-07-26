
class ItemController {

    private ItemService itemService = new ItemService();

    Item save(Item item)throws BadRequestException{

        return itemService.save(item);
    }

    void update(Item item)throws BadRequestException{

        validationObject(item);

        itemService.update(item);
    }

    void delete(Long id)throws BadRequestException{

        itemService.delete(id);
    }

    Item findById(Long id){

        return itemService.findById(id);
    }

    private void validationObject(Item item)throws BadRequestException{
        if (item == null)
            throw new NullPointerException("Item is not existing");

        if (item.getName() == null)
            throw new BadRequestException("The object Name is not filled");

        if (item.getDateCreated() == null)
            throw new BadRequestException("The object DateCreated is not filled");

        if (item.getLastUpdateDate() == null)
            throw new BadRequestException("The object LastUpdateDate is not filled");

        if (item.getDescription() == null)
            throw new BadRequestException("The object Description is not filled");
    }
}
