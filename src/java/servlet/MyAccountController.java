package servlet;

import bean.OrderRecord;
import bean.RecycleToy;
import bean.User;
import database.OrderRecordDB;
import database.RecycleToyDB;
import database.UserDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MyAccountController extends HttpServlet {
 
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
        
        User user = null;
        String targetURL = null;
        if (session.getAttribute("userInfo") != null) {
            user = (User) session.getAttribute("userInfo");
            RecycleToyDB toyDB = new RecycleToyDB();
            ArrayList<RecycleToy> myRecycleToys = toyDB.getUserRToys(user.getUsername());
            request.setAttribute("myRecycleToys", myRecycleToys);
            OrderRecordDB orderDB = new OrderRecordDB();
            ArrayList<OrderRecord> myOrder = orderDB.getUserOrderHistory(user.getUsername());
            request.setAttribute("myOrder", myOrder);
            targetURL = "myAccount.jsp";
        } else {
            targetURL = "login.jsp";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
