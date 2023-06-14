package com.biblioteca.tareabiblioteca.controller;

import com.biblioteca.tareabiblioteca.model.Categoria;
import com.biblioteca.tareabiblioteca.model.Libro;
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

@WebServlet(name = "registrarLibroServlet", value = "/registroLibro")
public class RegistrarLibroServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            DBGenerator.iniciarBD("BibliotecaDB");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroLibro.jsp");
        req.setAttribute("categorias",obtenerCategorias());
        respuesta.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroLibroErroneo.jsp");
        if(req.getParameter("id_libro").length()!=0 && req.getParameter("titulo").length()!=0  &&
                req.getParameter("editorial").length()!=0 && req.getParameter("categoria").length()!=0 &&
                req.getParameter("año").length()!=0 &&  req.getParameter("tipo_libro").length()!=0){
            int id_libro = Integer.parseInt(req.getParameter("id_libro"));
            String titulo = req.getParameter("titulo");
            String editorial = req.getParameter("editorial");
            int id_categoria = Integer.parseInt(req.getParameter("categoria"));
            int año = Integer.parseInt(req.getParameter("año"));
            String tipoLibro = req.getParameter("tipo_libro");
            try {
                if(agregarLibro(id_libro,titulo,editorial,id_categoria,año,tipoLibro)){
                    req.setAttribute("libro",agregarLibro(id_libro,titulo,editorial,id_categoria,año,tipoLibro));
                    respuesta = req.getRequestDispatcher("/registroLibroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }
    private boolean agregarLibro(int id_libro, String titulo, String editorial, int id_categoria, int año, String tipoLibro) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("BibliotecaDB");
        List libros = LibroDAO.obtenerLibro(query,"id_libro", id_libro);
        if(libros.size()!=0){
            return false;
        }
        else{
            LibroDAO.registarLibro(query,id_libro,titulo,editorial,id_categoria,año,tipoLibro);
            return true;
        }
    }
    public List<Categoria> obtenerCategorias(){
        Connection connection= DBConnector.connection("BibliotecaDB","root","");
        DSLContext query= DSL.using(connection);
        return CategoriaDAO.obtenerCategorias(query);
    }
}