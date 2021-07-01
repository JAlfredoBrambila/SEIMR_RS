package genetico;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class EstadoInfeccion {
    private int dia;
    private double registro;
    private String municipio;
    
    public EstadoInfeccion() {
        
    }
    
    public EstadoInfeccion(int d, double reg, String mpo) {
        this.dia = d;
        this.registro = reg;
        this.municipio = mpo;
    }

    /**
     * @return the dia
     */
    public int getDia() {
        return dia;
    }

    /**
     * @param dia the dia to set
     */
    public void setDia(int dia) {
        this.dia = dia;
    }

    /**
     * @return the registro
     */
    public double getRegistro() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setRegistro(double registro) {
        this.registro = registro;
    }

    /**
     * @return the municipio
     */
    public String getMunicipio() {
        return municipio;
    }

    /**
     * @param municipio the municipio to set
     */
    public void setMunicipio(String municipio) {
        this.municipio = municipio;
    }
    
    
}
