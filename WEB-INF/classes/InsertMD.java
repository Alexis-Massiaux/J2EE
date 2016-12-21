import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/InsertMD")
public class InsertMD extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con = null;

	String table = req.getParameter("table");

	try{
	    Class.forName("org.postgresql.Driver");
	    String url ="jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex","pass");
	    Statement stm = con.createStatement();
	    String query = "SELECT * FROM "+table;
	    ResultSet rs = null;
	    try{
		rs = stm.executeQuery(query);
	    }catch(Exception e){
		out.println(e.getMessage());
		try{
		    con.close();
		}catch(Exception exc){
		    out.println(exc.getMessage());
		}
	    }
	    ResultSetMetaData rsmd = rs.getMetaData();

	    out.println("<HEAD><TITLE>InsertMD-Formulaire</TITLE></HEAD>");
	    out.println("<BODY><CENTER><H1>Formulaire</H1>");
	    out.println("<FORM ACTION=\"/bonus/servlet/InsertMD2\" METHOD=GET>");
	    out.println("<BR><INPUT TYPE=\"hidden\" NAME=\"Table\" VALUE=\""+table+"\"/>");
	    for(int i=1; i<=rsmd.getColumnCount(); i++){
		out.println(rsmd.getColumnName(i)+": <BR><P><INPUT TYPE=\"text\" NAME=\""+rsmd.getColumnName(i)+"\"/></P><BR>");
	    }

	    out.println("<INPUT TYPE=\"submit\" VALUE=\"Valider\"/>");
	    out.println("</FORM></CENTER></BODY>");
	}catch(Exception e){
	    out.println(e.getMessage());
	}finally{
	    try{
		con.close();
	    }catch(Exception ex){
		out.println(ex.getMessage());
	    }
	}
    }
}
		
		
	
