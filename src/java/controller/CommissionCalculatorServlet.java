/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 *
 * @author ASUS
 */
public class CommissionCalculatorServlet extends HttpServlet {

    String[] salesPersonTypes = {"Salaried", "Non-salaried"};
    String[] productTypes = {"Standard", "Bonus", "Neither standard nor bonus"};
    String[] customerTypes = {"Regular", "Not regular"};

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
            out.println("<title>Servlet CommissionCalculatorServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CommissionCalculatorServlet at " + request.getContextPath() + "</h1>");
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        request.setAttribute("salesPersonTypes", salesPersonTypes);
        request.setAttribute("productTypes", productTypes);
        request.setAttribute("customerTypes", customerTypes);

        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    public String CalculateCommission(Boolean salesPersonType, String productType, float productPrice, Boolean customerType) {
        if (customerType) {
            return "$0";
        }
        if (productType.equalsIgnoreCase("standard")) {
            return "$0";
        }
        if (salesPersonType && productType.equalsIgnoreCase("neither")) {
            return "$0";
        }

        if (salesPersonType) {
            if (productPrice > 1000) {
                return "$25";
            } else {
                return "5% commission of <= $1.000";
            }
        } else {
            if (productType.equalsIgnoreCase("bonus")) {
                if (productPrice > 1000) {
                    return "$75";
                } else {
                    return "10% commission of <= $1.000";
                }
            } else {
                if (productPrice > 10000) {
                    return "5% commission of <= $10.000";
                } else {
                    return "10% commission of <= $10.000";
                }
            }
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
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String salesPersonTypeIn = request.getParameter("salesPersonType").trim();
        String productTypeIn = request.getParameter("productType").trim();
        String productPriceIn = request.getParameter("productPrice");
        if (productPriceIn.isBlank()) {
            productPriceIn = productPriceIn.trim();
        }
        String customerTypeIn = request.getParameter("customerType").trim();

        request.setAttribute("salesPersonTypes", salesPersonTypes);
        request.setAttribute("productTypes", productTypes);
        request.setAttribute("customerTypes", customerTypes);
        request.setAttribute("chosenSalesPersonType", salesPersonTypeIn);
        request.setAttribute("chosenProductType", productTypeIn);
        request.setAttribute("chosenCustomerType", customerTypeIn);

        float productPrice = 0;
        if (productPriceIn.isEmpty()) {
            request.setAttribute("errorMessage", "The product's price cannot be empty!");

            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        } else {
            try {
                productPrice = Float.parseFloat(productPriceIn);
            } catch (NumberFormatException e) {
                request.setAttribute("errorMessage", "The product's price is invalid");

                request.getRequestDispatcher("index.jsp").forward(request, response);
                return;
            }
        }
        if (productPrice < 1 || productPrice > 20000) {
            request.setAttribute("errorMessage", "The product's price cannot be less than $1 or greater than $20.000!");

            request.getRequestDispatcher("index.jsp").forward(request, response);
            return;
        }
        request.setAttribute("productPrice", productPriceIn);

        Boolean salesPersonType = null;
        if (salesPersonTypeIn.equalsIgnoreCase("Salaried")) {
            salesPersonType = true;
        } else {
            salesPersonType = false;
        }

        if (productTypeIn.equalsIgnoreCase("Neither standard nor bonus")) {
            productTypeIn = "neither";
        }

        Boolean customerType = null;
        if (customerTypeIn.equalsIgnoreCase("Regular")) {
            customerType = true;
        } else {
            customerType = false;
        }

        String result = CalculateCommission(salesPersonType, productTypeIn, productPrice, customerType);

        request.setAttribute("result", result);

        request.getRequestDispatcher("index.jsp").forward(request, response);
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
