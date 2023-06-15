<%--
  Created by IntelliJ IDEA.
  User: jluen
  Date: 13-06-2023
  Time: 11:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Registro Categoria</title>
</head>
<body>
<h1>Registro Categoria</h1>
<form action="registroCategoria" method="post">
    <div class="form-group">
        <label for="id_categoria">Codigo Categoria:</label>
        <input type="text" id="id_categoria" name="id_categoria" required>
    </div>
    <div class="form-group">
        <label for= "nombre">Nombre:</label>
        <input type="text" id = "nombre" name="nombre" required>
    </div>
    <input type="submit" value="Registrar" class="boton">
</form>
</body>
</html>