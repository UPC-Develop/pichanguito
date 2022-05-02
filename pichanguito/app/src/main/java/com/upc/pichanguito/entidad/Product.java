package com.upc.pichanguito.entidad;

public class Product {
    private int id;
    private String nombre, codigo, descripcion, categoria, estado, sede;
    private int ancho, largo, precio, cambio;

    public Product(String nombre, String codigo, String descripcion,
                   String categoria, String estado, String sede,
                   int ancho, int largo, int precio, int cambio) {
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
        this.sede = sede;
        this.ancho = ancho;
        this.largo = largo;
        this.precio = precio;
        this.cambio = cambio;
    }

    public Product(int id, String nombre, String codigo, String descripcion,
                   int categoria, int estado, int sede,
                   String ancho, String largo, String precio, String cambio) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.estado = estado;
        this.sede = sede;
        this.ancho = ancho;
        this.largo = largo;
        this.precio = precio;
        this.cambio = cambio;
    }

    public Product(String nombre, String codigo, String descripcion, String cambio,
                   String categoria, String estado, String sede, int ancho, int largo, int precio) {


    }

    public int getId() {

        return id;
    }

    public void setId(int id) {

        this.id = id;
    }

    public String getNombre() {

        return nombre;
    }

    public void setNombre(String nombre) {

        this.nombre = nombre;
    }

    public String getCodigo() {

        return codigo;
    }

    public void setCodigo(String codigo) {

        this.codigo = codigo;
    }

    public String getDescripcion() {

        return descripcion;
    }

    public void setDescripcion(String descripcion) {

        this.descripcion = descripcion;
    }

    public String getCategoria() {

        return categoria;
    }

    public void setCategoria(String categoria) {

        this.categoria = categoria;
    }

    public String getEstado() {

        return estado;
    }

    public void setEstado(String estado) {

        this.estado = estado;
    }

    public String getSede() {

        return sede;
    }

    public void setSede(String sede) {

        this.sede = sede;
    }

    public int getAncho() {

        return ancho;
    }

    public void setAncho(int ancho) {

        this.ancho = ancho;
    }

    public int getLargo() {

        return largo;
    }

    public void setLargo(int largo) {

        this.largo = largo;
    }

    public int getPrecio() {

        return precio;
    }

    public void setPrecio(int precio) {

        this.precio = precio;
    }

    public int getCambio() {

        return cambio;
    }

    public void setCambio(int cambio) {

        this.cambio = cambio;
    }
}

