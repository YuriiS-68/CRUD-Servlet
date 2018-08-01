import org.hibernate.HibernateException;

import java.util.List;

class ItemController {

    private ItemService itemService = new ItemService();

    Item save(Item item)throws BadRequestException {

        if (item.getId() == null){

            validationObject(item);

            try {

                itemService.save(item);

            }catch (HibernateException e){
                System.err.println(e.getMessage());
                throw new HibernateException("Operation failed");
            }
            return item;
        }
        else
            throw new BadRequestException("Item with id " + item.getId() + " can`t be registered in the database");
    }

    void update(Item item)throws BadRequestException{

        validationObject(item);

        try {

            if (findById(item.getId()) != null){

                itemService.update(item);

            }else
                throw new BadRequestException("Updating is not possible. Item with id - " + item.getId() +
                " is missing in the database.");

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
    }

    void delete(Long id)throws BadRequestException{

        if (findById(id) != null){

            try {

                itemService.delete(id);

            }catch (HibernateException e){
                System.err.println(e.getMessage());
                throw new HibernateException("Operation failed");
            }
        }
        else
            throw new BadRequestException("The ID - " + id + " does not exist");
    }

    Item findById(Long id){

        if (id != null){

            try {

                return itemService.findById(id);

            }catch (HibernateException e){
                System.err.println(e.getMessage());
                throw new HibernateException("Operation failed");
            }
        }
        return null;
    }

    List<Item> getAllFiles(){

        try {

            return itemService.getAllFiles();

        }catch (HibernateException e){
            System.err.println(e.getMessage());
            throw new HibernateException("Operation failed");
        }
    }
    private void validationObject(Item item)throws BadRequestException{
        if (item == null)
            throw new BadRequestException("Item is not existing");

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
