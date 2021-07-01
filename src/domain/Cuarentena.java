/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author al_x3
 */
public class Cuarentena {
    private int diaInicio;
    private int diaFin;
    private double porcentaje;
    private int escenario;
    private int sintomatico;
    
    public Cuarentena() {
        this.diaInicio=0;
        this.diaFin=0;
        this.porcentaje=0.0;
	this.escenario=0;
    }
    
    public Cuarentena(int inicio, int fin, double porcentaje, int escenario, int sintomatico) {
        this.diaInicio=inicio;
        this.diaFin=fin;
        this.porcentaje=porcentaje;
	this.escenario = escenario;
	this.sintomatico = sintomatico;
    }
    
    public Cuarentena(int inicio, int sintomatico, double porcentaje) {
        this.diaInicio=inicio;
        this.porcentaje=porcentaje;
	this.sintomatico = sintomatico;
	//System.out.println("Inic: " + this.diaInicio + " Sint: " + this.sintomatico + " Alpa: " + this.porcentaje);
    }
    
    public double getAlpha(int n) {
	return porcentaje;
    }
    
    public boolean enRango(int dia, int sintomaticos, int esc) {
	boolean enRango = false;
	if(dia >= this.diaInicio && dia <= this.diaFin && sintomaticos == this.sintomatico && esc == this.escenario) {
	    enRango = true;
	}
	return enRango;
    }
    
    public boolean enRangoC(int dia, int sintomaticos, int esc) {
	boolean enRango = false;
	if(dia == this.diaInicio && sintomaticos == this.sintomatico) {
	    enRango = true;
	}
	return enRango;
    }
    
    /*public Cuarentena(int inicio, int fin, double porcentaje) {
        this.diaInicio=inicio;
        this.diaFin=fin;
        this.porcentaje=porcentaje;
    }*/

    /**
     * @return the diaInicio
     */
    public int getDiaInicio() {
        return diaInicio;
    }

    /**
     * @param diaInicio the diaInicio to set
     */
    public void setDiaInicio(int diaInicio) {
        this.diaInicio = diaInicio;
    }

    /**
     * @return the diaFin
     */
    public int getDiaFin() {
        return diaFin;
    }

    /**
     * @param diaFin the diaFin to set
     */
    public void setDiaFin(int diaFin) {
        this.diaFin = diaFin;
    }

    /**
     * @return the porcentaje
     */
    public double getPorcentaje() {
        return porcentaje;
    }

    /**
     * @param porcentaje the porcentaje to set
     */
    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    /**
     * @return the escenario
     */
    public int getEscenario() {
	return escenario;
    }

    /**
     * @param escenario the escenario to set
     */
    public void setEscenario(int escenario) {
	this.escenario = escenario;
    }
    
}
