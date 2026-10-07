package notasdocente.johncarlosarrietaarrieta.notasdocente.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class ConexionBasedatos extends SQLiteOpenHelper {

    public static final int MODO_LECTURA = 0;
    public static final int MODO_ESCRITURA = 1;

    private SQLiteDatabase conexion;
    private String nombreBD;
    private int versionBD;
    private Context actividad;
    private String estado;

    public ConexionBasedatos(Context actividad, String nombreBD, CursorFactory cursor, int versionBD) {
        super(actividad, nombreBD, cursor, versionBD);
        this.actividad = actividad;
        this.nombreBD = nombreBD;
        this.versionBD = versionBD;
        this.estado = "Sin conectar";
    }

    @Override
    public void onCreate(SQLiteDatabase bd) {
        bd.execSQL(CodigoSQLBackUp.sqlBackup);
    }

    @Override
    public void onUpgrade(SQLiteDatabase bd, int versionActual, int versionNueva) {
        bd.execSQL(CodigoSQLBackUp.sqlBackup);
    }

    public void conectar(int modo) {
        if (modo == MODO_ESCRITURA) {
            conexion = getWritableDatabase();
            estado = "Conectado en modo escritura";
        } else {
            conexion = getReadableDatabase();
            estado = "Conectado en modo lectura";
        }
    }

    public void desconectar() {
        if (conexion != null && conexion.isOpen()) {
            conexion.close();
            estado = "Desconectado";
        }
    }

    public void ejecutarSQL(String sql) {
        conexion.execSQL(sql);
    }

    public void insertar(String tabla, ContentValues columnasValor) {
        conexion.insertOrThrow(tabla, null, columnasValor);
    }

    public void actualizar(String tablas, ContentValues columnasValor,
                           String whereColumnasIgualValor, String[] valorWhere) {
        conexion.update(tablas, columnasValor, whereColumnasIgualValor, valorWhere);
    }

    public void eliminar(String tabla, String whereColumnasIgualValor, String[] valorWhere) {
        conexion.delete(tabla, whereColumnasIgualValor, valorWhere);
    }

    public void eliminar(String sqlDelete, String[] valorWhere) {
        conexion.execSQL(sqlDelete, valorWhere);
    }

    public Cursor consultar(String consultaSQL, String[] valorWhere) {
        return conexion.rawQuery(consultaSQL, valorWhere);
    }

    public Cursor consultar(String tablas, String[] soloColumnas,
                            String whereColumnasIgualValor, String[] valorWhere,
                            String groupBy, String having,
                            String orderBy, String limite) {
        return conexion.query(tablas, soloColumnas, whereColumnasIgualValor,
                valorWhere, groupBy, having, orderBy, limite);
    }

    public SQLiteDatabase getConexion() {
        return conexion;
    }

    public String getNombreBD() {
        return nombreBD;
    }

    public int getVersionBD() {
        return versionBD;
    }

    public String getEstado() {
        return estado;
    }
}
