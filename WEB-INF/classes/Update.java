import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Update")
public class Update extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con  = null;

	String table = req.getParameter("table");
	String modif = req.getParameter("modif");
	String colone_modif = req.getParameter("colone_modif");
	String colone_primary = req.getParameter("colone_primary");
	String primary = req.getParameter("primary");

	try{
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex","pass");
	    Statement stmt = con.createStatement();
	    String query = "UPDATE "+table+" SET "+colone_modif+" = '"+modif+"' WHERE "+colone_primary+" = '"+primary+"' ;";
	    ResultSet rs = stmt.executeQuery(query);
	    
	}catch(Exception e){
	    out.println(e.getMessage());
	}finally{
	    try{
		con.close();
	    }catch(Exception e){
		out.println(e.getMessage());
	    }
	    res.sendRedirect("/bonus/servlet/UpdateFrm?table="+table);
	}
    }
}
