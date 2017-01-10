import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Modif")
public class Modif extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException{
	
	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	//Session - recuperation des identifiants
	HttpSession session = req.getSession(true);
	String login = (String)session.getAttribute("login");
	String mdp = (String)session.getAttribute("mdp");

	if(login == null || mdp == null)
	    res.sendRedirect("/bonus/login.html");

	String role = (String)session.getAttribute("role");
	
	out.println("<HEAD><TITLE>Modification</TITLE></HEAD><BODY><CENTER>");
	out.println("<H1>Modification - "+login+"</H1>");

	Connection con = null;
	
	try{
	    Class.forName("org.postgresql.Driver");
	    String url = "jdbc:postgresql:dtbase";
	    con = DriverManager.getConnection(url,"alex","pass");
	    Statement stmt = con.createStatement();
	    String query="";

	    //Recuperation des donnees modifies
	    String colone = req.getParameter("colone_modif");
	    String value = req.getParameter("modification");
	    String login2 = req.getParameter("login_modif");

	    if(colone != null && value != null){
		query = "UPDATE Personne SET "+colone+" = '"+value+"' WHERE login LIKE '"+login2+"' ;";
	        stmt.executeUpdate(query); //int executeUpdtate(String) ! 
	    }
	    
	    if(role.equals("util"))
		query = "SELECT * FROM Personne WHERE login LIKE '"+login2+"' AND mdp LIKE '"+mdp+"' ;";
	    else
		query = "SELECT * FROM Personne;";

	    ResultSet rs = stmt.executeQuery(query);
	    

	    ResultSetMetaData rsmd = rs.getMetaData();

	    out.println("<TABLE BORDER =\"1\">");

	    //recuperation nbr colone
	    int taille = rsmd.getColumnCount();

	    //affichage titre colone
	    for(int i=1; i<=taille; i++)
		out.println("<TH>"+rsmd.getColumnName(i)+"</TH>");

	    //affichage table
	    while(rs.next()){
		out.println("<TR>");
		for(int j=1; j<=taille; j++)
		    out.println("<TD><FORM ACTION=\"/bonus/servlet/Modif\" METHOD=GET><INPUT TYPE=\"text\" NAME=\"modification\" VALUE=\""+rs.getString(j)+"\"/><INPUT TYPE=\"hidden\" NAME=\"colone_modif\" VALUE=\""+rsmd.getColumnName(j)+"\"/><INPUT TYPE=\"hidden\" NAME=\"login_modif\" VALUE=\""+rs.getString(1)+"\"/></FORM></TD>");
		out.println("</TR>");
	    }

	    out.println("</TABLE></CENTER></BODY></HTML>");
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
			
		
