package www.udbvirtual.edu.sv.desafio01dwf.controllers;

import www.udbvirtual.edu.sv.desafio01dwf.daos.DepartamentoDao;
import www.udbvirtual.edu.sv.desafio01dwf.models.Departamento;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

// crea el servlet y lo mapea a la URL "/departamento".
@WebServlet(name = "DepartamentoServlet", value = "/departamento")
public class DepartamentoServlet extends HttpServlet {
    private DepartamentoDao departamentoDao;

    // Se ejecuta una vez para inicializar el DAO.
    @Override
    public void init(){
        departamentoDao = new DepartamentoDao();
    }

    // Centraliza el manejo de todas las peticiones para este servlet.
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        try {

            // Ejecuta una accion diferente dependiendo del parametro "action".

            switch (action) {
                case "list":
                    listDepartamentos(request, response);
                    break;
                case "showCreateForm":
                    showCreateForm(request, response);
                    break;
                case "create":
                    createDepartamento(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateDepartamento(request, response);
                    break;
                case "delete":
                    deleteDepartamento(request, response);
                    break;
                default:
                    listDepartamentos(request, response);
                    break;
            }
        } catch (SQLException | ServletException ex) {
            throw new IOException(ex);
        }
    }

    // Obtiene la lista de departamentos y la envia al JSP.
    private void listDepartamentos(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        List<Departamento> departamentos = departamentoDao.getAllDepartamentos();
        request.setAttribute("departamentos", departamentos);
        request.getRequestDispatcher("views/departamento/list-departamentos.jsp").forward(request, response);
    }

    // Muestra el formulario para crear un nuevo departamento.
    private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("views/departamento/create-departamento.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para crear un nuevo departamento.
    private void createDepartamento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String nombreDepartamento = request.getParameter("nombreDepartamento");
        String descripcionDepartamento = request.getParameter("descripcionDepartamento");

        Departamento departamento = new Departamento();
        departamento.setNombreDepartamento(nombreDepartamento);
        departamento.setDescripcionDepartamento(descripcionDepartamento);

        departamentoDao.addDepartamento(departamento);
        response.sendRedirect("departamento?action=list");
    }

    // Muestra el formulario de edicion con los datos de un departamento.
    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Departamento existingDepartamento = departamentoDao.getDepartamentoById(id);
        request.setAttribute("departamento", existingDepartamento);
        request.getRequestDispatcher("views/departamento/create-departamento.jsp").forward(request, response);
    }

    // Procesa los datos del formulario para actualizar un departamento.
    private void updateDepartamento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nombreDepartamento = request.getParameter("nombreDepartamento");
        String descripcionDepartamento = request.getParameter("descripcionDepartamento");

        Departamento departamento = new Departamento();
        departamento.setIdDepartamento(id);
        departamento.setNombreDepartamento(nombreDepartamento);
        departamento.setDescripcionDepartamento(descripcionDepartamento);

        departamentoDao.updateDepartamento(departamento);
        response.sendRedirect("departamento?action=list");
    }

    // Elimina un departamento.
    private void deleteDepartamento(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        departamentoDao.deleteDepartamento(id);
        response.sendRedirect("departamento?action=list");
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