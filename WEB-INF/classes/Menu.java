import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Menu")
public class Menu extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	Connection con = null;

	try{
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url, "alex", "pass");
	    Statement stmt = con.createStatement();

	    String login = req.getParameter("Utilisateur");
	    String mdp = req.getParameter("Mdp");

	    HttpSession session = req.getSession(true);
	    session.setMaxInactiveInterval(60);
	    session.setAttribute("login", login);
	    session.setAttribute("mdp", mdp);

	    String query = "Select rôle from Personne where login like '"+login+"' and mdp like '"+mdp+"' ;";
	    ResultSet rs = stmt.executeQuery(query);

	    if(!(rs.next())){
	        res.sendRedirect("/bonus/login.html");
	    }else{
		session.setAttribute("role", rs.getString(1));
		out.println("<HTML><HEAD><TITLE>Menu</TITLE></HEAD><BODY>");
		out.println("<CENTER><H1>Menu<H1>");
		out.println("<A HREF=\"/bonus/servlet/Lecture\">Lecture</A>");
		out.println("<BR><BR><A HREF=\"/bonus/servlet/Modif\">Ecriture</A>");
		out.println("<BR><BR><A HREF=\"/bonus/servlet/Deconnect\">Deconnexion</A>");
	    }
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
	    
			     
