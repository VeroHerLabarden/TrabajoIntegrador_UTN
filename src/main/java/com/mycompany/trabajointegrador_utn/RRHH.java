package com.mycompany.trabajointegrador_utn;

public class RRHH {
    private int idRRHH;
    private Tecnico tecnico;
    private Incidente incidente;

    public RRHH() {
    }

    public RRHH(int idRRHH, Tecnico tecnico, Incidente incidente) {
    }

    public int getIdRRHH() {
        return idRRHH;
    }

    public void setIdRRHH(int idRRHH) {
        this.idRRHH = idRRHH;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Incidente getIncidente() {
        return incidente;
    }

    public void setIncidente(Incidente incidente) {
        this.incidente = incidente;
    }

    public void altasTecnicos() {
    }
  
    public void bajasTecnicos() {
    }

    public void modificarTecnicos() {
    }

    public void emitirReportes() {
    }
}
