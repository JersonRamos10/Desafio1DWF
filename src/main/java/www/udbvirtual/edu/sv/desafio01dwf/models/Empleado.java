package www.udbvirtual.edu.sv.desafio01dwf.models;

//importaciones para usar los modelos
import java.util.Date;
import java.util.Calendar;

// Representa la entidad Empleado, mapeando la tabla de la base de datos.
public class Empleado {
    //atributos
    private int idEmpleado;
    private String numeroDui;
    private String nombrePersona;
    private String usuario;
    private String numeroTelefono;
    private String correoInstitucional;
    private Date fechaNacimiento;

    //accesores get y set
    public int getIdEmpleado(){

        return idEmpleado;
    }

    public void setIdEmpleado(int idEmpleado) {
        if (idEmpleado <= 0) {
            throw new IllegalArgumentException("EL ID Debe ser un valor positivo");
        }
        this.idEmpleado = idEmpleado;
    }

    public String getNumeroDui() {
        return numeroDui;
    }

    // metodo para validar que el DUI tenga el formato correcto (8 digitos, guion, 1 digito).
    public void setNumeroDui(String numeroDui) {
        if (!numeroDui.matches("^[0-9]{8}-[0-9]$")) {
            throw new IllegalArgumentException("Numero DUI debe tener 8 digitos, un guion y otro digito");
        }
        this.numeroDui = numeroDui;
    }
    public String getNombrePersona() {
        return nombrePersona;
    }

    public void setNombrePersona(String nombrePersona) {
        this.nombrePersona = nombrePersona;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getNumeroTelefono() {
        return numeroTelefono;
    }

    // Metodo para validar que el telefono tenga el formato correcto (4 digitos, guion, 4 digitos).
    public void setNumeroTelefono(String numeroTelefono) {
        if (!numeroTelefono.matches("^[0-9]{4}-[0-9]{4}$")) {
            throw new IllegalArgumentException("Numero de telefono debe tener 8 digitos");
        }
        this.numeroTelefono = numeroTelefono;
    }
    public String getCorreoInstitucional() {
        return correoInstitucional;
    }

    public void setCorreoInstitucional(String correoInstitucional) {
        if (!correoInstitucional.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("FOrmato de correo invalido");
        }
        this.correoInstitucional = correoInstitucional;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        if (esMayorDeEdad(fechaNacimiento)) {
            this.fechaNacimiento = fechaNacimiento;
        } else {
            throw new IllegalArgumentException("La persona debe tener al menos 18 años.");
        }
    }

    //metodo para validar si es mayor de edad
    private boolean esMayorDeEdad(Date fechaNacimiento) {
        Calendar fchNacimiento = Calendar.getInstance();// fch (es la abreviacion de fecha)

        fchNacimiento.setTime(fechaNacimiento);

        Calendar fchActual = Calendar.getInstance(); // fch (es la abreviacion de fecha)

        int edad = fchActual.get(Calendar.YEAR) - fchNacimiento.get(Calendar.YEAR);
        if (fchActual.get(Calendar.MONTH) < fchNacimiento.get(Calendar.MONTH) ||
                (fchActual.get(Calendar.MONTH) == fchNacimiento.get(Calendar.MONTH) && fchActual.get(Calendar.DAY_OF_MONTH) < fchNacimiento.get(Calendar.DAY_OF_MONTH))) {
            edad--;
        }
        return edad >= 18;
    }




}
