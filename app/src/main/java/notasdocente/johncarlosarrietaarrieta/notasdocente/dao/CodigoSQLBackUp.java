package notasdocente.johncarlosarrietaarrieta.notasdocente.dao;

public class CodigoSQLBackUp {

    public static String sqlBackup =
        "CREATE TABLE IF NOT EXISTS Universidades (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
            "nombre VARCHAR NOT NULL, " +
            "www VARCHAR" +
        ");" +
        "CREATE TABLE IF NOT EXISTS Programas (" +
            "id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL UNIQUE, " +
            "nombre VARCHAR NOT NULL, " +
            "director VARCHAR DEFAULT 'John Carlos Arrieta Arrieta', " +
            "email VARCHAR, " +
            "universidades_id INTEGER REFERENCES Universidades(id)" +
        ");" +
        "CREATE TABLE IF NOT EXISTS Alumnos (" +
            "codigo VARCHAR PRIMARY KEY NOT NULL UNIQUE, " +
            "nombre VARCHAR NOT NULL, " +
            "apellidos VARCHAR NOT NULL, " +
            "email VARCHAR, " +
            "telefono VARCHAR, " +
            "programas_id INTEGER REFERENCES Programas(id)" +
        ");" +
        "CREATE TABLE IF NOT EXISTS Asignaturas (" +
            "codigo VARCHAR NOT NULL UNIQUE, " +
            "nombre VARCHAR NOT NULL, " +
            "programas_id INTEGER REFERENCES Programas(id)" +
        ");" +
        "CREATE TABLE IF NOT EXISTS Calificaciones (" +
            "alumnos_id VARCHAR NOT NULL, " +
            "asignaturas_id VARCHAR NOT NULL, " +
            "nota1 FLOAT DEFAULT 0.0, " +
            "peso1 FLOAT DEFAULT 0.30, " +
            "nota2 FLOAT DEFAULT 0.0, " +
            "peso2 FLOAT DEFAULT 0.30, " +
            "nota3 FLOAT DEFAULT 0.0, " +
            "peso3 FLOAT DEFAULT 0.40, " +
            "PRIMARY KEY (alumnos_id, asignaturas_id), " +
            "FOREIGN KEY (alumnos_id) REFERENCES Alumnos(codigo), " +
            "FOREIGN KEY (asignaturas_id) REFERENCES Asignaturas(codigo)" +
        ");";
}
