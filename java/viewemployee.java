import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/viewemployee")
public class viewemployee extends HttpServlet {
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    public void doGet(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();
        
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/administrative-portal","root","");
           
            String sql;
            
            sql = "select * from teachers";
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(sql);
            
            out.println("<table cellspacing='0' width='350px' border='1'>");
            out.println("<tr>");
            out.println("<td> EmpID </td>");
            out.println("<td> Firstname </td>");
            out.println("<td> Lastname </td>");
            out.println("<td> Edit </td>");
            out.println("<td> Delete </td>");
            
            out.println("</tr>");
            
            while(rs.next())
            {
             out.println("<tr>");
             out.println("<td>"  + rs.getString("id")   +  "</td>");
             out.println("<td>"  + rs.getString("fname")   +  "</td>");  
             out.println("<td>"  + rs.getString("lname")   +  "</td>");  
             
             out.println("<td>"  + "<a href='Editreturn?id=" +  rs.getString("id")  + "'> Edit </a>" + "</td>");
             out.println("<td>"  + "<a href='Delete?id=" +  rs.getString("id")  + "'> Delete </a>" + "</td>");
             out.println("</tr>");

            }
            
            out.println("</table>");
 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");  
        }
    }  
}