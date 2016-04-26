/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;


import database.RecycleToyDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class viewRecycleToy extends HttpServlet {

    /**
     * 
     * This servlet will return 
     * 1) a view (page in jsp) that including details of a single product, or (type=tid page=details)
     * 2) item list of the 10 latest product,or  (type=latest page=index {if is requested from index page})
     * 3) item list of all item (type=all page=all {if is requested from all product page)
     * 
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
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet viewProduct</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet viewProduct at " + request.getContextPath() + "</h1>");
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
            ArrayList<String> category=rtDB.getAllCategory();
            
            boolean found=false;
            for (String c:category){
                if (type.equals(c)){
                    request.setAttribute("productItems",rtDB.getRToyByCategory(c.replaceAll("_"," ")));
                    found=true;
                }
            }
            
            if (found==false){
                if (type.equals("latest"))
                    request.setAttribute("productItems",rtDB.getLatestRToys());
                else if (type.equals("all"))
                    request.setAttribute("productItems",rtDB.getApprovedRToys());
                else 
                    request.setAttribute("productItems",rtDB.getRToyById(Integer.parseInt(type)));
            }

            //determine view page
            if(page.equals("detail"))
                targetURL="/single-recycleToy.jsp";
            else if(page.equals("index"))
                targetURL="/index.jsp";
            else if(page.equals("all")) {
                targetURL="/recycle.jsp";
                request.setAttribute("pageNo",request.getParameter("pageNo"));
            } else
                return;
         } else
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

       
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>



}
