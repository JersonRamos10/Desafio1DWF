package www.udbvirtual.edu.sv.desafio01dwf.models;

// Representa la entidad Departamento.
public class Departamento {
    private int idDepartamento;
    private String nombreDepartamento;
    private String descripcionDepartamento;

    //accesores get y set
    public int getIdDepartamento() {
        return idDepartamento;
    }

    public void setIdDepartamento(int idDepartamento) {
        if (idDepartamento <= 0) {
            throw new IllegalArgumentException("ID debe ser positivo");
        }
        this.idDepartamento = idDepartamento;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }

    public String getDescripcionDepartamento() {
        return descripcionDepartamento;
    }

    public void setDescripcionDepartamento(String descripcionDepartamento) {
        this.descripcionDepartamento = descripcionDepartamento;
    }
}