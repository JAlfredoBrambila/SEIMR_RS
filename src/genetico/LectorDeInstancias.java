package genetico;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import domain.ModelSEIMRRS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class LectorDeInstancias {

    private int nRegistros;
    private ArrayList<EstadoInfeccion> registro;
    private String municipio;
    ModelSEIMRRS modelo;
    private int region=0;

    public LectorDeInstancias() {
        this.nRegistros=0;
        registro = new ArrayList<EstadoInfeccion>();
        modelo = new ModelSEIMRRS();
    }
  
    public void leerArchivo(String archivoStr, int region) {
	this.region = region;
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            archivo = new File(archivoStr);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);
            
            String linea;
            linea = br.readLine();
            this.municipio = linea;
            
            StringTokenizer tokens;
            int i=0;
            int dia=0; int valorR=0;
            while ((linea = br.readLine()) != null) {
                tokens = new StringTokenizer(linea, ",");
                dia = Integer.parseInt(tokens.nextToken());
                valorR = Integer.parseInt(tokens.nextToken());
                registro.add(new EstadoInfeccion(dia,valorR,municipio));
                i++;
            }
	    this.nRegistros = registro.size();
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
    }
    
    public double getValorAptitud(double[] vector) {
        double valor=0;
        double[] serie;
	//modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.00001,0.9999), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.00001,0.9999), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.00001,0.9999));
	//double[][] intervalo = {{0.1,0.00001},{4,10},{0.00001,0.9999},{4,10},{4,10},{0.00001,0.9999}};
	modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.00001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.00001,0.9999), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.00001,0.9999), escalarValorEn(vector[6], 0.4,1.0));

        modelo.condicionesIniciales();
        modelo.simulaModelo();
        serie = modelo.getValoresSerieMuertos_Region(this.region);
        double suma=0;
        for(EstadoInfeccion ie:this.registro) {
            suma += Math.abs(serie[ie.getDia()-1] - ie.getRegistro());
        }
        valor = suma/this.registro.size();
        if(Double.isNaN(valor)) {
            System.out.println("NAN: " + suma + " / " + this.registro.size() + " " + vector[0] + " " + vector[1] + " " + vector[2] + " " + vector[3] + " " + vector[4] + " " + vector[5] + " " + vector[6]);
        }
        return valor;
    }
    
    public double getValorAptitud10(double[] vector) {
        double valor=0;
        double[] serie;
	int step=0;
	//modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.0001,0.9999), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.0001,0.9999), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.0001,0.9999));
	modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.00001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.00001,0.9999), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.00001,0.9999), escalarValorEn(vector[5], 0.4,1.0));

	//System.out.println("---------- Fin: " + this.registro.get(this.registro.size()-1).getDia());
        modelo.condicionesIniciales();
        modelo.simulaModelo();
        serie = modelo.getValoresSerieMuertos_Region(this.region);
        double suma=0;
	step = this.registro.size()/10;
        //for(EstadoInfeccion ie:this.registro) {
	for(int i=0; i<this.registro.size(); i+=step) {
            //suma += Math.abs(serie[ie.getDia()-1] - ie.getRegistro());
	    suma += Math.abs(serie[this.registro.get(i).getDia()-1] - this.registro.get(i).getRegistro());
        }
        valor = suma/10;
        return valor;
    }
    
    public void seriesModeloResultado(double[] vector) {
        //modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.00030, 0.00100),escalarValorEn(vector[1], 0.0020,0.070),escalarValorEn(vector[2], 0.040,0.070));
	//modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.000001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.000001,0.9), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.000001,0.9));
	modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.00001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.00001,0.9999), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.00001,0.9999), escalarValorEn(vector[5], 0.4,1.0));
        double[] serie;
        modelo.condicionesIniciales();
        modelo.simulaModelo();
        serie = modelo.getValoresSerieMuertos_Region(this.region);
        System.out.println("Dia\tSerie\tRegistro");
        System.out.println("----------------------------");
	double error=0.0;
	double promSim=0;
	double promReg=0;
        for(EstadoInfeccion ie:this.registro) {
            //System.out.println("Dia: " + ie.getDia() + ", Serie: " + serie[ie.getDia()-1] +" Registro: "+ ie.getRegistro());
	    System.out.println(ie.getDia() + "\t" + serie[ie.getDia()-1] +"\t"+ ie.getRegistro());
	    error += Math.abs(serie[ie.getDia()-1] - ie.getRegistro());
	    promSim += serie[ie.getDia()-1];
	    promReg += ie.getRegistro();
        }
	//System.out.println("Error\t" + (error)/registro.size());
	System.out.println("Error\tPromedio Sim\tPromedio Reg");
	System.out.println((error)/registro.size() + "\t" + (promSim/registro.size()) + "\t" + (promReg/registro.size()));
    }
    
    public double[] seriesModeloResultadoValsT(double[] vector) {
	double[] resultados = new double[5];
        //modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.000001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.000001,0.9), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.000001,0.9));
	modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.00001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.00001,0.9999), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.00001,0.9999), escalarValorEn(vector[6], 0.4,1.0));
        double[] serie;
        modelo.condicionesIniciales();
        modelo.simulaModelo();
        serie = modelo.getValoresSerieMuertos_Region(this.region);
        System.out.println("Dia\tSerie\tRegistro");
        System.out.println("----------------------------");
	double distancia=0.0;
        double sumMSE = 0.0;
	double promSim=0;
	double promReg=0;
        for(EstadoInfeccion ie:this.registro) {
            //System.out.println("Dia: " + ie.getDia() + ", Serie: " + serie[ie.getDia()-1] +" Registro: "+ ie.getRegistro());
	    System.out.println(ie.getDia() + "\t" + serie[ie.getDia()-1] +"\t"+ ie.getRegistro());
	    distancia += Math.abs(serie[ie.getDia()-1] - ie.getRegistro());
            sumMSE += Math.pow((serie[ie.getDia()-1] - ie.getRegistro()), 2);
	    promSim += serie[ie.getDia()-1];
	    promReg += ie.getRegistro();
        }
	//System.out.println("Error\t" + (error)/registro.size());
	System.out.println("Dist.\tPromedio Sim\tPromedio Reg\tMSE");
	System.out.println((distancia)/registro.size() + "\t" + (promSim/registro.size()) + "\t" + (promReg/registro.size())+"\t" + (sumMSE/registro.size()));
	resultados[0] = ((distancia)/registro.size());
	resultados[1] = (promSim/registro.size());
	resultados[2] = (promReg/registro.size());
        resultados[3] = (sumMSE/registro.size());
        resultados[4] = Math.sqrt((sumMSE/registro.size()));
	
	return resultados;
    }
    
    public boolean cromosomaFactible(double[] cromosoma) {
	boolean esFactible = true;
	for(int i=0; i<cromosoma.length; i++) {
	    if(cromosoma[i] <= 0) {
		esFactible = false;
	    }
	}
	return esFactible;
    }
    
    private double normalizar(double x, double a, double b) {
        return (x - a) / (b - a);
    }

    public double escalarValorEn(double z, double a, double b) {
        return (z * (b - a)) + a;
    }
    
    /*
    public double getError(double valor) {
        double e=0;
        
        return e;
    }
    */

    /**
     * @return the nRegistros
     */
    public int getnRegistros() {
        return nRegistros;
    }

    /**
     * @param nObjetos the nRegistros to set
     */
    public void setnRegistros(int nObjetos) {
        this.nRegistros = nObjetos;
    }
    
    /**
     * @return the registro
     */
    public ArrayList<EstadoInfeccion> getRegistros() {
        return registro;
    }

    /**
     * @param registro the registro to set
     */
    public void setObjetos(ArrayList<EstadoInfeccion> registro) {
        this.registro = registro;
    }
}
