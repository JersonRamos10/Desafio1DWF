package www.udbvirtual.edu.sv.desafio01dwf.controllers;

import www.udbvirtual.edu.sv.desafio01dwf.daos.TipoContratacionDao;
import www.udbvirtual.edu.sv.desafio01dwf.models.TipoContratacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// crea el servlet y lo mapea a la URL "/tipoContratacion".
@WebServlet(name = "TipoContratacionServlet", value = "/tipoContratacion")
public class TipoContratacionServlet extends HttpServlet {
    private TipoContratacionDao tipoContratacionDao;

    // Se ejecuta una vez para inicializar el DAO.
    @Override
    public void init() {
        tipoContratacionDao = new TipoContratacionDao();
    }

    // Centraliza el manejo de todas las peticiones para este servlet.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {
            // Ejecuta una accion diferente dependiendo del parametro.
            switch (action) {
                case "list":
                    listTiposContratacion(request, response);
                    break;
                case "showCreateForm":
                    showCreateForm(request, response);
                    break;
                case "create":
                    createTipoContratacion(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateTipoContratacion(request, response);
                    break;
                case "delete":
                    deleteTipoContratacion(request, response);
                    break;
                default:
                    listTiposContratacion(request, response);
                    break;
            }
        } catch (SQLException | ServletException ex) {
            throw new IOException(ex);
        }
    }

    // Obtiene la lista de tipos de contratacion y la envía al JSP.
    private void listTiposContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<TipoContratacion> tipos = tipoContratacionDao.getAllTiposContratacion();
        request.setAttribute("tiposContratacion", tipos);
        request.getRequestDispatcher("views/tipoContratacion/list-tipo-contrataciones.jsp").forward(request, response);
    }

    // Muestra el formulario para crear un nuevo tipo.
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("views/tipoContratacion/create-tipo-contratacion.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para crear un nuevo tipo.
    private void createTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String tipo = request.getParameter("tipoContratacion");
        TipoContratacion nuevoTipo = new TipoContratacion();
        nuevoTipo.setTipoContratacion(tipo);
        tipoContratacionDao.addTipoContratacion(nuevoTipo);
        response.sendRedirect("tipoContratacion?action=list");
    }

    // Muestra el formulario de edicion con los datos de un tipo.
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        TipoContratacion tipoExistente = tipoContratacionDao.getTipoContratacionById(id);
        request.setAttribute("tipoContratacion", tipoExistente);
        request.getRequestDispatcher("views/tipoContratacion/create-tipo-contratacion.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para actualizar un tipo.
    private void updateTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String tipo = request.getParameter("tipoContratacion");
        TipoContratacion tipoActualizado = new TipoContratacion();
        tipoActualizado.setIdTipoContratacion(id);
        tipoActualizado.setTipoContratacion(tipo);
        tipoContratacionDao.updateTipoContratacion(tipoActualizado);
        response.sendRedirect("tipoContratacion?action=list");
    }

    // Elimina un tipo de contratacion.
    private void deleteTipoContratacion(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        tipoContratacionDao.deleteTipoContratacion(id);
        response.sendRedirect("tipoContratacion?action=list");
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