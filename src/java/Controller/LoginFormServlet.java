/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import dataaccesslayer.UserDAOImpl;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author User
 */
public class LoginFormServlet extends HttpServlet {
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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SampleFormServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SampleFormServlet at " + request.getContextPath() + "</h1>");
            out.println("<UL>\n");
            out.println("  <LI><B>email</B>: "
                + request.getParameter("email") + "\n");
            out.println("  <LI><B>password</B>: "
                + request.getParameter("password") + "\n");
            out.println("</UL>\n");
            out.println("</body>");
            out.println("</html>");
        }
        
//            response.sendRedirect("https://www.google.com");
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
                System.out.print("Receibing GET request");
        UserDAOImpl userDAO = new UserDAOImpl();
        User userFound = userDAO.getUserByEmail(request.getParameter("email"));
        if(userFound!= null && userFound.getPassword().equals(request.getParameter("password"))){
            System.out.println("valid");
            response.sendRedirect("https://http://localhost:8080/FWRP/inventory/management");
        }else{
            System.out.println("invalid");
        }
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
        System.out.print("Receibing POST request");
        UserDAOImpl userDAO = new UserDAOImpl();
        User userFound = userDAO.getUserByEmail(request.getParameter("email"));
        if(userFound!= null && userFound.getPassword().equals(request.getParameter("password"))){
            System.out.println("valid");
            response.sendRedirect("https://http://localhost:8080/FWRP/inventory/management");
        }else{
            System.out.println("invalid");
        }
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