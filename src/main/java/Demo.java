import java.util.Date;

public class Demo {
    public static void main(String[] args) {

        ItemDAO itemDAO = new ItemDAO();

        Item item1 = new Item("Item1", new Date(), new Date(), "description1");
        Item item2 = new Item("Changed", new Date(), new Date(), "changed");
        Item item3 = new Item("Item3", new Date(), new Date(), "description3");
        Item item4 = new Item("Item4", new Date(), new Date(), "description4");
        Item item5 = new Item("Item5", new Date(), new Date(), "description5");

        /*itemDAO.save(item2);
        itemDAO.save(item3);
        itemDAO.save(item4);
        itemDAO.save(item5);*/

        //System.out.println(itemDAO.findById(4));

        //item2.setId(2L);
        //itemDAO.update(item2);
        //itemDAO.delete(2L);

        //System.out.println(itemDAO.findById(2));

    }
}
