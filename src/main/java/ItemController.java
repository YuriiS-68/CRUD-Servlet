import org.hibernate.HibernateException;

import java.util.List;

class ItemController {

    private ItemService itemService = new ItemService();

    Item save(Item item)throws BadRequestException{

        if (item.getId() == null){

            validationObject(item);

            try {

                itemService.save(item);

            }catch (HibernateException e){
                System.err.println(e.getMessage());
                throw e;
            }
            return item;
        }
        else
            throw new BadRequestException("Item with id " + item.getId() + " can`t be registered in the database");
    }

    void update(Item item)throws BadRequestException{

        validationObject(item);

        try {

            itemService.update(item);

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
    }

    void delete(Long id)throws BadRequestException{

        if (findById(id) != null){

            try {

                itemService.delete(id);

            }catch (HibernateException e){
                System.err.println(e.getMessage());
                throw e;
            }
        }
        else
            throw new BadRequestException("Item with id " + id + " in the database not found");
    }

    Item findById(Long id)throws BadRequestException{

        if (id != null){

            try {

                return itemService.findById(id);

            }catch (HibernateException e){
                System.err.println(e.getMessage());
                throw e;
            }
        }
        throw new BadRequestException("Item with id: " + id + " is not exist in DB.");
    }

    List<Item> getAllFiles(){

        try {

            return itemService.getAllFiles();

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw e;
        }
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
