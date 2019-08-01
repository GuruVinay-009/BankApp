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
@WebServlet(name = "GetBal", urlPatterns = {"/GetBal"})
public class GetBal extends HttpServlet {

   
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       HttpSession session=request.getSession();
       String su=(String)session.getAttribute("username");
       PrintWriter out=response.getWriter();
       try{
           Class.forName("oracle.jdbc.driver.OracleDriver");
           Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "system", "system");
           String query = "select * from Bankusers where username=?";
           PreparedStatement st = con.prepareStatement(query);
           st.setString(1, su);
          
           ResultSet rs = st.executeQuery();
           if (rs.next()) {
               out.print("your balance is:"+rs.getString("balance"));
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
    
