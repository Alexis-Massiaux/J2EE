<%@ page import="java.io.*, java.sql.*, javax.servlet.*, javax.servlet.http.*, javax.servlet.annotation.WebServlet" %>
<HTML>
	<HEAD>
		<TITLE>Afficher</TITLE>
	</HEAD>
	<BODY>
		<CENTER>
			<H1>Affichage d'une table</H1>
			<TABLE BORDER = 1>
			<%

			Connection con = null;

			try{
				Class.forName("org.postgresql.Driver");
	    			String url="jdbc:postgresql:dtbase";
	    			con = DriverManager.getConnection(url, "alex", "pass");
	    			Statement stm = con.createStatement();
	    			String query = "SELECT * FROM Resultats;";
	    			ResultSet rs = stm.executeQuery(query);

	    			while(rs.next()){%>
		                <TR><TD><%=rs.getString("nom")%>
				</TD><TD><%=rs.getInt("annee")%>
				</TD><TD><%=rs.getString("nationalite")%>
				</TD><TD><%=rs.getString("categ")%>
				</TD><TD><%=rs.getString("club")%>
				</TD><TD><%=rs.getInt("temps")%>
				</TD></TR>
			    <%  }
			    }catch(Exception e){%>
			    	<%=e.getMessage()%>
			    <%}finally{
				try{
					con.close();
				}catch(Exception e){%>
					<%=e.getMessage()%>
				<%}
			    }%>
			</TABLE>

		</CENTER>
	</BODY>
</HTML>
			