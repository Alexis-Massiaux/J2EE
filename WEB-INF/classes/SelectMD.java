import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/SelectMD")
public class SelectMD extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con = null;

	String table = req.getParameter("table");

	try{
	    Class.forName("org.postgresql.Driver");
	    String url="jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url, "alex", "pass");
	    Statement stmt = con.createStatement();
	    String query = "SELECT * FROM "+table;
	    ResultSet rs=null;
	    try{
		rs=stmt.executeQuery(query);
	    }catch(Exception e){
		out.println(e.getMessage());
		try{
		    con.close();
		}catch(Exception exce){
		    out.println(exce.getMessage());
		}
	    }
	    ResultSetMetaData rsmd = rs.getMetaData();

	    out.println("<HEAD><TITLE>SelectMD</TITLE></HEAD>");
	    out.println("<BODY><CENTER><H1>Liste de la table \""+table+"\"</H1>");
	    out.println("<TABLE BORDER=\"1\">");

	    int taille = rsmd.getColumnCount();
	    
	    for(int i=1; i<=taille; i++){
		out.println("<TH>"+rsmd.getColumnName(i)+"</TH>");
	    }

	    while(rs.next()){
		out.println("<TR>");
		for(int j=1; j<=taille; j++){
		    out.println("<TD>");
		    out.println(rs.getString(j));
		    out.println("</TD>");
		}
		out.println("</TR>");
	    }

	    out.println("</TABLE></CENTER></BODY>");

	}catch(Exception ex){
	    out.println(ex.getMessage());
	}finally{
	    try{
		con.close();
	    }catch(Exception exc){
		out.println(exc.getMessage());
	    }
	}
    }
}

	    
	    
										
