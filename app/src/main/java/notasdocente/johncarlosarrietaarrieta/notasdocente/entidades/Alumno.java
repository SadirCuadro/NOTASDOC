package notasdocente.johncarlosarrietaarrieta.notasdocente.entidades;

public class Alumno {

    private String codigo;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private Programas carrera;

    public Alumno() {
    }

    public Alumno(String codigo, String nombre, String apellido) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Programas getCarrera() {
        return carrera;
    }

    public void setCarrera(Programas carrera) {
        this.carrera = carrera;
    }
}
