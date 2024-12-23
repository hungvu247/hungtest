/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dal.LodgingHousesDAO;
import dal.StaffDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Account;
import model.Staff;
import utils.Pagination;

/**
 *
 * @author Admin
 */
public class ListStaffServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ListStaffServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ListStaffServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        PrintWriter out = response.getWriter();
        StaffDAO staffDAO = new StaffDAO();
        HttpSession session = request.getSession();
        String logdingHouseID_raw = (String) session.getAttribute("lodgingID");
        //Account account = (Account) session.getAttribute("account");
        int numberPerPage = 6;
        int lodgingHouseID = 0 ;
        if(logdingHouseID_raw != null) {
            //lodgingHouseID = lodgeDAO.getLodgingHouseByManageID(account.getAccountID());
            lodgingHouseID = Integer.parseInt(logdingHouseID_raw);
        }
        String currentPageRaw = request.getParameter("curentPage");
        Pagination<Staff> pagination = new Pagination<>(currentPageRaw, numberPerPage, staffDAO.getAllStaffByLodgingHouseID(lodgingHouseID));
        session.removeAttribute("pagination");
        session.setAttribute("pagination", pagination);
//        out.print(pagination.getListObject().size() + " " + lodgingHouseID + "" + account.getAccountID());
        request.getRequestDispatcher("view/manager/list-staff.jsp").forward(request, response);
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
        request.getRequestDispatcher("view/manager/list-staff.jsp").forward(request, response);
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