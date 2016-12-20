import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/ListerB")
public class ListerB extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	out.println("<HEAD><TITLE>Lister</TITLE>");
	out.println("<BODY><CENTER><H1>Lister</H1>");

	String ordre = "nom";
	String tri="";

	if(req.getParameter("ordre") != null)
	   ordre=req.getParameter("ordre");

	if(req.getParameter("tri") != null)
	    tri=req.getParameter("tri");
	
	//TextFields
	out.println("<FORM ACTION=\"ListerB\" method=get>Ordre<BR><INPUT TYPE=\"text\" name=\"ordre\" value=\""+ordre+"\"/><BR>");
	out.println("Tri<BR><INPUT TYPE=\"text\" name=\"tri\" value=\""+tri+"\"/><BR><BR>");
	out.println("<INPUT TYPE=\"submit\" value=\"Submit\"/></FORM><BR><BR>");
	//Table
	out.println("<TABLE BORDER=\"1\">");

	Connection con = null;

	try{
	    Class.forName("org.postgresql.Driver");
	    String url="jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url, "alex", "pass");
	    Statement stm = con.createStatement();
	    String query="";
	    if(tri.equals(""))
		query = "SELECT * FROM Resultats ORDER BY "+ordre+";";
	    else
		query = "SELECT * FROM Resultats WHERE "+tri+" ORDER BY "+ordre+";"; 
	    ResultSet rs;

	    try{
		rs=stm.executeQuery(query);   
	    }catch(Exception e){
		if(tri.equals(""))
		    out.println("La colone "+ordre+" n'existe pas...<BR><BR>");
		else
		    out.println("La colone "+ordre+" n'existe pas OU le filtre \""+tri+"\" est impossible à réaliser...<BR><BR>");
		rs=stm.executeQuery("SELECT * FROM Resultats;");
	    }

	    while(rs.next()){
		out.println("<TR><TD>"+rs.getString("nom")+"</TD><TD>"+rs.getInt("annee")+"</TD><TD>"+rs.getString("nationalite")+"</TD><TD>"+rs.getString("categ")+"</TD><TD>"+rs.getString("club")+"</TD><TD>"+rs.getInt("temps")+"</TD></TR>");
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
	
