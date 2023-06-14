<%@ page import="java.util.ArrayList" %>
<%@ page import="com.biblioteca.tareabiblioteca.model.Categoria" %>
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
    <title>Busqueda Libro</title>
</head>
<body>
<h1>Busqueda de libro por categoria</h1>
<form action="buscarLibro" method="post">
    <div class="form-group">
        <label>
            <select name="categoria">
                <option value="0">Categoria</option>
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.getId()}">${categoria.getNombre()}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <input type="submit" value="Buscar" class="boton">
</form>
</body>
</html>
