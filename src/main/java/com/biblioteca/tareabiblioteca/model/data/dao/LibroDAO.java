package com.biblioteca.tareabiblioteca.model.data.dao;

import com.biblioteca.tareabiblioteca.model.Categoria;
import com.biblioteca.tareabiblioteca.model.Libro;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class LibroDAO {
    public static Libro registarLibro(DSLContext query, int id_libro, String titulo, String editorial, int id_categoria, int año, String tipoLibro){
        Categoria categoria= CategoriaDAO.buscarCategoria(query,"id_categoria",id_categoria);
        Table tablaLibro= table(name("Libro"));
        Field[] columnas = tablaLibro.fields("id_libro","titulo","editorial","categoria","año","tipo_libro");
        query.insertInto(tablaLibro, columnas[0], columnas[1],columnas[2],columnas[3],columnas[4],columnas[5])
                .values(id_libro,titulo,editorial,categoria.getNombre(),año,tipoLibro)
                .execute();
        Libro libro = new Libro(id_libro,titulo,editorial,categoria,año,tipoLibro);
        return libro;
    }
    public static List obtenerLibro(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Libro")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaLibros(query, resultados);
    }
    private static List obtenerListaLibros(DSLContext query,Result resultados){
        List<Libro> libros= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            int id = (Integer) resultados.getValue(fila,"id_libro");
            String titulo = (String) resultados.getValue(fila,"titulo");
            String editorial = (String) resultados.getValue(fila,"editorial");
            String categoria = (String) resultados.getValue(fila,"categoria");
            int año = (Integer) resultados.getValue(fila,"año");
            String tipoLibro = (String) resultados.getValue(fila,"tipo_libro");
            System.out.println(categoria);
            Categoria categoria1 = CategoriaDAO.buscarCategoria(query,"nombre",categoria);
            libros.add(new Libro(id,titulo,editorial,categoria1,año,tipoLibro));
        }
        return libros;
    }
}
