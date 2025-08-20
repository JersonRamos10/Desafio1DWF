<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 17/8/2025
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>${not empty departamento ? 'Editar' : 'Agregar'} Departamento</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<div class="container mt-5">
    <h2 class="mb-4">${not empty departamento ? 'Editar' : 'Agregar'} Departamento</h2>
    <form action="${pageContext.request.contextPath}/departamento?action=${not empty departamento ? 'update' : 'create'}" method="post">

        <c:if test="${not empty departamento}">
            <input type="hidden" name="id" value="${departamento.idDepartamento}" />
        </c:if>

        <div class="form-group">
            <label for="nombreDepartamento">Nombre del Departamento</label>
            <input type="text" class="form-control" id="nombreDepartamento" name="nombreDepartamento" value="${departamento.nombreDepartamento}" required maxlength="50">
        </div>

        <div class="form-group">
            <label for="descripcionDepartamento">Descripción del Departamento</label>
            <textarea class="form-control" id="descripcionDepartamento" name="descripcionDepartamento" required>${departamento.descripcionDepartamento}</textarea>
        </div>

        <button type="submit" class="btn btn-primary">Guardar</button>
        <a href="${pageContext.request.contextPath}/departamento?action=list" class="btn btn-secondary">Cancelar</a>
    </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>