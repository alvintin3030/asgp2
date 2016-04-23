/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.Comment;
import database.CommentDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Calendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author tkwong94
 */
public class CommentHandler extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
           String action=request.getParameter("action");//add/reply
           String username=request.getParameter("username"); //username/Anonymous
           String comment=request.getParameter("commentArea");
           int tid=Integer.parseInt(request.getParameter("tid"));
           int id;
          
           if (action.equals("reply")){
                CommentDB cdb=new CommentDB();
                Comment c = new Comment();
                id= (Integer.parseInt(request.getParameter("id")));
                c.setToyID(tid);
                c.setSubComment(id);
                c.setUsername(username);
                c.setContent(comment);
                c.setIsSubComment(1);
                cdb.addComment(c);
           }
           if (action.equals("add")){
               System.out.println(tid+username+comment);
               CommentDB cdb=new CommentDB();
                Comment c = new Comment();
                c.setToyID(tid);
                c.setSubComment(0);
                c.setUsername(username);
                c.setContent(comment);
                c.setIsSubComment(0);
                cdb.addComment(c);
           }
           String targetURL="/viewProduct?type="+tid+"&page=detail";
           RequestDispatcher rd = this.getServletContext().getRequestDispatcher(targetURL);
           rd.forward(request, response);
           
            
        } finally {
            out.close();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
