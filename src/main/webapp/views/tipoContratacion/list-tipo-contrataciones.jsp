<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 15/8/2025
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
  <title>Lista de Tipos de Contratación</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<!--incluye el navbar para usarlo en esta jsp-->
<%@ include file="../navbar.jsp" %>

<div class="container mt-5">
  <div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Lista de Tipos de Contratación</h2>
    <a href="${pageContext.request.contextPath}/tipoContratacion?action=showCreateForm" class="btn btn-primary">Crear Nuevo Tipo</a>
  </div>

  <c:choose>
    <c:when test="${not empty tiposContratacion}">
      <table class="table table-bordered table-hover text-center">
        <thead class="thead-dark">
        <tr>
          <th>ID</th>
          <th>Tipo de Contratación</th>
          <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tipo" items="${tiposContratacion}">
          <tr>
            <td><c:out value="${tipo.idTipoContratacion}"/></td>
            <td><c:out value="${tipo.tipoContratacion}"/></td>
            <td class="d-flex justify-content-center">
              <a href="${pageContext.request.contextPath}/tipoContratacion?action=edit&id=${tipo.idTipoContratacion}" class="btn btn-success mr-2">Editar</a>
              <a href="${pageContext.request.contextPath}/tipoContratacion?action=delete&id=${tipo.idTipoContratacion}" class="btn btn-danger" onclick="return confirm('¿Estás seguro de que deseas eliminar este tipo de contratación?');">Borrar</a>
            </td>
          </tr>
        </c:forEach>
        </tbody>
      </table>
    </c:when>
    <c:otherwise>
      <div class="alert alert-info" role="alert">
        No hay tipos de contratación disponibles.
      </div>
    </c:otherwise>
  </c:choose>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>