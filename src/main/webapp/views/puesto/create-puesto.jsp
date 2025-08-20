<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 16/8/2025
  Time: 10:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
  <title>${not empty puesto ? 'Editar' : 'Agregar'} Cargo</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="container mt-5">
  <h2 class="mb-4">${not empty puesto ? 'Editar' : 'Agregar'} Cargo</h2>
  <form action="${pageContext.request.contextPath}/puesto?action=${not empty puesto ? 'update' : 'create'}" method="post">
    <c:if test="${not empty puesto}">
      <input type="hidden" name="id" value="${puesto.idCargo}" />
    </c:if>
    <div class="form-group">
      <label for="puestoNombre">Nombre del Cargo</label>
      <input type="text" class="form-control" id="puestoNombre" name="puestoNombre" value="${puesto.cargo}" required>
    </div>
    <div class="form-group">
      <label for="puestoDescripcion">Descripción</label>
      <textarea class="form-control" id="puestoDescripcion" name="puestoDescripcion" required>${puesto.descripcionCargo}</textarea>
    </div>
    <div class="form-group">
      <label for="puestoEsJefatura">Es Jefatura</label>
      <select class="form-control" id="puestoEsJefatura" name="puestoEsJefatura">
        <option value="true" ${puesto.jefatura ? 'selected' : ''}>Sí</option>
        <option value="false" ${!puesto.jefatura ? 'selected' : ''}>No</option>
      </select>
    </div>
    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="${pageContext.request.contextPath}/puesto?action=list" class="btn btn-secondary">Cancelar</a>
  </form>
</div>
</body>
</html>