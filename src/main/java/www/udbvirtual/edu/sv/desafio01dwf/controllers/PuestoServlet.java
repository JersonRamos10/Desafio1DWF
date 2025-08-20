package www.udbvirtual.edu.sv.desafio01dwf.controllers;

import www.udbvirtual.edu.sv.desafio01dwf.daos.PuestoDao;
import www.udbvirtual.edu.sv.desafio01dwf.models.Puesto;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// crea el servlet y lo mapea a la URL "/puesto".
@WebServlet(name = "PuestoServlet", value = "/puesto")
public class PuestoServlet extends HttpServlet {
    private PuestoDao puestoDao;

    // Se ejecuta una vez para inicializar el DAO de Puesto.
    @Override
    public void init() {
        puestoDao = new PuestoDao();
    }

    // Redirige las peticiones GET al metodo central.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Redirige las peticiones POST al metodo central.
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    // Centraliza el manejo de todas las peticiones para este servlet.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Obtiene la accon de la URL, si no existe, la accion por defecto es "listar".
        String action = request.getParameter("action") != null ? request.getParameter("action") : "list";

        try {
            // Ejecuta el metodo correspondiente a la accion.
            switch (action) {
                case "list":
                    listPuestos(request, response);
                    break;
                case "showCreateForm":
                    showCreateForm(request, response);
                    break;
                case "create":
                    createPuesto(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updatePuesto(request, response);
                    break;
                case "delete":
                    deletePuesto(request, response);
                    break;
                default:
                    listPuestos(request, response);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Maneja errores de base de datos.
            throw new ServletException("Error de base de datos en PuestoServlet", e);
        }
    }

    // Obtiene la lista de puestos y la envía al JSP.
    private void listPuestos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Puesto> listaPuestos = puestoDao.getAllPuestos();
        request.setAttribute("puestos", listaPuestos);
        request.getRequestDispatcher("/views/puesto/list-puestos.jsp").forward(request, response);
    }

    // Muestra el formulario para crear un nuevo puesto.
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/views/puesto/create-puesto.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para crear un nuevo puesto.
    private void createPuesto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombre = request.getParameter("puestoNombre");
        String descripcion = request.getParameter("puestoDescripcion");
        boolean esJefatura = Boolean.parseBoolean(request.getParameter("puestoEsJefatura"));

        Puesto nuevoPuesto = new Puesto();
        nuevoPuesto.setCargo(nombre);
        nuevoPuesto.setDescripcionCargo(descripcion);
        nuevoPuesto.setJefatura(esJefatura);

        puestoDao.addPuesto(nuevoPuesto);
        response.sendRedirect("puesto?action=list");
    }


    // Muestra el formulario con los datos de un puesto.
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Puesto puestoExistente = puestoDao.getPuestoById(id);
        request.setAttribute("puesto", puestoExistente);
        request.getRequestDispatcher("/views/puesto/create-puesto.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para actualizar un puesto.
    private void updatePuesto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombre = request.getParameter("puestoNombre");
        String descripcion = request.getParameter("puestoDescripcion");
        boolean esJefatura = Boolean.parseBoolean(request.getParameter("puestoEsJefatura"));

        Puesto puestoActualizado = new Puesto();
        puestoActualizado.setIdCargo(id);
        puestoActualizado.setCargo(nombre);
        puestoActualizado.setDescripcionCargo(descripcion);
        puestoActualizado.setJefatura(esJefatura);

        puestoDao.updatePuesto(puestoActualizado);
        response.sendRedirect("puesto?action=list");
    }

    // Elimina un puesto.
    private void deletePuesto(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        puestoDao.deletePuesto(id);
        response.sendRedirect("puesto?action=list");
    }
}