package servlet;

import bean.Toy;

import database.ToyInventoryDB;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public final String RESULT_OK = "OK";
    public final String RESULT_FAIL = "FAIL";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ToyInventoryDB toyDB = new ToyInventoryDB();
        Toy t = new Toy();
        t.setTid(Integer.parseInt(request.getParameter("tid")));
        t.setName(request.getParameter("toyName"));
        t.setDescription(request.getParameter("toyDescription"));
        t.setCategory(request.getParameter("toyCategory"));
        t.setImage(request.getParameter("toyImage"));
        t.setPrice(Float.parseFloat(request.getParameter("toyPrice")));
        t.setQuantity(Integer.parseInt(request.getParameter("toyQuantity")));

        if (!toyDB.editRecord(t)) {
            request.setAttribute("result", RESULT_FAIL);
        }
        else {
            request.setAttribute("result", RESULT_OK);
        }
        String targetURL = "editToy.jsp";
        request.setAttribute("result", RESULT_OK);
        request.setAttribute("content", request.getParameter("tid"));
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
