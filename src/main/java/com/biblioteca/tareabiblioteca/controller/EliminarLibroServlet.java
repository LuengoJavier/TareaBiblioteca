package com.biblioteca.tareabiblioteca.controller;

import com.biblioteca.tareabiblioteca.model.Libro;
import com.biblioteca.tareabiblioteca.model.data.DBConnector;
import com.biblioteca.tareabiblioteca.model.data.DBGenerator;
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

@WebServlet(name = "eliminarLibroServlet", value = "/eliminarLibro")
public class EliminarLibroServlet extends HttpServlet {
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
        RequestDispatcher respuesta = req.getRequestDispatcher("/eliminarLibro.jsp");
        req.setAttribute("libros",obtenerLibros());
        respuesta.forward(req,resp);
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher respuesta = req.getRequestDispatcher("/eliminarLibroErroneo.jsp");
        if(req.getParameter("libro")!=null){
            int id_libro = Integer.parseInt(req.getParameter("libro"));
            try {
                if(eliminarLibro(id_libro)){
                    respuesta = req.getRequestDispatcher("/eliminarLibroExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        req.setAttribute("libros",obtenerLibros());
        respuesta.forward(req,resp);
    }
    private boolean eliminarLibro(int id_libro) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("BibliotecaDB");
        LibroDAO.eliminarLibro(query,id_libro);
        return true;
    }
    public List<Libro> obtenerLibros(){
        Connection connection= DBConnector.connection("BibliotecaDB","root","");
        DSLContext query= DSL.using(connection);
        return LibroDAO.obtenerLibros(query);
    }
}
