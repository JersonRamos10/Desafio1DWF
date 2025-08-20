<%--
  Created by IntelliJ IDEA.
  User: jerson_ramos
  Date: 18/8/2025
  Time: 20:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
  <title>${not empty empleado ? 'Editar' : 'Agregar'} Empleado</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>

<div class="container mt-5">
  <h2 class="mb-4">${not empty empleado ? 'Editar' : 'Agregar'} Empleado</h2>

  <c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
      <c:out value="${error}"/>
    </div>
  </c:if>

  <form action="${pageContext.request.contextPath}/empleado?action=${not empty empleado ? 'update' : 'create'}" method="post">
    <c:if test="${not empty empleado}">
      <input type="hidden" name="id" value="${empleado.idEmpleado}" />
      <input type="hidden" name="correoInstitucional" value="${empleado.correoInstitucional}" />
    </c:if>

    <div class="form-group">
      <label for="numeroDui">Numero DUI</label>

      <input type="text" class="form-control" id="numeroDui" name="numeroDui" value="<c:out value='${empleado.numeroDui}'/>" required maxlength="10" pattern="\d{8}-\d{1}" title="Formato: 12345678-9">
      <small class="form-text text-muted">Formato: 8 digitos, un guion, 1 digito (ej. 12345678-9).</small>
    </div>

    <div class="form-group">
      <label for="nombrePersona">Nombre y Apellido</label>

      <input type="text" class="form-control" id="nombrePersona" name="nombrePersona" value="<c:out value='${empleado.nombrePersona}'/>" required maxlength="50">
    </div>

    <div class="form-group">
      <label for="usuario">Usuario</label>
      <input type="text" class="form-control" id="usuario" name="usuario" value="<c:out value='${empleado.usuario}'/>" required maxlength="20">
    </div>

    <div class="form-group">
      <label for="numeroTelefono">Numero de Telefono</label>
      <input type="text" class="form-control" id="numeroTelefono" name="numeroTelefono" value="<c:out value='${empleado.numeroTelefono}'/>" required maxlength="9" pattern="\d{4}-\d{4}" title="Formato: 1234-5678">
      <small class="form-text text-muted">Formato: 4 digitos, un guion, 4 digitos (ej. 7777-7777).</small>
    </div>

    <div class="form-group">
      <label for="fechaNacimiento">Fecha de Nacimiento</label>
      <input type="date" class="form-control" id="fechaNacimiento" name="fechaNacimiento" value="${empleado.fechaNacimiento}" required>
    </div>

    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="${pageContext.request.contextPath}/empleado?action=list" class="btn btn-secondary">Cancelar</a>
  </form>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>