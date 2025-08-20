package www.udbvirtual.edu.sv.desafio01dwf.controllers;

import www.udbvirtual.edu.sv.desafio01dwf.daos.EmpleadoDao;
import www.udbvirtual.edu.sv.desafio01dwf.models.Empleado;



import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

   // crea el servlet y lo mapea a la URL "/empleado".
    @WebServlet(name = "EmpleadoServlet", value = "/empleado")
    public class  EmpleadoServlet extends HttpServlet{
        private EmpleadoDao empleadoDao;

        @Override
        // El metodo init() se ejecuta una vez cuando el servlet se carga por primera vez.
        public void init(){
            // Se crea una instancia del DAO para poder usar sus metodos.
            empleadoDao = new EmpleadoDao();
        }

       // Centraliza el manejo de peticiones GET y POST.
        protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException{
            response.setContentType("text/html;charset=UTF-8");
            String action = request.getParameter("action");

            // Si no se especifica ninguna accion, la accion por defecto sera "listar"
            if (action == null) {
                action = "list";
            }

            try {
                // Un switch para ejecutar el metodo correspondiente a la accion solicitada.
                switch (action) {
                    case "list":
                        listEmpleados(request, response);
                        break;
                    case "showCreateForm":
                        showCreateForm(request, response);
                        break;
                    case "create":
                        createEmpleado(request, response);
                        break;
                    case "edit":
                        showEditForm(request, response);
                        break;
                    case "update":
                        updateEmpleado(request, response);
                        break;
                    case "delete":
                        deleteEmpleado(request, response);
                        break;
                    default:
                        listEmpleados(request, response);
                        break;
                }
            } catch (SQLException | ServletException ex) {
                // Maneja cualquier excepcion SQL o de Servlet que pueda ocurrir.
                throw new IOException(ex);
            }

        }

       // Obtiene la lista de empleados y la envia al JSP correspondiente.
        private void listEmpleados(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
            List<Empleado> empleados = empleadoDao.getAllEmpleados();
            request.setAttribute("empleados", empleados);
            request.getRequestDispatcher("views/empleado/list-empleados.jsp").forward(request, response);
        }

       // Muestra el formulario para crear un nuevo empleado.
        private void showCreateForm(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
            request.getRequestDispatcher("views/empleado/create-empleado.jsp").forward(request, response);
        }

       // Procesa los datos del formulario para crear un nuevo empleado.
        private void createEmpleado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
            String numeroDui = request.getParameter("numeroDui");
            // Valida si el DUI ya existe antes de intentar crearlo.
            if (empleadoDao.duiExists(numeroDui)) {
                request.setAttribute("error", "El DUI ingresado ya existe en la base de datos.");
                showCreateForm(request, response);
                return;
            }
            String nombrePersona = request.getParameter("nombrePersona");
            String usuario = request.getParameter("usuario");
            String numeroTelefono = request.getParameter("numeroTelefono");
            String correoInstitucional = request.getParameter("usuario") + "@empresa.com";  // Genera el correo institucional automaticamente a partir del usuario.
            Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));

            Empleado empleado = new Empleado();
            empleado.setNumeroDui(numeroDui);
            empleado.setNombrePersona(nombrePersona);
            empleado.setUsuario(usuario);
            empleado.setNumeroTelefono(numeroTelefono);
            empleado.setCorreoInstitucional(correoInstitucional);
            empleado.setFechaNacimiento(fechaNacimiento);

            // Llama al DAO para guardar el nuevo empleado en la base de datos.
            empleadoDao.addEmpleado(empleado);
            // Redirige al usuario a la lista de empleados para ver el nuevo registro.
            response.sendRedirect("empleado?action=list");
        }

       // Procesa los datos del formulario para actualizar un empleado.
        private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            Empleado existingEmpleado = empleadoDao.getEmpleadoById(id);
            request.setAttribute("empleado", existingEmpleado);
            request.getRequestDispatcher("views/empleado/create-empleado.jsp").forward(request, response);
        }

       // Procesa los datos del formulario para actualizar un empleado.
        private void updateEmpleado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
            int id = Integer.parseInt(request.getParameter("id"));
            String numeroDui = request.getParameter("numeroDui");
            String nombrePersona = request.getParameter("nombrePersona");
            String usuario = request.getParameter("usuario");
            String numeroTelefono = request.getParameter("numeroTelefono");
            String correoInstitucional = request.getParameter("correoInstitucional");
            Date fechaNacimiento = Date.valueOf(request.getParameter("fechaNacimiento"));

            Empleado empleado = new Empleado();
            empleado.setIdEmpleado(id);
            empleado.setNumeroDui(numeroDui);
            empleado.setNombrePersona(nombrePersona);
            empleado.setUsuario(usuario);
            empleado.setNumeroTelefono(numeroTelefono);
            empleado.setCorreoInstitucional(correoInstitucional);
            empleado.setFechaNacimiento(fechaNacimiento);

            // Llama al DAO para actualizar el empleado en la base de datos.
            empleadoDao.updateEmpleado(empleado);
            response.sendRedirect("empleado?action=list");
        }
       // Elimina un empleado de la base de datos.
        private void deleteEmpleado(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
            String id = request.getParameter("id");
            empleadoDao.deleteEmpleado(id);
            response.sendRedirect("empleado?action=list");
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


