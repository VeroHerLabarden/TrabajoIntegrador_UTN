package com.mycompany.trabajointegrador_utn;

public class Tecnico {
    private int idTecnico;
    private int Dni;
    private String nombre;
    private Especialidad especialidad;
    private Incidente incidente;
    private boolean disponible;
    private int resueltos;
    private String notificacion;
    private int incidentesResueltos;

    public Tecnico() {
    }

    public Tecnico(int idTecnico, int Dni, String nombre, Especialidad especialidad, Incidente incidente, boolean disponible) {
    }

    public int getIdTecnico() {
        return idTecnico;
    }

    public void setIdTecnico(int idTecnico) {
        this.idTecnico = idTecnico;
    }

    public int getDni() {
        return Dni;
    }

    public void setDni(int Dni) {
        this.Dni = Dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getResueltos() {
        return resueltos;
    }

    public void setResueltos(int resueltos) {
        this.resueltos = resueltos;
    }

    public String getNotificacion() {
        return notificacion;
    }

    public void setNotificacion(String notificacion) {
        this.notificacion = notificacion;
    }

    public int getIncidentesResueltos() {
        return incidentesResueltos;
    }

    public void setIncidentesResueltos(int insidentesResueltos) {
        this.incidentesResueltos = insidentesResueltos;
    }
}

