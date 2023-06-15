package com.biblioteca.tareabiblioteca.controller;

import com.biblioteca.tareabiblioteca.model.Categoria;
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

import java.io.IOException;
import java.util.List;

@WebServlet(name = "registrarCategoriaServlet", value = "/registroCategoria")
public class RegistrarCategoriaServlet extends HttpServlet {
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
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroCategoria.jsp");
        respuesta.forward(req,resp);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException  {
        RequestDispatcher respuesta = req.getRequestDispatcher("/registroCategoriaErroneo.jsp");
        if(req.getParameter("id_categoria").length()!=0 && req.getParameter("nombre").length()!=0){
            int id = Integer.parseInt(req.getParameter("id_categoria"));
            String nombre = req.getParameter("nombre");
            Categoria categoria = new Categoria(id,nombre);
            try {
                if(agregarCategoria(categoria)){
                    req.setAttribute("categoria",categoria);
                    respuesta = req.getRequestDispatcher("/registroCategoriaExitoso.jsp");
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        respuesta.forward(req,resp);
    }
    private boolean agregarCategoria(Categoria categoria) throws ClassNotFoundException {
        DSLContext query= DBGenerator.conectarBD("BibliotecaDB");
        List categorias = CategoriaDAO.obtenerCategoria(query,"id_categoria", categoria.getId());
        if(categorias.size()!=0){
            return false;
        }
        else{
            CategoriaDAO.registarCategoria(query,categoria);
            return true;
        }
    }
}
