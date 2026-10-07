package notasdocente.johncarlosarrietaarrieta.notasdocente;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ActividadPrincipal extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_actividad_principal);
    }

    public void mostrarAdminUniversidad(View component) {
        Intent intent = new Intent(this, ActividadCrudUniversidad.class);
        startActivity(intent);
    }
}
