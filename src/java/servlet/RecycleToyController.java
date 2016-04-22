package servlet;

import bean.User;
import database.UserDB;
import bean.RecycleToy;
import database.RecycleToyDB;
import java.io.IOException;
import static java.lang.Float.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RecycleToyController extends HttpServlet {

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if ("donate".equals(action)) {
            doDonate(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void doDonate(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String price = request.getParameter("price");
        String targetURL = null;
        

        HttpSession session = request.getSession(true);
        User userInfo = (User)session.getAttribute("userInfo");
                
        RecycleToyDB rtDB = new RecycleToyDB();
        RecycleToy rt = new RecycleToy();
        rt.setName(name);
        rt.setDescription(description);
        rt.setCategory(category);
        rt.setImage("null");
        rt.setPrice(parseFloat(price));
        rt.setDonatedBy(userInfo.getUsername());
        rt.setIsApproved(0);
        rtDB.addRecord(rt);
        
        targetURL = "/recycle.jsp";
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(RecycleToy.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
