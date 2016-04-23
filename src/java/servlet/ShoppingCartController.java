/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import bean.ShoppingCart;
import bean.ShoppingCartItem;
import bean.User;
import database.ShoppingCartDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author tkwong94
 */
public class ShoppingCartController extends HttpServlet {

    /**
     * This servlet handle request that is related to shopping cart, 
     * including 1) add to shopping cart , 2)remove from shopping cart and
     * 3)view items in shopping cart.
     * 
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
        
        String action = request.getParameter("action");
       // boolean cb=request.getParameter("cb").equals("true")?true:false;
        int toyId=Integer.parseInt(request.getParameter("id"));
        int quantity=Integer.parseInt(request.getParameter("quantity"));
        
        HttpSession session = request.getSession(true);
        User userInfo = (User) session.getAttribute("userInfo");
        ShoppingCartDB sdb=new ShoppingCartDB();
        
        //String targetURL=request.getRequestURI();;
        System.out.println(request.getRequestURI());
        if (userInfo!=null){
            String username=userInfo.getUsername();
            if (action.equals("add")){
                sdb.addRecord(username, toyId, quantity);
                out.println("Sucessfully add");
            }
            if (action.equals("view")){
                ArrayList<ShoppingCart> items=sdb.getShoppingCart(username);
                request.setAttribute("products",items);
                RequestDispatcher rd = this.getServletContext().getRequestDispatcher("/cart.jsp");
                rd.forward(request, response);
            }
                        
            
        }else{
            out.println("Please login.");
        }
        
       // RequestDispatcher rd = this.getServletContext().getRequestDispatcher(targetURL);
        //rd.forward(request, response);
        
        
        
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
