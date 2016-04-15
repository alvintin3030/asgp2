package servlet;

import bean.User;
import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginController extends HttpServlet {

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

        if ("authenticate".equals(action)) {
            doAuthenticate(request, response);
        } else if ("register".equals(action)) {
            doRegister(request, response);
        } else if ("logout".equals(action)) {
            doLogout(request, response);
        } else if (!isAuthenticated(request)) {
            doLogin(request, response);
        } else {
            response.sendError(HttpServletResponse.SC_NOT_IMPLEMENTED);
        }
    }

    private void doAuthenticate(HttpServletRequest request, HttpServletResponse response) 
            throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        String targetURL = null;

        UserDB userdb = new UserDB();
        Boolean isValidUser = userdb.isValidUser(username, password);

        HttpSession session = request.getSession(true);

        if (isValidUser) {
            User user = userdb.getUserInfo(username);
            session.setAttribute("userInfo", user);
            targetURL = "/index";
        } else {
            request.setAttribute("loginError", true);
            targetURL = "/login.jsp";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private boolean isAuthenticated(HttpServletRequest request) {
        boolean result = false;
        HttpSession session = request.getSession();
        if (session.getAttribute("userInfo") != null) {
            result = true;
        }
        return result;
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String targetURL = "login.jsp";
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String address = request.getParameter("address");
       
        String targetURL = null;

        UserDB userdb = new UserDB();
        Boolean isDuplicateUser = userdb.isDuplicateUser(username);

        HttpSession session = request.getSession(true);

        if (!isDuplicateUser) {
            userdb.addRecord(username, password, email, phone, address);
            User user = userdb.getUserInfo(username);
            session.setAttribute("userInfo", user);
            targetURL = "/index.jsp";
        } else {
            request.setAttribute("registerError", true);
            targetURL = "/signup.jsp";
        }

        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        try {
            rd.forward(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void doLogout(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        if (session != null) {
            session.removeAttribute("userInfo");
            session.invalidate();
        }
        doLogin(request, response);
    }

}
