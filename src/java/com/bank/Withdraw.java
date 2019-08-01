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
@WebServlet(name = "Withdraw", urlPatterns = {"/Withdraw"})
public class Withdraw extends HttpServlet {
      
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         HttpSession session=request.getSession();
       String su=(String)session.getAttribute("username");
       PrintWriter out=response.getWriter();
       
       int amt=Integer.parseInt(request.getParameter("amount"));
        System.out.println(amt);
       try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
           String query = "update Bankusers set balance=balance-? where username=?";
           PreparedStatement st = con.prepareStatement(query);
           st.setInt(1,amt);
           st.setString(2, su);
          
           int i= st.executeUpdate();
           if (i==1) {
               out.print("Withdraw successful");
           }
           }
       catch(Exception e){
           e.printStackTrace();
       }
   

    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

}  
