import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/JdbcConnection")
public class JdbcConnection extends HttpServlet {

	Connection connection = null;
	ResultSet resultSet = null;
	PreparedStatement preparedStatement = null;
	String query = null;
	String url = null;
	String username = null;
	String password = null;

	public void init(ServletConfig config) throws ServletException {

		url = "jdbc:mysql://localhost:3306/demoprj";
		username = "root";
		password = "";
		try {
			Class.forName("com.mysql.jdbc.Driver");

			connection = DriverManager.getConnection(url, username, password);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		query = "select * from demo";
		try {
			preparedStatement = connection.prepareStatement(query);
			resultSet = preparedStatement.executeQuery();

			out.println("<html><body><h2>The Select query has following results : </h2>");
			out.println("<hr></br><table cellspacing='0' cellpadding='5' border='1'>");
			out.println("<tr>");
			out.println("<td><b>First Name</b></td>");
			out.println("<td><b>Last Name</b></td>");
//   out.println("<td><b>Email</b></td>");
			out.println("</tr>");

			while (resultSet.next()) {
				out.println("<tr>");
				out.println("<td>" + resultSet.getString(1) + "</td>");
				out.println("<td>" + resultSet.getString(2) + "</td>");
//    out.println("<td>"+resultSet.getString(3) + "</td>");
				out.println("</tr>");

			}

			out.println("</table></br><hr></body></html>");
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	@Override
	public void destroy() {
		try {
			resultSet.close();
			preparedStatement.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

}
