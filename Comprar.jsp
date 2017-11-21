<%
   String costo = request.getParameter("precio");
   String id = request.getParameter("idAlbum");
   String album = request.getParameter("nombreAlbum");
   String email = request.getParameter("email");
   String nombre = request.getParameter("nombre");
   String direccion = request.getParameter("direccion");
   String notarjeta = request.getParameter("notarjeta");
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
<script>
function UpdateInfo()
{
  var can = document.getElementById('cantidad').value;
  document.getElementById('total').value = can*<%=costo%>;
}    
</script>
<body>
    <div class="container">
        <h1 class="text-center">Compra del Album <%= album%></h1>
        
        <div class="text-center">
            <img src='../amazonTecReloadedCorba/img/<%=id%>.jpg' height="300" width="300" alt='Album <%=album%>' class='img-thumbnail'>
        </div>
        <br>
        <h5 class="text-center">Email: <%= email%></h5>
        <h5 class="text-center">Nombre: <%= nombre%></h5>
        <h5 class="text-center">Direccion: <%= direccion%></h5>
        <h5 class="text-center">No. de Tarjeta: <%= notarjeta%></h5>
        <h5 class="text-center">Album: <%= album%></h5>
        <h5 class="text-center">Costo: $<%= costo%></h5>
        <form action='../amazonTecReloadedCorba/amazon' method='get' class='form=inline'>
                <input type='hidden' name="id" value='<%=id%>'>
                <input type='hidden' name="email" value='<%=email%>'>
                <label class="" for="cantidad">Cantidad:</label>
                <div class="form=group">
                    <input class='form-control' id='cantidad' type='number' name='tfCantidad' value='1' onchange="UpdateInfo()" required>
                </div>
                <br>
                <label class="" for="total">Total:</label>
                <div class="form=group">
                    <input class='form-control' id='total' type='number' name='tfTotal' value=<%= costo%> readonly>
                </div>
                <div class="text-center">
                    <input class="btn btn-primary" type='submit' name='bIngresado' value='Registrar Compra'>
                    <input class="btn btn-secondary" type='submit' name='bIngresado' value='Cancelar' formnovalidate>
                </div>
        </form>
    </div>
</body>
</html>