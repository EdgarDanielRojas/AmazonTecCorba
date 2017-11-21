<%@ page import="java.util.StringTokenizer" %>
<%
   String artist = request.getParameter("artista");
   String datos = request.getParameter("album");
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
        <h1 class="text-center">Albums del artista: <%=artist%></h1>
        <%
            String nombre="",artista="",ano="",id="",precio="",cantidad="";
            int number = tokenizer.countTokens();
            funcWrite("<div class='row'>",out);
            for(int i=0;i<number;i++){
                StringTokenizer secondTokenizer = new StringTokenizer(tokenizer.nextToken(),"_");
                id = secondTokenizer.nextToken();
                artista=secondTokenizer.nextToken();
                nombre=secondTokenizer.nextToken();
                ano = secondTokenizer.nextToken();
                precio = secondTokenizer.nextToken();
                cantidad = secondTokenizer.nextToken();
                funcWrite("<div class='col-lg-3 col-md-4 col-sm-12'>",out);
                funcWrite("<div class='text-center'>",out);
                funcWrite("<a href='../amazonTecReloadedCorba/amazon?bIngresado=Consulta Canciones&album="+nombre+"&id="+id+"'>",out);
                funcWrite("<img src='../amazonTecReloadedCorba/img/"+id+".jpg' height='200' width='200' alt='Album "+nombre+"' class='img-thumbnail'>",out);
                funcWrite("<br>",out);
                funcWrite("<p>"+nombre+"</p>",out);
                funcWrite("</a>",out);
                funcWrite("<p>"+artista+"</p>",out);
                funcWrite("<p>"+ano+"</p>",out);
                funcWrite("<a href='../amazonTecReloadedCorba/amazon?bIngresado=Comprar Album&album="+nombre+"&id="+id+"'>",out);
                funcWrite("<button type='button' class='btn btn-outline-info'>Precio: $"+precio+"0</button>",out);
                funcWrite("</a>",out);
                funcWrite("<br><br>",out);
                funcWrite("</div>",out);
                funcWrite("</div>",out);
            }
            funcWrite("</div>",out);
        %>
    </div>
</body>
</html>