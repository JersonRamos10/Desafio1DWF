<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 17/8/2025
  Time: 22:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit-no">
    <title>Lista de Departamentos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!--incluye el navbar para usarlo en esta jsp-->
<%@ include file="../navbar.jsp" %>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Lista de Departamentos</h2>
        <a href="${pageContext.request.contextPath}/departamento?action=showCreateForm" class="btn btn-primary">Crear Nuevo Departamento</a>
    </div>

    <c:choose>
        <c:when test="${not empty departamentos}">
            <table class="table table-bordered table-hover text-center">
                <thead class="thead-dark">
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Descripción</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="departamento" items="${departamentos}">
                    <tr>
                        <td><c:out value="${departamento.idDepartamento}"/></td>
                        <td><c:out value="${departamento.nombreDepartamento}"/></td>
                        <td><c:out value="${departamento.descripcionDepartamento}"/></td>
                        <td class="d-flex justify-content-center">
                            <a href="${pageContext.request.contextPath}/departamento?action=edit&id=${departamento.idDepartamento}" class="btn btn-success mr-2">Editar</a>
                            <a href="${pageContext.request.contextPath}/departamento?action=delete&id=${departamento.idDepartamento}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este departamento?');">Borrar</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info" role="alert">
                No hay departamentos disponibles.
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>