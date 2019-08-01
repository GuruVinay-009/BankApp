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
@WebServlet(name = "Transfer1", urlPatterns = {"/Transfer1"})
public class Transfer1 extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         
         HttpSession session=request.getSession();
       String su=(String)session.getAttribute("username");
       PrintWriter out=response.getWriter();
       
       String user2=request.getParameter("user2");
        int amt=Integer.parseInt(request.getParameter("amount"));
    
        try {
            Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","system");
            String query = "select username from Bankusers where username=? ";
            PreparedStatement st = con.prepareStatement(query);
            st.setString(1, user2);
            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("user existed");
                String query1="update BankUsers set balance=balance+? where username=?";
                PreparedStatement st1 = con.prepareStatement(query1);
                st1.setInt(1, amt);
                st1.setString(2, user2);
                int i=st1.executeUpdate();
                
                 String query2="update BankUsers set balance=balance-? where username=?";
                PreparedStatement st2 = con.prepareStatement(query2);
                st2.setInt(1, amt);
                st2.setString(2, su);
                int j=st2.executeUpdate();
                
                out.print("Transfer succesful");

            } else {
                out.print("user doesnot exist;");
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}