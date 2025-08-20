package www.udbvirtual.edu.sv.desafio01dwf.models;

// Representa la entidad TipoContratacion.
public class TipoContratacion {

    private int idTipoContratacion;
    private String tipoContratacion;

    public int getIdTipoContratacion() {
        return idTipoContratacion;
    }

    public void setIdTipoContratacion(int idTipoContratacion) {
        if (idTipoContratacion <= 0) {
            throw new IllegalArgumentException("ID debe ser positivo");
        }
        this.idTipoContratacion = idTipoContratacion;
    }

    public String getTipoContratacion() {
        return tipoContratacion;
    }

    public void setTipoContratacion(String tipoContratacion) {
        this.tipoContratacion = tipoContratacion;
    }
}
