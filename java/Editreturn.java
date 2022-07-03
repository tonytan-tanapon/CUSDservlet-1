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

@WebServlet("/Editreturn")
public class Editreturn extends HttpServlet {

	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	int row;

	public void doGet(HttpServletRequest req, HttpServletResponse rsp) throws IOException, ServletException {

		rsp.setContentType("text/html");
		PrintWriter out = rsp.getWriter();

		String eid = req.getParameter("id");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost/administrative-portal", "root", "");

			pst = con.prepareStatement("select * from teachers where id = ?");
			pst.setString(1, eid);
			rs = pst.executeQuery();

			while (rs.next()) {
				out.print("<form action='EditServlet' method='POST'");
				out.print("<table");

				out.print("<tr> <td>EmpID</td><td> <input type='text' name ='id' id='id' value= '"
						+ rs.getString("id") + "'/> </td> </tr>");
				out.print("<tr> <td>Firstname</td>    <td> <input type='text' name ='fname' id='fname' value= '"
						+ rs.getString("fname") + "'/> </td> </tr>");
				out.print("<tr> <td>Firstname</td>    <td> <input type='text' name ='lname' id='lname' value= '"
						+ rs.getString("lname") + "'/> </td> </tr>");
				out.print("<tr>  <td colspan ='2'> <input type='submit'  value= 'Edit'/> </td> </tr>");
				out.print("</table");
				out.print("</form");

			}

		} catch (ClassNotFoundException ex) {
			Logger.getLogger(employee.class.getName()).log(Level.SEVERE, null, ex);
		} catch (SQLException ex) {

			out.println("<font color='red'>  Record Failed   </font>");

		}
	}

}