<%@ page import="metier.Personne" %>
<%@ page session="true" %>

<HTML>
	<HEAD>
		<TITLE>Compteur</TITLE>
	</HEAD>
	<BODY>
		<CENTER>
			<H1>Compteur</H1>
			<%!Personne personne = new Personne();
			   int cpt = 0;
			   HttpSession session;%>
			<%session = request.getSession(true);
			  if(!(session.getAttribute("cpt") == null))
			     cpt=(int)session.getAttribute("cpt");
			  cpt ++;
			  session.setAttribute("cpt", cpt);
			  out.println(cpt);
			 %><BR><BR>
			<%=personne.toString()%>
			
		</CENTER>
	</BODY>
</HTML>