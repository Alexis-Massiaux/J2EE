import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/UpdateFrm")
public class UpdateFrm extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con  = null;

	String table = req.getParameter("table");

	try{
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex","pass");
	    Statement stmt = con.createStatement();
	    String query = "SELECT * FROM "+table+" ;";
	    ResultSet rs = null;
	    try{
		rs = stmt.executeQuery(query);
	    }catch(Exception ex){
		out.println(ex.getMessage());
		try{
		    con.close();
		}catch(Exception exp){
		    out.println(exp.getMessage());
		}
	    }

	    out.println("<HEAD><TITLE>Update</TITLE></HEAD>");
	    out.println("<BODY><CENTER><H1>Formulaire pour la table "+table+"</H1><TABLE BORDER = \"1\">");
	    
	    ResultSetMetaData rsmd = rs.getMetaData();

	    int taille = rsmd.getColumnCount();
	    //Permet de connaitre la case exact (avec colone_modif(classe Update))
	    String colone_primary = rsmd.getColumnName(1);

	    for(int i=1; i<=taille; i++)
		out.println("<TH>"+rsmd.getColumnName(i)+"</TH>");

	    while(rs.next()){
		out.println("<TR>");
		for(int j=1; j<=taille; j++){
		    out.println("<TD>");
		    out.println("<FORM ACTION = \"/bonus/servlet/Update\" METHOD=GET>");
		    out.println("<INPUT TYPE=\"text\" NAME=\"modif\" VALUE=\""+rs.getString(j)+"\"/><INPUT TYPE=\"hidden\" NAME=\"table\" VALUE=\""+table+"\"/><INPUT TYPE=\"hidden\" NAME=\"colone_modif\" VALUE=\""+rsmd.getColumnName(j)+"\"/><INPUT TYPE=\"hidden\" NAME=\"colone_primary\" VALUE=\""+colone_primary+"\"/><INPUT TYPE=\"hidden\" NAME=\"primary\" VALUE=\""+rs.getString(colone_primary)+"\"/></FORM></TD>");
		}
		out.println("</TR>");
	    }
	    
	    out.println("</TABLE></CENTER></BODY>");
	    
	}catch(Exception e){
	    out.println(e.getMessage());
	}finally{
	    try{
		con.close();
	    }catch(Exception e){
		out.println(e.getMessage());
	    }
	}
    }
}
		

	    

	    
