package servlet;

import bean.Toy;
import database.ToyInventoryDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EditToyController extends HttpServlet {

    /**
     * Processes requests for both HTTP
     * <code>GET</code> and
     * <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int tid = Integer.parseInt(request.getParameter("tid"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String image = request.getParameter("image");
        float price = Float.parseFloat(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        ToyInventoryDB toyDB = new ToyInventoryDB();
        Toy t = new Toy();
        t.setTid(tid);
        t.setName(name);
        t.setDescription(description);
        t.setCategory(category);
        t.setImage(image);
        t.setPrice(price);
        t.setQuantity(quantity);

        String targetURL = null;
        if (!toyDB.editRecord(t)) {
            request.setAttribute("updateFail", true);
            Toy toy = toyDB.getToyById(tid); 
            request.setAttribute("toy", toy);
            targetURL = "manageSingle?tid=" + tid;
        } else {
            targetURL = "manageToy";
        }
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
