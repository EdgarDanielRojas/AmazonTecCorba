<%
   String id = request.getParameter("idAlbum");
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
    <title>Registro de nuevo usuario</title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="text-center">Registro de nuevo usuario</h1>
        <h3 class="text-center">Estas a un paso de poder comprar en AmazonTec!</h3>
        <br>
        <div class="alert alert-info text-center" role="alert">
          Llene todos los campos para continuar
        </div>
            <form action='../amazonTecReloadedCorba/amazon' method='get' class=''>
                <input type='hidden' name="id" value='<%=id%>'>
                <div class="form-group">
                    <label class="" for="Email">Email:</label>
                    <input class='form-control' id='Email' type='text' name='tfEmail' placeholder='Su Correo Electronico' required>
                </div>
                <div class="form-group">
                    <label class="" for="password">Password:</label>
                    <input class='form-control' id='password' type='password' name='tfPassword' placeholder="Ingrese password" required>
                </div>
                <div class="form-group">
                    <label class="" for="nom">Nombre:</label>
                    <input class='form-control' id='nom' type='text' name='tfNombre' placeholder="Ingrese su nombre completo" required>
                </div>
                <div class="form-group">
                    <label class="" for="direc">Direccion:</label>
                    <input class='form-control' id='direc' type='text' name='tfDireccion' placeholder="Ingrese su direccion completa" required>
                </div>
                <div class="form-group">
                    <label class="" for="tel">Telefono:</label>
                    <input class='form-control' id='tel' type='text' name='tfTelefono' placeholder="Ingrese telefono" required>
                </div>
                <div class="form-group">
                    <label class="" for="notar">No. de Tarjeta:</label>
                    <input class='form-control' id='tel' type='text' name='tfNoTarjeta' placeholder="Ingrese Numero de Tarjeta" required>
                </div>
                <div class="form-group">
                    <label for="sel1">Seleccione su tipo de tarjeta:</label>
                    <select class="form-control" id="sel1" name="tfTarjeta">
                        <option selected="selected">Master Card</option>
                        <option>Visa</option>
                        <option>American Express</option>
                        <option>Otro</option>
                    </select>
                </div>
                <br>
                <div class="text-center">
                    <input class="btn btn-primary" type='submit' name='bIngresado' value='Capturar Datos'>
                    <input class="btn btn-secondary" type='submit' name='bIngresado' value='Cancelar' formnovalidate>
                </div>
        </form>
    </div>
</body>
</html>