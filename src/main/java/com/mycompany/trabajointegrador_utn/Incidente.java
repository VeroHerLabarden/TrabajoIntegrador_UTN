package com.mycompany.trabajointegrador_utn;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Objects;

public class Incidente {
    private int idIncidentes;
    private String descripcionIncidente;
    private Time tiempoResolucion;
    private boolean resuelto;
    private int idEspecialidad; 
    private LocalDate fechaResolucion;

    public Incidente() {
    }

    public Incidente(int idIncidentes, String descripcionIncidente, Time tiempoResolucion, boolean resuelto, int idEspecialidad,  LocalDate fechaResolucion) {
        this.idIncidentes = idIncidentes;
        this.descripcionIncidente = descripcionIncidente;
        this.tiempoResolucion = tiempoResolucion;
        this.resuelto = resuelto;
        this.idEspecialidad = idEspecialidad;
        this.fechaResolucion = fechaResolucion;
    }

    public String getDescripcionIncidente() {
        return descripcionIncidente;
    }

    public void setDescripcionIncidente(String descripcionIncidente) {
        this.descripcionIncidente = descripcionIncidente;
    }

    public Time getTiempoResolucion() {
        return tiempoResolucion;
    }

    public void setTiempoResolucion(Time tiempoResolucion) {
        this.tiempoResolucion = tiempoResolucion;
    }

    public boolean isResuelto() {
        return resuelto;
    }

    public void setResuelto(boolean resuelto) {
        this.resuelto = resuelto;
    }

    public int getIdEspecialidad() {
        return idEspecialidad;
    }

    public void setIdEspecialidad(int idEspecialidad) {
        this.idEspecialidad = idEspecialidad;
    }

    public int getIdIncidentes() {
        return idIncidentes;
    }

    public void setIdIncidentes(int idIncidentes) {
        this.idIncidentes = idIncidentes;
    }

    public LocalDate getFechaResolucion() {
        return fechaResolucion;
    }

    public void setFechaResolucion(LocalDate fechaResolucion) {
        this.fechaResolucion = fechaResolucion;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Incidente other = (Incidente) obj;
        if (this.resuelto != other.resuelto) {
            return false;
        }
        if (this.idEspecialidad != other.idEspecialidad) {
            return false;
        }
        if (this.idIncidentes != other.idIncidentes) {
            return false;
        }
        if (!Objects.equals(this.descripcionIncidente, other.descripcionIncidente)) {
            return false;
        }
        if (!Objects.equals(this.tiempoResolucion, other.tiempoResolucion)) {
            return false;
        }
        return Objects.equals(this.fechaResolucion, other.fechaResolucion);
    }
    
    
}
