<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <link rel ="stylesheet" type="text/css" href="style.css">
    <title>Menú Bliblioteca</title>
</head>
<body>
<h1>Menú principal biblioteca</h1>
<br/>
<div class="form-group">
    <form action="registroCategoria" method="get">
        <input type="submit" value="Registrar Categoria" class="boton">
    </form>
    <br>
    <form action="registroLibro" method="get">
        <input type="submit" value="Registrar Libro" class="boton">
    </form>
    <br>
    <form action="buscarLibro" method="get">
        <input type="submit" value="Buscar libro" class="boton">
    </form>
</div>
</body>
</html>