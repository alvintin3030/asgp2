package servlet;

import bean.User;
import database.UserDB;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class EditUserInfoController extends HttpServlet {

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
        String targetURL = null;
        
        if ("update".equals(action)) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String newPassword = request.getParameter("newPassword");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String address = request.getParameter("address");

            UserDB userDB = new UserDB();
            Boolean isValidUser = userDB.isValidUser(username, password);

            HttpSession session = request.getSession(true);

            if (isValidUser) {
                boolean result;
                if (newPassword != null) 
                   result = userDB.editRecord(username, newPassword, email, phone, address);
                else 
                   result = userDB.editRecord(username, password, email, phone, address);
               
                if (result == true) {
                    User user = userDB.getUserInfo(username);
                    session.setAttribute("userInfo", user);
                    request.setAttribute("editSuccess", true);
                } else {
                    request.setAttribute("editFail", true);
                }
            } else {
                request.setAttribute("editFail", true);
            }
        }
        
        targetURL = "myAccount.jsp";
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
