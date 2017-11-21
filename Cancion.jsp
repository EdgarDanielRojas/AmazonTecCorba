<%@ page import="java.util.StringTokenizer" %>
<%
   String datos = request.getParameter("cancion");
   String id = request.getParameter("id");
   String album = request.getParameter("album");
   StringTokenizer tokenizer = new StringTokenizer(datos,"*");
%>
<%!
    private void funcWrite(String Bits, javax.servlet.jsp.JspWriter myOut)
    {  
      try{ myOut.println(Bits); } 
      catch(Exception eek) { }
    }
%>
<html>
<head>
    <title>Servlets y JSPs</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="text-center">Canciones del Album <%= album%></h1>
        <div class="text-center">
            <img src='../amazonTecReloadedCorba/img/<%=id%>.jpg' height="300" width="300" alt='Album <%=album%>' class='img-thumbnail'>
        </div>
        <table class="table table-hover">
            <thead class="thead-default">
                <tr>
                    <th>#</th>
                    <th>Nombre</th>
                    <th>Compositor</th>
                    <th>Duracion</th>
                    <th>Reproducir</th>
                </tr>
            </thead>
            <tbody>
                <%
                    String nombre="",compositor="",duracion="";
                    int number = tokenizer.countTokens();
                    int counter = 1;
                    for(int i=0;i<number;i++){
                        StringTokenizer secondTokenizer = new StringTokenizer(tokenizer.nextToken(),"_");
                        nombre=secondTokenizer.nextToken();
                        compositor=secondTokenizer.nextToken();
                        duracion = secondTokenizer.nextToken();
                        
                        funcWrite("<tr>",out);
                        funcWrite("<th scope='row'>"+counter+"</th>",out);
                        
                        funcWrite("<td>",out);
                        funcWrite("<a href='../amazonTecReloadedCorba/CancionSolita.jsp?cancion="+nombre+"&id="+id+"'>",out);
                        funcWrite(nombre,out);
                        funcWrite("</a>",out);
                        funcWrite("</td>",out);
                        funcWrite("<td>"+compositor+"</td>",out);
                        funcWrite("<td>"+duracion+"</td>",out);
                        funcWrite("<td>",out);
                        funcWrite("<audio controls>",out);
                        funcWrite("<source src='../amazonTecReloadedCorba/audio/"+nombre+".mp3' type='audio/mpeg'",out);
                        funcWrite("<source src='../amazonTecReloadedCorba/audio/"+nombre+".wav' type='audio/wav'",out);
                        funcWrite("</audio>",out);
                        funcWrite("</td>",out);
                        funcWrite("</tr>",out);
                        counter++;
                    }
                %>
            </tbody>
        </table>
        
    </div>
</body>
</html>