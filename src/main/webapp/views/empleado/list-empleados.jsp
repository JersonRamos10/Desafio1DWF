<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 18/8/2025
  Time: 20:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Lista de Empleados</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<%@ include file="../navbar.jsp" %>

<div class="container mt-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <h2>Lista de Empleados</h2>
        <a href="${pageContext.request.contextPath}/empleado?action=showCreateForm" class="btn btn-primary">Crear Nuevo Empleado</a>
    </div>

    <c:choose>
        <c:when test="${not empty empleados}">
            <table class="table table-bordered table-hover text-center">
                <thead class="thead-dark">
                <tr>
                    <th>DUI</th>
                    <th>Nombre</th>
                    <th>Teléfono</th>
                    <th>Correo</th>
                    <th>Fecha de Nacimiento</th>
                    <th>Acciones</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="empleado" items="${empleados}">
                    <tr>
                        <td><c:out value="${empleado.numeroDui}"/></td>
                        <td><c:out value="${empleado.nombrePersona}"/></td>
                        <td><c:out value="${empleado.numeroTelefono}"/></td>
                        <td><c:out value="${empleado.correoInstitucional}"/></td>
                        <td><c:out value="${empleado.fechaNacimiento}"/></td>
                        <td class="d-flex justify-content-center">
                            <a href="${pageContext.request.contextPath}/empleado?action=edit&id=${empleado.idEmpleado}" class="btn btn-success mr-2">Editar</a>
                            <a href="${pageContext.request.contextPath}/empleado?action=delete&id=${empleado.numeroDui}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar a este empleado?');">Borrar</a>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </c:when>
        <c:otherwise>
            <div class="alert alert-info" role="alert">
                No hay empleados disponibles. ¡Crea uno nuevo!
            </div>
        </c:otherwise>
    </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
