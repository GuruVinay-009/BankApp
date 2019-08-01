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

/**
 *
 * @author USER
 */
@WebServlet(name = "Signup", urlPatterns = {"/Signup"})
public class Signup extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String su = request.getParameter("username");
        String sp = request.getParameter("password");
        String accno = request.getParameter("accno");
        String accname = request.getParameter("accname");
       



        PrintWriter out = response.getWriter();
        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
            String query = "insert into BankUsers(username,password,accno,accname) values(?,?,?,?)";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, su);
            st.setString(2, sp);
            st.setString(3, accno);
            st.setString(4, accname);
          
            int i = st.executeUpdate();
            if (i > 0) {

                out.print("new user created");

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
