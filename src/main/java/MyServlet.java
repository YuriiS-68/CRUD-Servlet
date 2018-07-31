
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.HibernateException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

@WebServlet(urlPatterns = "/test")
public class MyServlet extends HttpServlet {

    private ItemController controller = new ItemController();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws IOException{
        String action = req.getParameter("param");

        try {
            if (action.equalsIgnoreCase("list")){
                resp.getWriter().print(controller.getAllFiles());
            }
            else
                resp.getWriter().println(controller.findById(Long.parseLong(req.getParameter("param"))).toString());

        } catch (BadRequestException e) {

            e.printStackTrace();

        }catch (HibernateException e){

            resp.getWriter().println("Operation failed");
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            controller.save(mappingObject(req));

            resp.getWriter().println("Object saved successfully");

        } catch (BadRequestException e) {

            resp.getWriter().println(e);

        }catch (HibernateException e){

            resp.getWriter().println("Operation failed");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)throws IOException {

        try {
            controller.update(mappingObject(req));

            resp.getWriter().println("Object updated successfully");

        } catch (BadRequestException e) {

            resp.getWriter().println(e);

        } catch (HibernateException e){

            resp.getWriter().println("Operation failed");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)throws IOException{

        try {
            controller.delete(Long.parseLong(req.getParameter("itemId")));

            resp.getWriter().println("Recording deleted successfully");

        } catch (BadRequestException e) {

            resp.getWriter().println(e);

        }catch (HibernateException e){

            resp.getWriter().println("Operation failed");
        }
    }

    private Item mappingObject(HttpServletRequest req)throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line;

        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String input = objectMapper.writeValueAsString(stringBuilder.toString());

        return objectMapper.convertValue(input, Item.class);
    }
}
