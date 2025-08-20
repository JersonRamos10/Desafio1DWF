<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 15/8/2025
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>${not empty tipoContratacion ? 'Editar' : 'Agregar'} Tipo de Contratacion</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<div class="container mt-5">
    <h2 class="mb-4">${not empty tipoContratacion ? 'Editar' : 'Agregar'} Tipo de Contratación</h2>
    <form action="${pageContext.request.contextPath}/tipoContratacion?action=${not empty tipoContratacion ? 'update' : 'create'}" method="post">

        <c:if test="${not empty tipoContratacion}">
            <input type="hidden" name="id" value="${tipoContratacion.idTipoContratacion}" />
        </c:if>

        <div class="form-group">
            <label for="tipoContratacion">Nombre del Tipo de Contratación</label>
            <input type="text" class="form-control" id="tipoContratacion" name="tipoContratacion" value="${tipoContratacion.tipoContratacion}" required maxlength="100">
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/tipoContratacion?action=list" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>