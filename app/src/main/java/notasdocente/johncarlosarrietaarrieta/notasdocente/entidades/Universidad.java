package notasdocente.johncarlosarrietaarrieta.notasdocente.entidades;

public class Universidad {

    private String id;
    private String nombre;
    private String www;

    public Universidad() {
    }

    public Universidad(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWww() {
        return www;
    }

    public void setWww(String www) {
        this.www = www;
    }
}
