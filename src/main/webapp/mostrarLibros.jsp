<%@ page import="com.biblioteca.tareabiblioteca.model.Libro" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: jluen
  Date: 14-06-2023
  Time: 0:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Mostrar usuarios</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1>Lista de libros encontrados</h1>
<div class="tabla">
    <table>
        <tr>
            <th>Titulo</th>
            <th>Editorial</th>
            <th>Categoria</th>
            <th>Año publicacion</th>
            <th>Tipo libro</th>
        </tr>
        <c:forEach items="${libros}" var="libro">
            <tr>
                <td> <c:out value="${libro.getTitulo()}"></c:out> </td>
                <td> <c:out value="${libro.getEditorial()}"></c:out> </td>
                <td> <c:out value="${libro.getCategoria().getNombre()}"></c:out> </td>
                <td> <c:out value="${libro.getAño()}"></c:out> </td>
                <td> <c:out value="${libro.getTipoLibro()}"></c:out> </td>
            </tr>
        </c:forEach>
    </table>
    <form>
        <a href="index.jsp" class="button-link">Volver al menú</a>
    </form>
</div>
</body>
</html>
