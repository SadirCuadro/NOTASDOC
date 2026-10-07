package notasdocente.johncarlosarrietaarrieta.notasdocente.dao;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

import notasdocente.johncarlosarrietaarrieta.notasdocente.entidades.Universidad;

public class DaoUniversidad {

    private Activity actividad;
    private Universidad institucion;
    private ConexionBasedatos bd;

    public DaoUniversidad(Context actividad, String nombre, int version) {
        bd = new ConexionBasedatos(actividad, nombre, null, version);
        bd.conectar(ConexionBasedatos.MODO_ESCRITURA);
    }

    public void agregarUniversidad(Universidad institucion) throws Exception {
        if (institucion.getNombre() == null || institucion.getNombre().trim().isEmpty()) {
            throw new Exception("El nombre de la Universidad no puede estar vacío.");
        }
        ContentValues args = new ContentValues();
        args.put("nombre", institucion.getNombre());
        if (institucion.getWww() == null || institucion.getWww().trim().isEmpty()) {
            args.putNull("www");
        } else {
            args.put("www", institucion.getWww());
        }
        bd.insertar("Universidades", args);
    }

    public Universidad getUniversidad(Cursor resultado) {
        Universidad u = new Universidad();
        u.setId(resultado.getString(0));
        u.setNombre(resultado.getString(1));
        u.setWww(resultado.getString(2));
        return u;
    }

    public Universidad consultarUnaUniversidad(String id) throws Exception {
        Cursor resultado = bd.consultar(
                "SELECT id, nombre, www FROM Universidades WHERE id=?",
                new String[]{id}
        );
        if (resultado.moveToFirst()) {
            Universidad u = getUniversidad(resultado);
            resultado.close();
            return u;
        } else {
            resultado.close();
            throw new Exception("No existe una Universidad con ID: " + id);
        }
    }

    public List<Universidad> listarTodasUniversidades() throws Exception {
        Cursor resultado = bd.consultar(
                "SELECT id, nombre, www FROM Universidades",
                null
        );
        List<Universidad> lista = new ArrayList<>();
        while (resultado.moveToNext()) {
            lista.add(getUniversidad(resultado));
        }
        resultado.close();
        if (lista.isEmpty()) {
            throw new Exception("No hay Universidades registradas.");
        }
        return lista;
    }

    public void borrarUniversidad(String id) {
        bd.eliminar("Universidades", "id=?", new String[]{id});
    }

    public void editarUniversidad(Universidad institucion) {
        ContentValues args = new ContentValues();
        args.put("nombre", institucion.getNombre());
        if (institucion.getWww() == null || institucion.getWww().trim().isEmpty()) {
            args.putNull("www");
        } else {
            args.put("www", institucion.getWww());
        }
        bd.actualizar("Universidades", args, "id=?", new String[]{institucion.getId()});
    }

    public int proximoId() {
        Cursor resultado = bd.consultar(
                "SELECT MAX(id) FROM Universidades",
                null
        );
        if (resultado.moveToFirst() && !resultado.isNull(0)) {
            int maximo = resultado.getInt(0);
            resultado.close();
            return maximo + 1;
        } else {
            resultado.close();
            return 1;
        }
    }

    public ConexionBasedatos getBd() {
        return bd;
    }
}
