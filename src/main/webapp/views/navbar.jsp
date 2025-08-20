<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <a class="navbar-brand" href="${pageContext.request.contextPath}/empleado?action=list">UDB </a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarNavDropdown">
        <ul class="navbar-nav">

            <li class="nav-item mx-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/empleado?action=list">Empleados</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/departamento?action=list">Departamentos</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/puesto?action=list">Cargos</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/tipoContratacion?action=list">Tipos de Contrato</a>
            </li>
            <li class="nav-item mx-2">
                <a class="nav-link" href="${pageContext.request.contextPath}/contrataciones?action=list">Contrataciones</a>
            </li>
        </ul>
    </div>
</nav>