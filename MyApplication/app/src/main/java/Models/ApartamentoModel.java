package Models;

public class ApartamentoModel {

    private String ciudad;
    private String direccion;
    private String habitaciones;
    private String pais;
    private String reseña;
    private String valor;

    private ApartamentoModel(){

    }
    public ApartamentoModel(String ciudad, String direccion, String habitaciones, String pais, String reseña, String valor) {
        this.ciudad = ciudad;
        this.direccion = direccion;
        this.habitaciones = habitaciones;
        this.pais = pais;
        this.reseña = reseña;
        this.valor = valor;
    }



    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHabitaciones() {
        return habitaciones;
    }

    public void setHabitaciones(String habitaciones) {
        this.habitaciones = habitaciones;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getReseña() {
        return reseña;
    }

    public void setReseña(String reseña) {
        this.reseña = reseña;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }



}
