/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import bean.ShoppingCart;
import bean.Toy;
import bean.User;
import database.OrderRecordDB;
import database.ShoppingCartDB;
import database.ToyInventoryDB;
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
public class checkoutController extends HttpServlet {

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
        
           
            String action=request.getParameter("action");
             System.out.println("request: "+action);
            OrderRecordDB odb=new OrderRecordDB();
                ToyInventoryDB tdb=new ToyInventoryDB();
                ShoppingCartDB sdb=new ShoppingCartDB();
            
            HttpSession session=request.getSession();
            String username=((User)session.getAttribute("userInfo")).getUsername();
            
            if (action.equals("newPayment")){ 
                session.setAttribute("products",sdb.getShoppingCart(username));
                
            }
            
            if(action.equals("pay")){
                
                ArrayList<ShoppingCart> products=(ArrayList<ShoppingCart>)session.getAttribute("products");
                
                String payMethod=request.getParameter("payment_method");
                if (payMethod==null)
                    payMethod="no";
                
                String msg="<p><Successful Payment> Payment made by "+payMethod.replace("_"," ")+" is successful.<br></p><p>Order:</br>";
                
                float total=0;
                for (ShoppingCart p:products){
                    odb.addRecord(p);
                    msg=msg+tdb.getToyById(p.getToyId()).getName()+" X "+p.getQuantity()+"<br>";
                    total=total+(tdb.getToyById(p.getToyId()).getPrice()*p.getQuantity());
                    Toy t = tdb.getToyById(p.getToyId());
                    t.setQuantity(t.getQuantity()-p.getQuantity());
                    tdb.editRecord(t);
                }
                
                msg=msg+"</p><p>Total amount:"+total+"<br>";  
                msg=msg+"Thank you. Hope you enjoy the shopping experience.</p>";
                
                request.setAttribute("msg", msg);
               System.out.println(username);
               sdb.deleteAllCartItem(username);

            }
            
            RequestDispatcher rd;
            rd = getServletContext().getRequestDispatcher("/checkout.jsp");
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
