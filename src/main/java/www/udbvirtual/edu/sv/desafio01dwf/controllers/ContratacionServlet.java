package www.udbvirtual.edu.sv.desafio01dwf.controllers;

import www.udbvirtual.edu.sv.desafio01dwf.daos.*;
import www.udbvirtual.edu.sv.desafio01dwf.models.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//crea el servlet y lo mapea a la URL "/departamento".
@WebServlet(name = "ContratacionServlet", value = "/contrataciones")
public class ContratacionServlet extends HttpServlet {
    private ContratacionDao contratacionDao;
    private EmpleadoDao empleadoDao;
    private DepartamentoDao departamentoDao;
    private PuestoDao puestoDao;
    private TipoContratacionDao tipoContratacionDao;

    @Override
    // El metodo init se ejecuta una vez cuando el servlet se carga por primera vez
    public void init() {
        // Se crean las instancias de los DAO para poder usar sus metodos

        contratacionDao = new ContratacionDao();
        empleadoDao = new EmpleadoDao();
        departamentoDao = new DepartamentoDao();
        puestoDao = new PuestoDao();
        tipoContratacionDao = new TipoContratacionDao();
    }

    // Metodo principal que procesa todas las peticiones GET y POST
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");  // Se establece el tipo de contenido de la respuesta
        String action = request.getParameter("action"); // Se obtiene el parametro 'action' para determinar que operacion realizar
        if (action == null) {
            // Si no se especifica ninguna accion, la accion por defecto sera "list"
            action = "list";
        }

        try {
            // Un switch para ejecutar el metodo correspondiente a la accion solicitada
            switch (action) {
                case "list":
                    listContrataciones(request, response);
                    break;
                case "showCreateForm":
                    showCreateForm(request, response);
                    break;
                case "create":
                    createContratacion(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateContratacion(request, response);
                    break;
                case "delete":
                    deleteContratacion(request, response);
                    break;
                default:
                    listContrataciones(request, response); // Si la accion no es reconocida, se muestra la lista por defecto
                    break;
            }
        } catch (SQLException | ServletException ex) {
            ex.printStackTrace();
            // Aca se maneja cualquier excepcion SQL o de Servlet que pueda ocurrir
            throw new IOException(ex);
        }
    }

    // Obtiene y muestra la lista de todas las contrataciones
    private void listContrataciones(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Contratacion> contrataciones = contratacionDao.getAllContrataciones(); // Se obtiene la lista completa de contrataciones desde el DAO
        // Se crean listas para almacenar los nombres de las entidades relacionadas
        List<String> departamentosNombres = new ArrayList<>();
        List<String> empleadosNombres = new ArrayList<>();
        List<String> puestosNombres = new ArrayList<>();
        List<String> tiposContratacionNombres = new ArrayList<>();

        // Se itera sobre cada contratacion para obtener los nombres asociados a los IDs
        for (Contratacion contratacion : contrataciones) {
            Departamento depto = departamentoDao.getDepartamentoById(contratacion.getIdDepartamento());
            departamentosNombres.add(depto != null ? depto.getNombreDepartamento() : "N/A");

            Empleado empleado = empleadoDao.getEmpleadoById(contratacion.getIdEmpleado());
            empleadosNombres.add(empleado != null ? empleado.getNombrePersona() : "N/A");

            Puesto puesto = puestoDao.getPuestoById(contratacion.getIdCargo());
            puestosNombres.add(puesto != null ? puesto.getCargo() : "N/A");

            TipoContratacion tipo = tipoContratacionDao.getTipoContratacionById(contratacion.getIdTipoContratacion());
            tiposContratacionNombres.add(tipo != null ? tipo.getTipoContratacion() : "N/A");
        }

        // Se agregan las listas al objeto request para ser usadas en el JSP

        request.setAttribute("contrataciones", contrataciones);
        request.setAttribute("departamentosNombres", departamentosNombres);
        request.setAttribute("empleadosNombres", empleadosNombres);
        request.setAttribute("puestosNombres", puestosNombres);
        request.setAttribute("tiposContratacionNombres", tiposContratacionNombres);

        // Se reenvia la peticion al JSP para que muestre los datos
        request.getRequestDispatcher("/views/contratacion/list-contrataciones.jsp").forward(request, response);
    }

