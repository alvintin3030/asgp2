/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package servlet;

import bean.RecycleCart;
import bean.RecycleToy;
import bean.User;
import database.RecycleOrderRecordDB;
import database.RecycleCartDB;
import database.RecycleToyDB;
import database.UserDB;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class RecycleCheckoutController extends HttpServlet {

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
            RecycleOrderRecordDB odb=new RecycleOrderRecordDB();
            RecycleToyDB rtdb=new RecycleToyDB();
            RecycleCartDB rcdb=new RecycleCartDB();
            
            HttpSession session=request.getSession();
            String username=((User)session.getAttribute("userInfo")).getUsername();
            
            
                if (action.equals("newPayment")){ 
                    session.setAttribute("products",rcdb.getRecycleCart(username));

                }

                if(action.equals("pay")){

                    ArrayList<RecycleCart> products=(ArrayList<RecycleCart>)session.getAttribute("products");

                    String payMethod=request.getParameter("payment_method");
                    if (payMethod==null)
                        payMethod="no";

                    String msg="<p><Successful Payment> Payment made by "+payMethod.replace("_"," ")+" is successful.<br></p><p>Order:</br>";

                    float total=0;
                    for (RecycleCart p:products){
                        odb.addRecord(p);
                        msg=msg+rtdb.getRToyById(p.getToyId()).getName()+" X "+"<br>";
                        total=total+(rtdb.getRToyById(p.getToyId()).getPrice());
                        UserDB userdb = new UserDB();
                        RecycleToy rt = rtdb.getRToyById(p.getToyId());
                        User donater = userdb.getUserInfo(rt.getDonatedBy());
                        float newCredit = donater.getCredit() + rt.getPrice();
                        userdb.updateCredit(donater.getUsername(), newCredit);
                        rtdb.deleteRecord(rt.getTid());
                        rcdb.deleteCartItem(username, p.getToyId());
                    }

                    msg=msg+"</p><p>Total amount:"+total+"<br>";  
                    msg=msg+"Thank you. Hope you enjoy the shopping experience.</p>";

                    request.setAttribute("msg", msg);
                   System.out.println(username);
                   rcdb.deleteAllCartItem(username);
                   
                }
                RequestDispatcher rd;
                rd = getServletContext().getRequestDispatcher("/recycleCheckout.jsp");
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
