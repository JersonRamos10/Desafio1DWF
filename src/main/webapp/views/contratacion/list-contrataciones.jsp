<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 15/8/2025
  Time: 10:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Lista de Contrataciones</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>


<%@ include file="../navbar.jsp" %>

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Lista de Contrataciones</h2>
    <a href="${pageContext.request.contextPath}/contrataciones?action=showCreateForm" class="btn btn-primary">Crear Nueva Contratacion</a>
  </div>

  <c:choose>
    <c:when test="${not empty contrataciones}">
      <table class="table table-bordered table-hover text-center">
        <thead class="thead-dark">
        <tr>
          <th>ID</th>
          <th>Empleado</th>
          <th>Departamento</th>
          <th>Cargo</th>
          <th>Tipo</th>
          <th>Fecha</th>
          <th>Salario</th>
          <th>Estado</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contratacion" items="${contrataciones}" varStatus="loop">
          <tr>
            <td><c:out value="${contratacion.idContratacion}"/></td>
            <td><c:out value="${empleadosNombres[loop.index]}"/></td>
            <td><c:out value="${departamentosNombres[loop.index]}"/></td>
            <td><c:out value="${cargosNombres[loop.index]}"/></td>
            <td><c:out value="${tiposContratacionNombres[loop.index]}"/></td>
            <td><c:out value="${contratacion.fechaContratacion}"/></td>
            <td><c:out value="${contratacion.salario}"/></td>
            <td>
              <c:choose>
                <c:when test="${contratacion.estado}">
                  <span class="badge badge-success">Activo</span>
                </c:when>
                <c:otherwise>
                  <span class="badge badge-danger">Inactivo</span>
                </c:otherwise>
              </c:choose>
            </td>
            <td class="d-flex justify-content-center">
              <a href="${pageContext.request.contextPath}/contrataciones?action=edit&id=${contratacion.idContratacion}" class="btn btn-success mr-2">Editar</a>
              <a href="${pageContext.request.contextPath}/contrataciones?action=delete&id=${contratacion.idContratacion}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar esta contratación?');">Borrar</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <div class="alert alert-info" role="alert">
        No hay contrataciones disponibles.
      </div>
    </c:otherwise>
  </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>