    //con este metodo se  procesa los datos del formulario para crear una nueva contratacion
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Empleado> empleados = empleadoDao.getAllEmpleados();
        List<Departamento> departamentos = departamentoDao.getAllDepartamentos();
        List<Puesto> puestos = puestoDao.getAllPuestos();
        List<TipoContratacion> tiposContratacion = tipoContratacionDao.getAllTiposContratacion();

        request.setAttribute("empleados", empleados);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("puestos", puestos);
        request.setAttribute("tiposContratacion", tiposContratacion);

        request.getRequestDispatcher("/views/contratacion/create-contratacion.jsp").forward(request, response);
    }

    private void createContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        try {
            String idEmpleadoStr = request.getParameter("idEmpleado");
            // Se valida que se haya seleccionado un empleado valido
            if (idEmpleadoStr == null || idEmpleadoStr.trim().isEmpty() || idEmpleadoStr.equals("0")) {
                // Si el ID es nulo, vacio o 0, lanzamos un error .
                throw new IllegalArgumentException("Error: Debe seleccionar un empleado valido. Se recibió un ID invalido.");
            }

            int idEmpleado = Integer.parseInt(idEmpleadoStr);
            int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
            int idCargo = Integer.parseInt(request.getParameter("idCargo"));
            int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
            Date fechaContratacion = Date.valueOf(request.getParameter("fechaContratacion"));
            double salario = Double.parseDouble(request.getParameter("salario"));
            boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

            Contratacion contratacion = new Contratacion();
            contratacion.setIdDepartamento(idDepartamento);
            contratacion.setIdEmpleado(idEmpleado);
            contratacion.setIdCargo(idCargo);
            contratacion.setIdTipoContratacion(idTipoContratacion);
            contratacion.setFechaContratacion(fechaContratacion);
            contratacion.setSalario(salario);
            contratacion.setEstado(estado);

            contratacionDao.addContratacion(contratacion);
            response.sendRedirect("contrataciones?action=list");

        } catch (IllegalArgumentException e) {
            // Si la validacion falla, enviamos el mensaje de error de vuelta al formulario.
            request.setAttribute("error", e.getMessage());
            showCreateForm(request, response);
        }
    }

    // Muestra el formulario para editar una contratacion existente
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Contratacion contratacion = contratacionDao.getContratacionById(id);

        // Se obtienen las listas necesarias para los menus desplegables
        List<Empleado> empleados = empleadoDao.getAllEmpleados();
        List<Departamento> departamentos = departamentoDao.getAllDepartamentos();
        List<Puesto> puestos = puestoDao.getAllPuestos();
        List<TipoContratacion> tiposContratacion = tipoContratacionDao.getAllTiposContratacion();

        // Se agregan los datos al request para poblar el formulario
        request.setAttribute("contratacion", contratacion);
        request.setAttribute("empleados", empleados);
        request.setAttribute("departamentos", departamentos);
        request.setAttribute("puestos", puestos);
        request.setAttribute("tiposContratacion", tiposContratacion);

        // Se reenvia la peticion al mismo JSP de creacion, que tambien sirve para editar
        request.getRequestDispatcher("/views/contratacion/create-contratacion.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para actualizar una contratacion
    private void updateContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        // Se obtienen todos los parametros del formulario
        int id = Integer.parseInt(request.getParameter("id"));
        int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
        int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
        int idCargo = Integer.parseInt(request.getParameter("idCargo"));
        int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
        Date fechaContratacion = Date.valueOf(request.getParameter("fechaContratacion"));
        double salario = Double.parseDouble(request.getParameter("salario"));
        boolean estado = Boolean.parseBoolean(request.getParameter("estado"));


        Contratacion contratacion = new Contratacion();
        contratacion.setIdContratacion(id);
        contratacion.setIdDepartamento(idDepartamento);
        contratacion.setIdEmpleado(idEmpleado);
        contratacion.setIdCargo(idCargo);
        contratacion.setIdTipoContratacion(idTipoContratacion);
        contratacion.setFechaContratacion(fechaContratacion);
        contratacion.setSalario(salario);
        contratacion.setEstado(estado);

        contratacionDao.updateContratacion(contratacion);
        response.sendRedirect("contrataciones?action=list"); // Se redirige al usuario a la lista de contrataciones
    }

    // Elimina una contratacion
    private void deleteContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        contratacionDao.deleteContratacion(id);
        response.sendRedirect("contrataciones?action=list");
    }

    // Redirige las peticiones GET al metodo central processRequest.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }

    // Redirige las peticiones POST al metodo central processRequest.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        processRequest(request, response);
    }
}