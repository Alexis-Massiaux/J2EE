/*
import java.io.*;
import java.sql.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

@WebServlet("/servlet/Compteur")
public class Compteur extends HttpServlet{
    int cpt=0;
    public void service(HttpServletResponse res, HttpServletRequest req) throws IOException, ServletException{

	res.setContentType("text/html");
	PrintWriter out = res.getWriter();
	cpt++;
	out.println("<HTML><HEAD><TITLE>Compteur</TITLE></HEAD>");
	out.println("<BODY><CENTER><H1>Compteur</H1>");
	out.println("<P>Vous avez acceder à cette page "+cpt+" nombre de fois</P>");
	out.println("</CENTER></BODY></HTML>");
    }
}
*/

import java.io.*;
import javax.servlet.*;      // pour les servlets
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
@WebServlet("/servlet/Compteur")
public class Compteur extends HttpServlet
{ int cpt=0;
    public void doGet(HttpServletRequest req, HttpServletResponse res) // ! doGet
	throws ServletException, IOException
    {
	res.setContentType("text/html;charset=UTF-8");
	PrintWriter out = res.getWriter();
	HttpSession session = req.getSession(true);
	Integer cpt2 = (Integer)session.getAttribute("Compteur");
	if(cpt2==null)
	    cpt2=1;
	else
	    cpt2++;
	session.setAttribute("Compteur", cpt2);
	cpt++;
	out.println("<head> <title>Implémenter un compteur</title> ");
	out.println("</head> <body>");
	out.println("<h1> Le nombre de chargements(globale) est : "+ cpt +" et le nombre de chargements(local) est "+cpt2+"</h1>");
	out.println("</body>");
    }
}
