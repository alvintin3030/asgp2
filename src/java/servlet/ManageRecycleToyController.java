package servlet;

import bean.RecycleToy;
import bean.User;
import database.RecycleToyDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ManageRecycleToyController extends HttpServlet {
 
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
        
        HttpSession session = request.getSession(true);
        
        boolean isAdmin = false;
        User user = null;
        if (session.getAttribute("userInfo") != null) {
            user = (User) session.getAttribute("userInfo");
            if (user.getGroupId() == 0)
                isAdmin = true;
        }
        
        String targetURL = null;
        
        if (isAdmin) {
            RecycleToyDB toyDB = new RecycleToyDB();
            ArrayList<RecycleToy> approvedToys = toyDB.getApprovedRToys();
            ArrayList<RecycleToy> notApprovedToys = toyDB.getNotApprovedRToys();
            request.setAttribute("approvedToys", approvedToys);
            request.setAttribute("notApprovedToys", notApprovedToys);
            targetURL = "manageRecycleToy.jsp";
        } else {
            targetURL = "index.jsp";
        }
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
