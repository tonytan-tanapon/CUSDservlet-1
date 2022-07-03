import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet  {
    
    
    Connection con;
    PreparedStatement pst;
    ResultSet rs;
    int row;

    public void doPost(HttpServletRequest req,HttpServletResponse rsp ) throws IOException,ServletException
    {
        
        rsp.setContentType("text/html");
        PrintWriter out = rsp.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/administrative-portal","root","");
           
             String empid = req.getParameter("id");
             String fname = req.getParameter("fname");
             String lname = req.getParameter("lname");
             
             pst = con.prepareStatement("update teachers set fname = ?, lname = ? where id = ?");
             pst.setString(1, fname);
             pst.setString(2, lname);
             pst.setString(3, empid);
             row = pst.executeUpdate();
             
              out.println("<font color='green'>  Record Updateeeedd   </font>");
              rsp.sendRedirect(req.getContextPath() + "/index.html");
   
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
           
             out.println("<font color='red'>  Record Failed   </font>");
 
        }

    }
  
}