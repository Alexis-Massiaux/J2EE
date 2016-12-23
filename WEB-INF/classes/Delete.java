import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;


@WebServlet("/servlet/Delete")
public class Delete extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con = null;

	String delete = req.getParameter("table");
	String key = req.getParameter("cle");

	if(delete != null && key != null){
	    try{
		Class.forName("org.postgresql.Driver");
		String url ="jdbc:postgresql:dtbase";
		con = DriverManager.getConnection(url,"alex","pass");
		Statement stmt = con.createStatement();
		String query = "DELETE FROM "+delete+" WHERE cle = "+key+" ;";
		ResultSet rs = stmt.executeQuery(query);
	    }catch(Exception e){
		out.println(e.getMessage());
	    }finally{
		try{
		    con.close();
		}catch(Exception e){
		    out.println(e.getMessage());
		}
		res.sendRedirect("/bonus/servlet/SelectMD?table="+delete);
	    }
	}else{
	    out.println("Veuillez fournir une table ET une clé");
	}
    }
}
	
