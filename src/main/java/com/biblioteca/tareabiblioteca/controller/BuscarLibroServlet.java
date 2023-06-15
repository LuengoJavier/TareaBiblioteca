package com.biblioteca.tareabiblioteca.controller;

import com.biblioteca.tareabiblioteca.model.Categoria;
import com.biblioteca.tareabiblioteca.model.data.DBConnector;
import com.biblioteca.tareabiblioteca.model.data.DBGenerator;
import com.biblioteca.tareabiblioteca.model.data.dao.CategoriaDAO;
import com.biblioteca.tareabiblioteca.model.data.dao.LibroDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jooq.DSLContext;
import org.jooq.impl.DSL;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

@WebServlet(name = "buscarLibroServlet", value = "/buscarLibro")
public class BuscarLibroServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("BibliotecaDB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        RequestDispatcher respuesta = req.getRequestDispatcher("/buscarLibro.jsp");
        req.setAttribute("categorias",obtenerCategorias());
        respuesta.forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher respuesta = req.getRequestDispatcher("/busquedaLibroErroneo.jsp");
        if(req.getParameter("categoria").length()!=0){
            int id_categoria = Integer.parseInt(req.getParameter("categoria"));
            try {
                if(buscarLibrosCategoria(id_categoria)!=null){
                    req.setAttribute("libros",buscarLibrosCategoria(id_categoria));
                    respuesta = req.getRequestDispatcher("/mostrarLibros.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("categorias",obtenerCategorias());
        respuesta.forward(req,resp);
    }
    private List buscarLibrosCategoria(int id_categoria) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("BibliotecaDB");
        List libros = LibroDAO.buscarLibrosCategoria(query, id_categoria);
       if(libros.size()!=0){
           return libros;
       }else {
           return null;
       }


    }
    public List<Categoria> obtenerCategorias(){
        Connection connection= DBConnector.connection("BibliotecaDB","root","");
        DSLContext query= DSL.using(connection);
        return CategoriaDAO.obtenerCategorias(query);
    }
}
