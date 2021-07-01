package genetico;


import java.util.Comparator;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class Individuo {
    private int n;
    private double[] cromosoma;
    private double aptitud;
    private double distancia;
    private double ve;
    
    public Individuo() {
	n = 0;
	cromosoma=null;
    }
    
    public Individuo(int n) {
	this.n = n;
	cromosoma = new double[n];
    }
    
    public Individuo(Individuo I) {
	n = I.getN();
	cromosoma = I.getCromosoma();
    }
    
    public void generaAleatorio(LectorDeInstancias instancia) {
	
        double[] s1 = new double[this.n];
        double[] s2 = new double[this.n];
        double[] best = new double[this.n];
	double val = 0;
        for(int j=0; j<n; j++) {
	    while(val == 0.0) {
		val = Math.random();
	    }
            s1[j] = val;
	    val = 0.0;
        }
	val = 0;
	copiaArray(best,s1);
        for(int i=0; i<5; i++) {
            for(int j=0; j<n; j++) {
		while(val == 0.0) {
		    val = Math.random();
		}
                s2[j] = val;
		val = 0.0;
            }
	    
            if(instancia.getValorAptitud(s2) < instancia.getValorAptitud(s1)) {
                copiaArray(s1,s2);
                copiaArray(best,s2);
            }
        }
        copiaArray(this.cromosoma,best);
    }
    
    public void mutacionPorInsersion() {
	int index = this.getIntRandomNumber(0, n);
	double value = 0.0;
	while(value == 0) {
	    value = Math.random();
	}
	this.cromosoma[index] = value;
    }
    
    public void mutacionIntercambioReciproco(LectorDeInstancias instancia) {
        double[] cromosomaCopia = new double[this.cromosoma.length];
        copiaArrayAToB(this.cromosoma,cromosomaCopia);
        boolean factible=false;
        int indx1=0;
        int indx2=0;
        double aux=0.0;
        while(!factible) {
            indx1=getIntRandomNumber(0,this.cromosoma.length);
            indx2=getIntRandomNumber(0,this.cromosoma.length);
            
            aux = this.cromosoma[indx1];
            this.cromosoma[indx1] = this.cromosoma[indx2];
            this.cromosoma[indx2] = aux;
            
            if(instancia.cromosomaFactible(this.cromosoma)) {
                factible = true;
            } else {
                copiaArrayAToB(cromosomaCopia,this.cromosoma);
            }
        }
        
        
        //this.aptitud = instancia.getValorAptitud(this.cromosoma);
        
    }
    
    public void evaluaIndividuo(LectorDeInstancias instancia) {
        double value = instancia.getValorAptitud(this.cromosoma);
        //System.out.println("Value: " + value);
        this.distancia = value;
        if(value == 0) {
            this.aptitud = 150;
        } else {
            this.aptitud = 100/value;
        }
    }
    
    public void copiaArrayAToB(double[] a, double[] b) {
        for(int i=0; i<a.length; i++) {
            b[i] = a[i];
        }
    }
    
    public void copiaArray(double[] a, double[] b) {
        for(int i=0; i<a.length; i++) {
            a[i]=b[i];
        }
    }
    
    public void copia(Individuo g) {
        this.n = g.getN();
        this.cromosoma = new double[this.n];
        for(int i=0; i<this.n; i++){
            this.cromosoma[i] = g.getCromosoma()[i];
        }
    }
    
    public static Comparator<Individuo> comparadorAptitud = new Comparator<Individuo>() {
        public int compare(Individuo c1, Individuo c2) {
            if (c1.getAptitud() < c2.getAptitud()) {
                return 1;
            } else if (c1.getAptitud() > c2.getAptitud()) {
                return -1;
            } else if (c1.getAptitud() == c2.getAptitud()) {
                return 0;
            } else {
                return 0;
            }
        }
    };
    
    public static Comparator<Individuo> comparadorDistancia = new Comparator<Individuo>() {
        public int compare(Individuo c1, Individuo c2) {
            if (c1.getDistancia() > c2.getDistancia()) {
                return 1;
            } else if (c1.getDistancia() < c2.getDistancia()) {
                return -1;
            } else if (c1.getDistancia() == c2.getDistancia()) {
                return 0;
            } else {
                return 0;
            }
        }
    };
    
    private int getIntRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }
    
    public void imprimeCromosoma() {
        for(int i=0; i<n; i++) {
            System.out.print(this.cromosoma[i] + " ");
        }
        System.out.println("");
    }
    
    public boolean compararIguales(Individuo c) {
        boolean sonIguales=true;
        for(int i=0; i<this.cromosoma.length; i++) {
            if(this.cromosoma[i] != c.getCromosoma()[i]) {
                sonIguales = false;
                break;
            }
        }
        return sonIguales;
    }
    
    public double[] copyOfCromosoma() {
        double[] cCromosoma = new double[this.cromosoma.length];
        for(int i=0; i<this.cromosoma.length; i++) {
            cCromosoma[i] = this.cromosoma[i];
        }
        return cCromosoma;
    }

    /**
     * @return the n
     */
    public int getN() {
	return n;
    }

    /**
     * @param n the n to set
     */
    public void setN(int n) {
	this.n = n;
    }

    /**
     * @return the cromosoma
     */
    public double[] getCromosoma() {
	return cromosoma;
    }

    /**
     * @param cromosoma the cromosoma to set
     */
    public void setCromosoma(double[] cromosoma) {
	this.cromosoma = cromosoma;
    }

    /**
     * @return the aptitud
     */
    public double getAptitud() {
	return aptitud;
    }

    /**
     * @param aptitud the aptitud to set
     */
    public void setAptitud(double aptitud) {
	this.aptitud = aptitud;
    }
    
    public double getVe() {
        return ve;
    }

    /**
     * @param ve the ve to set
     */
    public void setVe(double ve) {
        this.ve = ve;
    }

    /**
     * @return the distancia
     */
    public double getDistancia() {
	return distancia;
    }

    /**
     * @param distancia the distancia to set
     */
    public void setDistancia(double distancia) {
	this.distancia = distancia;
    }
}
