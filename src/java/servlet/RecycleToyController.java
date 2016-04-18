/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import database.RecycleToyDB;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RecycleToyController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet RecycleToyController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet RecycleToyController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        String type=request.getParameter("type");
         String page=request.getParameter("page");
         String targetURL;
         
         
         if (!(type.equals("")||page.equals(""))){
             
            RecycleToyDB rtDB=new RecycleToyDB();
            
            if (type.equals("latest"))
                request.setAttribute("productItems",rtDB.getLatestRToys());
            else if (type.equals("all"))
                request.setAttribute("productItems",rtDB.getRToys());
            else 
                request.setAttribute("productItems",rtDB.getRToyById(Integer.parseInt(type)));

            //determine view page
            if(page.equals("detail"))
                targetURL="/single-product.jsp";
            else if(page.equals("index"))
                targetURL="/index.jsp";
            else if(page.equals("all")){
                targetURL="/recycle.jsp";
                request.setAttribute("pageNo",request.getParameter("pageNo"));
            }else
                return;
         }else
             return;
         
         RequestDispatcher rd = this.getServletContext().getRequestDispatcher(targetURL);
         rd.forward(request, response);
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
        doGet(request,response);
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
