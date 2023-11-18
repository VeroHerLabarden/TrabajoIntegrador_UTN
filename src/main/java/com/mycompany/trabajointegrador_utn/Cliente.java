package com.mycompany.trabajointegrador_utn;

public class Cliente {
    private int idCliente;
    private String razonSocial;
    private int numCuit;
    private String mail;
    private int telefono;
    private String serviciosContratados;
    private Incidente incidente;

    public Cliente() {
    }

    public Cliente(int idCliente, String razonSocial, int numCuit, String mail, int telefono, String serviciosContratados, Incidente incidente) {
        this.idCliente = idCliente;
        this.razonSocial = razonSocial;
        this.numCuit = numCuit;
        this.mail = mail;
        this.telefono = telefono;
        this.serviciosContratados = serviciosContratados;
        this.incidente = incidente;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }

    public int getNumCuit() {
        return numCuit;
    }

    public void setNumCuit(int numCuit) {
        this.numCuit = numCuit;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getServiciosContratados() {
        return serviciosContratados;
    }

    public void setServiciosContratados(String serviciosContratados) {
        this.serviciosContratados = serviciosContratados;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    
}
