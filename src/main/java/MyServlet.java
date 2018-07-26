
import com.fasterxml.jackson.databind.ObjectMapper;

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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp){

        try {
            resp.getWriter().println(controller.findById(Long.parseLong(req.getParameter("param"))).toString());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            controller.save(mappingObject(req));

            resp.getWriter().println("Object saved successfully");

        } catch (BadRequestException e) {

            resp.getWriter().println(e);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)throws IOException {

        try {
            controller.update(mappingObject(req));

            resp.getWriter().println("Object updated successfully");

        } catch (BadRequestException e) {

            resp.getWriter().println(e);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)throws IOException{

        try {
            controller.delete(Long.parseLong(req.getParameter("itemId")));

            resp.getWriter().println("Recording deleted successfully");

        } catch (BadRequestException e) {

            resp.getWriter().println(e);
        }
    }

    private Item mappingObject(HttpServletRequest req)throws IOException {

        StringBuilder stringBuilder = new StringBuilder();
        String line = null;

        BufferedReader reader = req.getReader();
        while ((line = reader.readLine()) != null){
            stringBuilder.append(line);
        }

        ObjectMapper objectMapper = new ObjectMapper();

        String input = objectMapper.writeValueAsString(stringBuilder.toString());

        return objectMapper.convertValue(input, Item.class);
    }
}