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

public class HomeController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String targetURL = "/index.jsp";

        ToyInventoryDB toyDB = new ToyInventoryDB();
        ArrayList<Toy> al = toyDB.getLatestToys();
        request.setAttribute("latestToy", al);
        RequestDispatcher rd = this.getServletContext().getRequestDispatcher(targetURL);
        rd.forward(request, response);
    }

}
