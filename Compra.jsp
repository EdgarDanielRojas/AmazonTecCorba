<%
   String costo = request.getParameter("costo");
   String id = request.getParameter("idAlbum");
   String album = request.getParameter("album");
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
    <title>Compra de album <%=album%></title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="text-center">Compra del Album <%= album%></h1>
        <h3 class="text-center">Costo: $<%= costo%></h3>
        <div class="text-center">
            <img src='../amazonTecReloadedCorba/img/<%=id%>.jpg' height="300" width="300" alt='Album <%=album%>' class='img-thumbnail'>
        </div>
        <br>
        <div class="alert alert-primary text-center" role="alert">
          Sign in para poder comprar o Registrese como nuevo usuario.
        </div>
            <form action='../amazonTecReloadedCorba/amazon' method='get' class='form=inline'>
                <input type='hidden' name="id" value='<%=id%>'>
                <div class="form=group">
                    <label class="" for="Email">Email:</label>
                    <input class='form-control' id='Email' type='text' name='tfEmail' placeholder='Su Correo Electronico' required>
                </div>
                <div class="form=group">
                    <label class="" for="password">Password:</label>
                    <input class='form-control' id='password' type='password' name='tfPassword' placeholder="Ingrese password" required>
                </div>
                <br>
                <div class="text-center">
                    <input class="btn btn-primary" type='submit' name='bIngresado' value='Sign in'>
                    <input class="btn btn-secondary" type='submit' name='bIngresado' value='Registrar' formnovalidate>
                </div>
        </form>
    </div>
</body>
</html>