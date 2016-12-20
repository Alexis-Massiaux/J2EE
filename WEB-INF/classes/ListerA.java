import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/ListerA")
public class ListerA extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws  ServletException, IOException{
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	out.println("<HEAD><TITLE>Lister</TITLE>");
	out.println("<BODY><CENTER><H1>Lister</H1><TABLE BORDER=\"1\">");

	String order = "nom";
	String sens = "asc";

	if(req.getParameter("ordre") != null)
	    order=req.getParameter("ordre");

	if(req.getParameter("sens") != null){
	    if(req.getParameter("sens").equals("asc"))
		sens="desc";
	    else if(req.getParameter("sens").equals("desc"))
		sens="asc";
	}
	    

	out.println("<th><a href=http://localhost:8080/bonus/servlet/ListerA?ordre=nom&sens="+sens+">Nom</a></th>");
	out.println("<th><a href=http://localhost:8080/bonus/servlet/ListerA?ordre=annee&sens="+sens+">Annee</a></th>");
	out.println("<th><a href=http://localhost:8080/bonus/servlet/ListerA?ordre=nationalite&sens="+sens+">Nationalite</a></th>");
	out.println("<th><a href=http://localhost:8080/bonus/servlet/ListerA?ordre=categ&sens="+sens+">Categorie</a></th>");
	out.println("<th><a href=http://localhost:8080/bonus/servlet/ListerA?ordre=club&sens="+sens+">Club</a></th>");
	out.println("<th><a href=http://localhost:8080/bonus/servlet/ListerA?ordre=temps&sens="+sens+">Temps</a></th>");
	

	Connection con = null;

	try{
	    Class.forName("org.postgresql.Driver");
	    String url="jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url, "alex", "pass");
	    Statement stm = con.createStatement();
	    String query = "SELECT * FROM Resultats ORDER BY "+order+" "+sens+";";
	    ResultSet rs = stm.executeQuery(query);

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
