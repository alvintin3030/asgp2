package servlet;

import bean.RecycleToy;
import database.RecycleToyDB;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveRecycleController extends HttpServlet {

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

        RecycleToyDB toyDB = new RecycleToyDB();
        RecycleToy t = toyDB.getRToyById(tid);
        t.setIsApproved(1);

        String targetURL = null;
        if (!toyDB.editRecord(t)) {
            request.setAttribute("updateFail", true);
            targetURL = "manageRecycle";
        } else {
            request.setAttribute("updateFail", false);
            ArrayList<RecycleToy> approvedToys = toyDB.getApprovedRToys();
            ArrayList<RecycleToy> notApprovedToys = toyDB.getNotApprovedRToys();
            ArrayList<RecycleToy> rejectedToys = toyDB.getRejectedRToys();
            request.setAttribute("approvedToys", approvedToys);
            request.setAttribute("notApprovedToys", notApprovedToys);
            request.setAttribute("rejectedToys", rejectedToys);
            targetURL = "manageRecycle";
        }
        
        RequestDispatcher rd;
        rd = getServletContext().getRequestDispatcher("/" + targetURL);
        rd.forward(request, response);
    }
}
