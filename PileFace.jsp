<%@ page pageEncoding="UTF-8" %>
<%@ page import="PileFace.PileFace" %>
<HTML>
	<HEAD>
		<TITLE>PileOuFace</TITLE>
	</HEAD>
	<BODY>
		<CENTER>
			<H1>Pile ou Face</H1>
			<%!PileFace pf = new PileFace();%>
			<P>Le premier arrivé à 10 a gagné !</P><BR>
			<P>Deux faces identiques, je gagne ! Deux faces différentes, vous gagnez...</P><BR>
			<%if(pf.termine()){
				if(pf.getPointsOrdi() == 10)
					out.println("Vous avez PERDU !");
				else
					out.println("Vous avez GAGNEZ...");
				pf = new PileFace();
			  out.println("<A HREF=\"PileFace.jsp\">Recommencez</A>");
			}else{
				if(pf.numPartie != 0){
					     pf.play((request.getParameter("coup")).charAt(0));
					     out.println("Coups : (vous) :"+pf.getLastHumain()+" - (moi) :"+pf.getLastOrdi());%>
					     <BR>
					     <%out.println("Score : (vous) :"+pf.getPointsHumain()+" - (moi) :"+pf.getPointsOrdi());}%><BR><BR>
				<%

				out.println("<A HREF=\"PileFace.jsp?coup=P\">Pile</A>");
				out.println("<A HREF=\"PileFace.jsp?coup=F\">Face</A>");
				
			}%>
					     
		</CENTER>
	</BODY>
</HTML>