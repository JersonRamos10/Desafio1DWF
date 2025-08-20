package www.udbvirtual.edu.sv.desafio01dwf.models;

// Representa la entidad Cargo.

public class Puesto {
    private int idCargo;
    private String cargo;
    private String descripcionCargo;
    private boolean jefatura;

        //accesores get y set
    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getDescripcionCargo() {
        return descripcionCargo;
    }

    public void setDescripcionCargo(String descripcionCargo) {
        this.descripcionCargo = descripcionCargo;
    }

    public boolean isJefatura() {
        return jefatura;
    }

    public void setJefatura(boolean jefatura) {
        this.jefatura = jefatura;
    }
    //</editor-fold>
}