<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "java.util.*" %>
<!DOCTYPE html>
<%
    String[] salesPersonTypes = (String[]) request.getAttribute("salesPersonTypes");    
    String[] productTypes = (String[]) request.getAttribute("productTypes");   
    String[] customerTypes = (String[]) request.getAttribute("customerTypes");

    String chosenSalesPersonType = (String) request.getAttribute("chosenSalesPersonType");
    if (chosenSalesPersonType == null) {
        chosenSalesPersonType = "";
    }

    String chosenProductType = (String) request.getAttribute("chosenProductType");
    if (chosenProductType == null) {
        chosenProductType = "";
    }

    String productPrice = (String) request.getAttribute("productPrice");
    if (productPrice == null) {
        productPrice = "";
    }

    String chosenCustomerType = (String) request.getAttribute("chosenCustomerType");
    if (chosenCustomerType == null) {
        chosenCustomerType = "";
    }

    String errorMessage = (String) request.getAttribute("errorMessage");
    if (errorMessage == null) {
        errorMessage = "";
    }

    String result = (String) request.getAttribute("result");
    if (result == null) {
        result = "";
    }
%>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>Commission calculator</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <h1>Commission calculator</h1>

        <form action="calculate" method="POST">
            <p>Sales person type: <select name="salesPersonType" id="salesPersonType">
                <% for (String salesPersonType : salesPersonTypes) {
                    if (salesPersonType.equalsIgnoreCase(chosenSalesPersonType)) { %>
                        <option name="<%=salesPersonType %>" selected><%=salesPersonType %></option>
                <% } else { %>
                        <option name="<%=salesPersonType %>"><%=salesPersonType %></option>
                <% 
                    }
                } %>
            </select></p>
    
            <p>Product type: <select name="productType" id="productType">
                <% for (String productType : productTypes) {
                    if (productType.equalsIgnoreCase(chosenProductType)) { %>
                        <option name="<%=productType %>" selected><%=productType %></option>
                <% } else { %>
                        <option name="<%=productType %>"><%=productType %></option>
                <% 
                    }
                } %>
            </select></p>
    
            <p>
                Product price: <input type="number" name="productPrice" value="<%=productPrice %>" min="0">
    
            <p>Customer type: <select name="customerType" id="customerType">
                <% for (String customerType : customerTypes) {
                    if (customerType.equalsIgnoreCase(chosenCustomerType)) { %>
                        <option name="<%=customerType %>" selected><%=customerType %></option>
                <% } else { %>
                        <option name="<%=customerType %>"><%=customerType %></option>
                <% 
                    }
                } %>
            </select></p>

            <p><input type="submit" value="Calculate commission"> 

            <br>

            <p style="font-weight:bold;color:rgb(38, 91, 46)"><%=result %></p>
            <p style="font-style:italic;color:red"><%=errorMessage %></p>
        </form>
    </body>
</html>
