<%@ page import="java.util.ArrayList" %>
<%@ page import="com.biblioteca.tareabiblioteca.model.Categoria" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%--
  Created by IntelliJ IDEA.
  User: jluen
  Date: 13-06-2023
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registro categoria</title>
    <link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>
<h1> Registro categoria</h1>
<h2>Datos Incorrectos o libro ya registrada, favor comprobar nuevamente</h2>
<form action="registroLibro" method="post">
    <div class="form-group">
        <label for="id_libro">Codigo Libro:</label>
        <input type="text" id="id_libro" name="id_libro" required>
    </div>
    <div class="form-group">
        <label for= "titulo">Título:</label>
        <input type="text" id = "titulo" name="titulo" required>
    </div>
    <div class="form-group">
        <label for= "editorial">Editorial:</label>
        <input type="text" id = "editorial" name="editorial" required>
    </div>
    <div class="form-group">
        <label class="form-group">
            <select name="categoria">
                <option value="0">Categoria</option>
                <c:forEach items="${categorias}" var="categoria">
                    <option value="${categoria.getId()}">${categoria.getNombre()}</option>
                </c:forEach>
            </select>
        </label>
    </div>
    <div class="form-group">
        <label for= "año">Año publicación:</label>
        <input type="text" id = "año" name="año" required>
    </div>
    <div class="form-group">
        <label for= "tipo_libro">Físico o Digital:</label>
        <input type="text" id = "tipo_libro" name="tipo_libro" required>
    </div>
    <input type="submit" value="Registrar" class="boton">
</form>
</body>
</html>

