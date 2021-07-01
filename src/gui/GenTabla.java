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
public class GenTabla {

    public static void main(String[] args) {
	
	Individuo solucion = null;
	String[] archivos = {"muertos_tampico.csv", "muertos_madero.csv", "muertos_altamira.csv"};
	int region = 2; // 0 Tampico, 1 Madero, 2 Altamira
	double res[]= new double[3];
	ArrayList<String> lista = new ArrayList<String>();
	double[][] intervalo = {{0.00001,0.5},{1,30},{0.00001,0.9999},{1,30},{1,30},{0.00001,0.9999},{0.4,1.0}};
	
	double[] valoresParams = {0.9699211153752103,0.05815001097998995,0.8222247363497488,0.5297983919485263,0.4619881907406782,0.1369250418142577,0.15023146807099497};
	    //AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(archivos[region],region);
            AlgoritmoGenetico algoritmo = new AlgoritmoGenetico(archivos[region],region,1,0.2,1);
	    solucion = algoritmo.ejecutaGeneticoEstatico();
	    
	    //System.out.println(solucion.getCromosoma());
	    res = algoritmo.seriesModeloResultadoValsT(valoresParams);
	    System.out.println("\t"+res[0]+"\t"+res[1]+"\t"+res[2]+"\t"+res[3]+"\t"+res[4]);
	    
	    
    }
    
    public static double escalarValorEn(double z, double a, double b) {
        return (z * (b - a)) + a;
    }
    
}
