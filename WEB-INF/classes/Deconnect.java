import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import java.sql.*;

@WebServlet("/servlet/Deconnect")
public class Deconnect extends HttpServlet{
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException{

	PrintWriter out = res.getWriter();
	res.setContentType("text/html");

	HttpSession session = req.getSession(true);
	session.invalidate();
	res.sendRedirect("/bonus/login.html");
    }
}
