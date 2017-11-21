<%
   String datos = request.getParameter("cancion");
   String id = request.getParameter("id");
%>
<html>
<head>
    <title>Cancion <%=datos%></title>
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0" >
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta/css/bootstrap.min.css" integrity="sha384-/Y6pD6FV/Vv2HJnA6t+vslU6fwYXjCFtcEpHbNJ0lyAFsXTsjBbfaDjzALeQsN6M" crossorigin="anonymous">
</head>
<body>
    <div class="container">
        <h1 class="text-center">Cancion <%= datos%></h1>
        <div class="text-center">
            <img src='../amazonTecReloadedCorba/img/<%=id%>.jpg' height="300" width="300" class='img-thumbnail'>
            <br>
            <audio controls class='text-center'>
                <source src='../amazonTecReloadedCorba/audio/<%=datos%>.mp3' type='audio/mpeg'>
                <source src='../amazonTecReloadedCorba/audio/<%=datos%>.wav' type='audio/wav'>
            </audio>
        </div>
        
    </div>
</body>
</html>