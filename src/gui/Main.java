package gui;


import genetico.AlgoritmoGenetico;
import genetico.Individuo;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Alfredo Brambila Hdez. <alfredo.brambila@outlook.com>
 */
public class Main {

    public static void main(String[] args) {
	long startTime = 0;
        long endTime = 0;
	Individuo solucion = null;
	String[] archivos = {"muertos_tampico.csv", "muertos_madero.csv", "muertos_altamira.csv"};
	int region = 0; // 0 Tampico, 1 Madero, 2 Altamira
	double res[]= new double[3];
	ArrayList<String> lista = new ArrayList<String>();
	double[][] intervalo = {{0.00001,0.5},{1,30},{0.00001,0.9999},{1,30},{1,30},{0.00001,0.9999},{0.4,1.0}};
        // 0.00001,0.5),  1,30), 0.00001,0.9999),  1,30), 1,30),  0.00001,0.9999),  0.4,1.0)
	int tam_c=0;
	
	for(int i=0; i<1; i++) {
	    System.out.println("Experimento: " + i);
	    startTime = 0;
	    endTime = 0;
	    startTime = System.currentTimeMillis();
	    //AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(archivos[region],region);
            AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(archivos[region],region,5,0.2,150);
	    solucion = algoritmo.ejecutaGeneticoEstatico();
	    
	    endTime = System.currentTimeMillis() - startTime;
	    System.out.println(solucion.getCromosoma());
	    tam_c = solucion.getN();
	    res = algoritmo.seriesModeloResultadoValsT(solucion.getCromosoma());
	    lista.add(i+"\t"+res[0]+"\t"+res[1]+"\t"+res[2]+"\t"+res[3]+"\t"+res[4]+"\t"+endTime);
	    
            solucion.imprimeCromosoma();
            System.out.println("Reescalado: ");
	    for(int j=0; j<tam_c; j++) {
		solucion.getCromosoma()[j] = escalarValorEn(solucion.getCromosoma()[j],intervalo[j][0],intervalo[j][1]);
	    }
	    solucion.imprimeCromosoma();
	    solucion = null;
	}
	
	for(String s: lista) {
	    System.out.println(s);
	}
    }
    
    public static double escalarValorEn(double z, double a, double b) {
        return (z * (b - a)) + a;
    }
    
}
