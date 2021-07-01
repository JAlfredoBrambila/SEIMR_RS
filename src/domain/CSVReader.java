/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class CSVReader {

    private String data_gen = "DATA_GEN.csv";
    private String pob_st_rg = "POB_ST_RG_SS.csv";
    
    public int poblacionTotal() {
	int total = 0;
	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(data_gen);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            
            linea = br.readLine();
	    linea = br.readLine();
	    StringTokenizer tokens;
	    tokens = new StringTokenizer(linea, ",");
	    total = Integer.parseInt(tokens.nextToken());
	    
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("Error al cerrar el archivo: " + e2);
            }
        }
	return total;
    }
    
    //[this.regiones][this.estratos][n]
    public double[][][] condicionesInicialesSU(int region, int segmento, int n) {
	double[][][] valores = new double[region][segmento][n];
	File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(pob_st_rg);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
	    StringTokenizer tokens;
            
            linea = br.readLine();
	    String estado = "SU";
	    while ((linea = br.readLine()) != null) {
                tokens = new StringTokenizer(linea, " ");
                tokens.nextToken();
		if(estado.equals(tokens.nextToken())) {
		    
		}
            }
	    
        } catch (Exception e) {
            System.out.println("Error al leer archivo: " + e);
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e2) {
                System.out.println("Error al cerrar el archivo: " + e2);
            }
        }
	return valores;
    }
}
