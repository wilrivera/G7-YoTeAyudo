package com.yoteayudo.yoteayudo.ui;

public class model {
    String direccion, HoraFin, HoraInicio, nombre, web, logo, imgTienda;
    Integer telefono;
    Double latitud, longitud;

    public model() {
    }

    public model(String direccion, String horaFin, String horaInicio, String nombre, String web, String logo, String imgTienda, Integer telefono, Double latitud, Double longitud) {
        this.direccion = direccion;
        HoraFin = horaFin;
        HoraInicio = horaInicio;
        this.nombre = nombre;
        this.web = web;
        this.logo = logo;
        this.imgTienda = imgTienda;
        this.telefono = telefono;
        this.latitud = latitud;
        this.longitud = longitud;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHoraFin() {
        return HoraFin;
    }

    public void setHoraFin(String horaFin) {
        HoraFin = horaFin;
    }

    public String getHoraInicio() {
        return HoraInicio;
    }

    public void setHoraInicio(String horaInicio) {
        HoraInicio = horaInicio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getWeb() {
        return web;
    }

    public void setWeb(String web) {
        this.web = web;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getImgTienda() {
        return imgTienda;
    }

    public void setImgTienda(String imgTienda) {
        this.imgTienda = imgTienda;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public Double getLatitud() {
        return latitud;
    }

    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }

    public Double getLongitud() {
        return longitud;
    }

    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
}
