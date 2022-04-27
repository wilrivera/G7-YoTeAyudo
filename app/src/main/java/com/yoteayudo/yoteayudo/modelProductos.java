package com.yoteayudo.yoteayudo;

public class modelProductos {
    String id, imgProducto, marca, modelo, tipo;

    public modelProductos() {
    }

    public modelProductos(String id, String imgProducto, String marca, String modelo, String tipo) {
        this.id = id;
        this.imgProducto = imgProducto;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImgProducto() {
        return imgProducto;
    }

    public void setImgProducto(String imgProducto) {
        this.imgProducto = imgProducto;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
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
}
