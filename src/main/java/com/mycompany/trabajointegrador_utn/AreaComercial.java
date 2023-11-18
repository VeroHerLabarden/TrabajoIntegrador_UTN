package com.mycompany.trabajointegrador_utn;

public class AreaComercial {
    private int idAreaComercial;
    private Cliente cliente;

    public AreaComercial() {
    }

    public AreaComercial(int idAreaComercial, Cliente cliente) {
        this.idAreaComercial = idAreaComercial;
        this.cliente = cliente;
    }

    public int getIdAreaComercial() {
        return idAreaComercial;
    }

    public void setIdAreaComercial(int idAreaComercial) {
        this.idAreaComercial = idAreaComercial;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public void altasClientes(){ }
    public void bajasClientes(){ }
    public void modificarClientes(){ }
}
