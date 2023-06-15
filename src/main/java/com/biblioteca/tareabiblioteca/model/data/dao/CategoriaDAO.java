package com.biblioteca.tareabiblioteca.model.data.dao;

import com.biblioteca.tareabiblioteca.model.Categoria;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.Result;
import org.jooq.Table;
import org.jooq.impl.DSL;

import java.util.ArrayList;
import java.util.List;

import static org.jooq.impl.DSL.*;

public class CategoriaDAO {
    public static void registarCategoria(DSLContext query, Categoria categoria){
        Table tablaCategoria= table(name("Categoria"));
        Field[] columnas = tablaCategoria.fields("id_categoria","nombre");
        query.insertInto(tablaCategoria, columnas[0], columnas[1])
                .values(categoria.getId(),categoria.getNombre())
                .execute();
    }
    public static List obtenerCategoria(DSLContext query, String columnaTabla, Object dato){
        Result resultados = query.select().from(DSL.table("Categoria")).where(DSL.field(columnaTabla).eq(dato)).fetch();
        return obtenerListaCategorias(resultados);
    }
    public static List obtenerListaCategorias(Result resultados){
        List<Categoria> categorias= new ArrayList<>();
        for(int fila=0; fila<resultados.size();fila++){
            int id = (Integer) resultados.getValue(fila,"id_categoria");
            String nombre = (String) resultados.getValue(fila,"nombre");
            categorias.add(new Categoria(id,nombre));
        }
        return categorias;
    }
    public static List<Categoria> obtenerCategorias(DSLContext query){
        Result result=query.select().from(DSL.table("Categoria")).fetch();
        List<Categoria>categorias=new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            categorias.add(new Categoria(
                    (Integer) result.getValue(i,"id_categoria"),
                    (String) result.getValue(i,"nombre")));
        }
        return categorias;
    }
    public static Categoria buscarCategoria(DSLContext query, String columna, Object dato){
        Result result  = query.select().from(DSL.table("Categoria")).where(DSL.field(columna).eq(dato)).fetch();
        Categoria categoria = new Categoria((Integer) result.getValue(0,"id_categoria"),(String) result.getValue(0,"nombre"));
        return categoria;
    }
}

