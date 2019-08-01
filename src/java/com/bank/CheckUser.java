/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bank;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
 * @author USER
 */
@WebServlet(name = "CheckUser", urlPatterns = {"/CheckUser"})
public class CheckUser extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String su = request.getParameter("txtuser");
        String sp = request.getParameter("txtpwd");
        PrintWriter out = response.getWriter();
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
            String query = "select * from Bankusers where username=? AND password=?";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, su);
            st.setString(2, sp);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {

                HttpSession session = request.getSession();
                session.setAttribute("username", su);
                String  bal = rs.getString(3);
                out.print(bal);
                if (bal==null) {
                    out.print("you don't have sufficient balance to make transactions");
                    response.sendRedirect("/BankWeb/Deposit.html");

                } else {
                    
                    response.sendRedirect("/BankWeb/Menu.html");
                }
            } 
            else {
                out.print("you are not authenticated");
            }


        } catch (Exception ee) {
            ee.printStackTrace();
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}