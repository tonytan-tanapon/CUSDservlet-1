import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



// Import Database Connection Class file

// Servlet Name
@WebServlet("/Showdata")
public class Showdata extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	{
//		List<Student> students = new ArrayList<>();	
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try {

			// get a connection
			myConn = DatabaseConnection.initializeDatabase();
//			Connection con = DatabaseConnection.initializeDatabase();
			// create sql stmt
			String sql = "SELECT * FROM students";
			myStmt = myConn.createStatement();

			// execute query
			myRs = myStmt.executeQuery(sql);

			// process result
			while (myRs.next()) {

				// retrieve data from result set row
				int id = myRs.getInt("id");
				String firstName = myRs.getString("fname");
				String lastName = myRs.getString("lname");
				int age = myRs.getInt("age");
				int aclass = myRs.getInt("class");

				// create new student object
//				Student tempStudent = new Student(id, firstName, lastName, age, aclass);

				// add it to the list of students
				
//				students.add(tempStudent);
				PrintWriter out = response.getWriter();
				out.println("<html><body><b>Successfully Inserted"
							+ "</b>"+firstName
						
							+"</body></html>");
			}

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			// close JDBC objects
			close(myConn, myStmt, myRs);
		}
		


		
//		
//		try {
//
//			// Initialize the database
//			Connection con = DatabaseConnection.initializeDatabase();
//
//			// Create a SQL query to insert data into demo table
//			// demo table consists of two columns, so two '?' is used
//			PreparedStatement st = con.prepareStatement("select * from demo");
//
//			
//
//			// Execute the insert command using executeUpdate()
//			// to make changes in database
//			st.executeUpdate();
//
//			// Close all the connections
//			st.close();
//			con.close();
//
//			// Get a writer pointer
//			// to display the successful result
//			PrintWriter out = response.getWriter();
//			out.println("<html><body><b>Successfully Inserted"
//						+ "</b></body></html>");
//		}
//		catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {

		try {
			if (myRs != null) {
				myRs.close();
			}
			if (myStmt != null) {
				myStmt.close();
			}
			if (myConn != null) {
				myConn.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}

