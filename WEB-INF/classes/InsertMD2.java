import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/InsertMD2")
public class InsertMD2 extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con = null;

	String table = req.getParameter("Table");

	try{
	    Class.forName("org.postgresql.Driver");
	    String url ="jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex","pass");
	    Statement stm = con.createStatement();
	    String query = "SELECT * FROM "+table;
	    ResultSet rs = null;
	    try{
		rs=stm.executeQuery(query);
	    }catch(Exception e){
		out.println(e.getMessage());
		try{
		    con.close();
		}catch(Exception ex){
		    out.println(ex.getMessage());
		}
	    }
	    ResultSetMetaData rsmd = rs.getMetaData();
	    
	    int taille = rsmd.getColumnCount();
	    query = "INSERT INTO "+table+" VALUES(";

	    for(int i=1; i<=taille; i++){
		if(i+1<=taille)
		    query+="'"+req.getParameter(rsmd.getColumnName(i))+"',";
		else
		    query+="'"+req.getParameter(rsmd.getColumnName(i))+"');";
	    }
	    
	    rs = stm.executeQuery(query);
		
	}catch(Exception e){
	    out.println(e.getMessage());
	}finally{
	    try{
		con.close();
		res.sendRedirect("/bonus/servlet/SelectMD?table="+table);
	    }catch(Exception e){
		out.println(e.getMessage());
	    }
	}
    }
}
