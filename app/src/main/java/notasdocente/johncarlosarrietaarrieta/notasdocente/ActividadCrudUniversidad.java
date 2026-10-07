package notasdocente.johncarlosarrietaarrieta.notasdocente;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import notasdocente.johncarlosarrietaarrieta.notasdocente.dao.DaoUniversidad;
import notasdocente.johncarlosarrietaarrieta.notasdocente.entidades.Universidad;

public class ActividadCrudUniversidad extends Activity {

    private EditText campoId;
    private EditText campoNombre;
    private EditText campoWww;
    private Button botonGuardar;
    private Button botonBuscar;
    private DaoUniversidad dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_crud_universidad);

        campoId = findViewById(R.id.campoId);
        campoNombre = findViewById(R.id.campoNombre);
        campoWww = findViewById(R.id.campoWww);
        botonGuardar = findViewById(R.id.botonGuardar);
        botonBuscar = findViewById(R.id.botonBuscar);

        dao = new DaoUniversidad(this, "notasdocentev1", 1);
        campoId.setText(String.valueOf(dao.proximoId()));
    }

    public void agregarUniversidad(View campo) {
        try {
            Universidad u = new Universidad();
            u.setNombre(campoNombre.getText().toString());
            u.setWww(campoWww.getText().toString());
            dao.agregarUniversidad(u);
            Toast.makeText(this, "Mensaje: Uni Agregada", Toast.LENGTH_SHORT).show();
            campoId.setText(String.valueOf(dao.proximoId()));
            limpiarCampos(campo);
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    public void limpiarCampos(View componente) {
        campoNombre.setText("");
        campoWww.setText("");
        campoId.setText(String.valueOf(dao.proximoId()));
    }

    public void mostrarCuadroDialogoId(final View componente) {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View vistaDialog = inflater.inflate(R.layout.layout_dialog_buscar_id, null);
        final EditText campoBuscarId = vistaDialog.findViewById(R.id.campoBuscarId);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Buscar Universidad");
        builder.setView(vistaDialog);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String idIngresado = campoBuscarId.getText().toString().trim();
                campoId.setText(idIngresado);
                buscarUniversidad(componente);
            }
        });
        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                limpiarCampos(componente);
            }
        });
        builder.create().show();
    }

    public void buscarUniversidad(View campo) {
        String id = campoId.getText().toString().trim();
        try {
            Universidad u = dao.consultarUnaUniversidad(id);
            campoNombre.setText(u.getNombre());
            campoWww.setText(u.getWww() != null ? u.getWww() : "");
            Toast.makeText(this, "Universidad encontrada: " + u.getNombre(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
