
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Authent")
public class Authent extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");
	out.println("<head><title>Authent</title></head><body><center>");
	out.println("<h1>Authentification</h1>");

	Connection con = null;
	
	try{
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex","pass");
	    Statement stmt = con.createStatement();

	    //Recuperation des identifiants
	    String utilisateur = req.getParameter("Utilisateur");
	    String mdp = req.getParameter("Mdp");
	
	    boolean connexion = false;
	    
	    //Session
	    HttpSession session = req.getSession(true);
	    session.setAttribute("Utilisateur", utilisateur);
	    session.setAttribute("Mdp", mdp);

	    String query = "SELECT login, mdp FROM Personne WHERE login LIKE '"+utilisateur+"' AND mdp LIKE '"+mdp+"';";

	    ResultSet rs = stmt.executeQuery(query);

	    if(rs.next())
		connexion = true;

	    if(connexion){
		out.println("Connexion réussie");
		res.sendRedirect("/bonus/menu.html");
	    }else{
		out.println("Mot de passe ou login incorrect");
		res.sendRedirect("/bonus/login.html");
	    }
	    out.println("</center></body></html>");
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
