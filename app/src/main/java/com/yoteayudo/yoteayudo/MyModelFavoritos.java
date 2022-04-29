package com.yoteayudo.yoteayudo;

public class MyModelFavoritos {
    String modelo, tipo, precio;

    public MyModelFavoritos() {
    }

    public MyModelFavoritos(String modelo, String tipo, String precio) {
        this.modelo = modelo;
        this.tipo = tipo;
        this.precio = precio;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
