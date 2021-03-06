import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Select")
public class Select extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	out.println("<head><title>Select</title></head>");
	out.println("<body><center><table border =\"1\">");

	Connection con = null;

	try{
	    //Connection afin de recuperer la table
	    Class.forName("org.postgresql.Driver");
	    String url="jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex", "pass");
	    Statement stm = con.createStatement();
	    String query = "SELECT * FROM Clients;";
	    ResultSet rs = stm.executeQuery(query);

	    while(rs.next()){
		out.println("<tr><td>"+rs.getString("nom")+"</td><td>"+rs.getString("prenom")+"</td><td>"+rs.getInt("age")+"</td></tr>");
	    }

	    out.println("</table></center></body>");

	}catch(Exception e){
	    out.println(e.getMessage());
	}
	finally{
	    try{
		con.close();
	    }
	    catch(Exception e){
		out.println(e.getMessage());
	    }
	}
    }
}
