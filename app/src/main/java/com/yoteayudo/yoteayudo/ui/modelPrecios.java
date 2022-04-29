package com.yoteayudo.yoteayudo.ui;

public class modelPrecios {
    String nombre;
    Double precio;

    public modelPrecios() {
    }

    public modelPrecios(String nombre, Double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getPrecio() {
        return precio;
    }

    public void setPrecio(Double precio) {
        this.precio = precio;
    }
}
