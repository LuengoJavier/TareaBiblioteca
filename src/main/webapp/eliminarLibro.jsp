<%@ page import = "java.util.ArrayList" %>
<%@ page import = "com.biblioteca.tareabiblioteca.model.Libro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jluen
  Date: 13-06-2023
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="style.css">
    <title>Eliminar Libro</title>
</head>
<body>
<h1> Eliminar libro</h1>
<form action="eliminarLibro" method="post">
    <div class="form-group">
        <label>
            <select name ="libro">
                <option value ="0">Libro</option>
                <c:forEach items ="${libros}" var ="libro">
                    <option value ="${libro.getId()}">${libro.getTitulo()}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <input type="submit" value="Eliminar" class="boton">
</form>
</body>
</html>

