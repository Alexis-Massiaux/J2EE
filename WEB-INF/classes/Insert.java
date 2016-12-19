import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Insert")
public class Insert extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	String nom = req.getParameter("nom");
	String prenom = req.getParameter("prenom");
	int age = Integer.parseInt(req.getParameter("age"));

	Connection con = null;

	try{
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url, "alex", "pass");
	    Statement stm = con.createStatement();
	    String query = "INSERT INTO Clients VALUES('"+nom+"','"+prenom+"','"+age+"');";
	    ResultSet rs = stm.executeQuery(query);

	}catch(Exception e){
	    out.println(e.getMessage());
	}finally{
	    try{
		con.close();
		res.sendRedirect("Select");
	    }catch(Exception e){
		out.println(e.getMessage());
	    }
	}
    }
}
