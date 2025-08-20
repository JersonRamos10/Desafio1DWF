<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 16/8/2025
  Time: 10:02
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>Lista de Cargos</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Lista de Cargos</h2>
        <a href="${pageContext.request.contextPath}/puesto?action=showCreateForm" class="btn btn-primary">Crear Nuevo Cargo</a>
    </div>

    <table class="table table-bordered table-hover text-center">
        <thead class="thead-dark">
        <tr>
            <th>ID</th>
            <th>Nombre del Cargo</th>
            <th>Descripción</th>
            <th>Es Jefatura</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="puesto" items="${puestos}">
            <tr>
                <td><c:out value="${puesto.idCargo}"/></td>
                <td><c:out value="${puesto.cargo}"/></td>
                <td><c:out value="${puesto.descripcionCargo}"/></td>
                <td>
                    <c:if test="${puesto.jefatura}">
                        <span class="badge badge-success">Sí</span>
                    </c:if>
                    <c:if test="${!puesto.jefatura}">
                        <span class="badge badge-secondary">No</span>
                    </c:if>
                </td>
                <td class="d-flex justify-content-center">
                    <a href="${pageContext.request.contextPath}/puesto?action=edit&id=${puesto.idCargo}" class="btn btn-success mr-2">Editar</a>
                    <a href="${pageContext.request.contextPath}/puesto?action=delete&id=${puesto.idCargo}" class="btn btn-danger" onclick="return confirm('¿Seguro que quieres eliminar este cargo?');">Borrar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>