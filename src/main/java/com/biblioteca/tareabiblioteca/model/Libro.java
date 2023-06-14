package com.biblioteca.tareabiblioteca.model;

public class Libro {
    private int id;
    private String titulo;
    private String editorial;
    private Categoria categoria;
    private int año;
    private String tipoLibro;

    public Libro(int id, String titulo, String editorial, Categoria categoria, int año, String tipoLibro) {
        this.id = id;
        this.titulo = titulo;
        this.editorial = editorial;
        this.categoria = categoria;
        this.año = año;
        this.tipoLibro = tipoLibro;
    }

    public int getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public String getTipoLibro() {
        return tipoLibro;
    }

    public void setTipoLibro(String tipoLibro) {
        this.tipoLibro = tipoLibro;
    }
}
