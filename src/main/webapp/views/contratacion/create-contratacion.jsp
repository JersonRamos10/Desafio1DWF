<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>${not empty contratacion ? 'Editar' : 'Agregar'} Contratación</title>
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<%@ include file="../navbar.jsp" %>

<div class="container mt-5">
  <h2 class="mb-4">${not empty contratacion ? 'Editar' : 'Agregar'} Contratacion</h2>

  <c:if test="${not empty error}">
    <div class="alert alert-danger" role="alert">
      <strong><c:out value="${error}"/></strong>
    </div>
  </c:if>


  <form method="POST" action="${pageContext.request.contextPath}/contrataciones?action=${not empty contratacion ? 'update' : 'create'}">

    <c:if test="${not empty contratacion}">
      <input type="hidden" name="id" value="${contratacion.idContratacion}"/>
    </c:if>

    <div class="form-group">
      <label for="idEmpleado">Empleado *</label>
      <select id="idEmpleado" name="idEmpleado" class="form-control" required>
        <option value="">-- Seleccione un Empleado --</option>
        <c:forEach var="emp" items="${empleados}">
          <option value="${emp.idEmpleado}" ${not empty contratacion && contratacion.idEmpleado == emp.idEmpleado ? 'selected' : ''}>

              ${emp.nombrePersona}
          </option>
        </c:forEach>
      </select>
      <small class="form-text text-muted">Debe seleccionar un empleado valido</small>
    </div>


    <div class="form-group">
      <label for="idDepartamento">Departamento *</label>
      <select id="idDepartamento" name="idDepartamento" class="form-control" required>
        <option value="">-- Seleccione un Departamento --</option>
        <c:forEach var="depto" items="${departamentos}">
          <option value="${depto.idDepartamento}" ${not empty contratacion && contratacion.idDepartamento == depto.idDepartamento ? 'selected' : ''}>
              ${depto.nombreDepartamento}
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="idCargo">Cargo *</label>
      <select id="idCargo" name="idCargo" class="form-control" required>
        <option value="">-- Seleccione un Cargo --</option>
        <c:forEach var="puesto" items="${puestos}">
          <option value="${puesto.idCargo}" ${not empty contratacion && contratacion.idCargo == puesto.idCargo ? 'selected' : ''}>
              ${puesto.cargo}
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="idTipoContratacion">Tipo de Contratacion *</label>
      <select id="idTipoContratacion" name="idTipoContratacion" class="form-control" required>
        <option value="">-- Seleccione un Tipo --</option>
        <c:forEach var="tipo" items="${tiposContratacion}">
          <option value="${tipo.idTipoContratacion}" ${not empty contratacion && contratacion.idTipoContratacion == tipo.idTipoContratacion ? 'selected' : ''}>
              ${tipo.tipoContratacion}
          </option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="fechaContratacion">Fecha de Contratación *</label>
      <input type="date" class="form-control" id="fechaContratacion" name="fechaContratacion" value="${contratacion.fechaContratacion}" required>
    </div>

    <div class="form-group">
      <label for="salario">Salario *</label>
      <input type="number" step="0.01" min="0" class="form-control" id="salario" name="salario" value="${contratacion.salario}" required>
    </div>

    <div class="form-group">
      <label for="estado">Estado *</label>
      <select class="form-control" id="estado" name="estado" required>
        <option value="true" ${empty contratacion || contratacion.estado ? 'selected' : ''}>Activo</option>
        <option value="false" ${not empty contratacion && !contratacion.estado ? 'selected' : ''}>Inactivo</option>
      </select>
    </div>

    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="${pageContext.request.contextPath}/contrataciones?action=list" class="btn btn-secondary">Cancelar</a>
  </form>
</div>


<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>