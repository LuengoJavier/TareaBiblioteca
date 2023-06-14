package com.biblioteca.tareabiblioteca.model.data;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import java.sql.Connection;
import static org.jooq.impl.DSL.*;
import static org.jooq.impl.SQLDataType.*;
public class DBGenerator {
    //Metodo inicial para crear una base de datos y sus respectivas tablas en caso de que no exista
    public static void iniciarBD(String nombreBD) throws ClassNotFoundException {
        Connection connection = DBConnector.connection("root", "");
        DSLContext create = DSL.using(connection);
        crearBaseDato(create, nombreBD);
        create = actualizarConexion(connection, nombreBD);
        crearTablaLibro(create);
        crearTablaCategoria(create);
        DBConnector.closeConnection();
    }
    //Metodo para conectarse a una base de datos ya creada
    public static DSLContext conectarBD(String nombre) throws ClassNotFoundException {
        Connection connection = DBConnector.connection(nombre,"root","");
        return DSL.using(connection);
    }
    //Crea una base de datos en caso de que no exista
    private static void crearBaseDato(DSLContext create, String nombreBD){
        create.createDatabaseIfNotExists(nombreBD).execute();
    }
    //Actualiza la conexion inicial para conectar a la base de datos
    private static DSLContext actualizarConexion(Connection connection,String nombreBD){
        DBConnector.closeConnection();
        connection= DBConnector.connection(nombreBD,"root","");
        DSLContext create =DSL.using(connection);
        return create;
    }
    private static void crearTablaLibro(DSLContext create) {
        create.createTableIfNotExists( "Libro")
                .column("id_libro", INTEGER.identity(true)).constraint(primaryKey("id_libro"))
                .column("titulo", VARCHAR(100))
                .column("editorial", VARCHAR(70))
                .column("categoria", VARCHAR(70))
                .column("año", INTEGER)
                .column("tipo_libro",VARCHAR(20)).execute();
    }
    public static void crearTablaCategoria(DSLContext create){
        create.createTableIfNotExists("Categoria")
                .column("id_categoria", INTEGER.identity(true)).constraint(primaryKey("id_categoria"))
                .column("nombre",VARCHAR(20)).execute();
    }
}
