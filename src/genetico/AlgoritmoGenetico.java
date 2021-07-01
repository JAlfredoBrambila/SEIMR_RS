package genetico;


import java.util.ArrayList;
import java.util.Collections;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author J. Alfredo Brambila Hdez. <alfredo.brambila@outlook.com>
 */
public class AlgoritmoGenetico {
    private LectorDeInstancias instancia;
    private ArrayList<Individuo> poblacion;
    ArrayList<Individuo> nuevosPadres = new  ArrayList<Individuo>();
    ArrayList<Individuo> padresehijos = new  ArrayList<Individuo>();
    Individuo mejorIndividuoPop = null;
    int tamPoblacion;
    double porcMutacion;
    private int tamCromsoma;
    private int numGeneraciones;
    int N = 5;
    
    //int N=5, double porcMutacion = 0.2, int numGeneraciones = 150
    public AlgoritmoGenetico(String archivo,int region) {
	//System.out.println("Leer Archivo " + archivo);
	instancia = new LectorDeInstancias();
	instancia.leerArchivo(archivo,region);
	//System.out.println("LLLLLLLL********************");
	tamCromsoma = 7;
	tamPoblacion =  (N * tamCromsoma)%2 == 0 ? N * tamCromsoma : (N * tamCromsoma)+1;;
	poblacion = new  ArrayList<Individuo>();
	porcMutacion = 0.2;
	numGeneraciones = 150;
    }
    
    //AlgoritmoGenetico(archivos[region],region,5,0.2,150);
    public AlgoritmoGenetico(String archivo,int region, int N, double porcMutacion, int numGeneraciones) {
	this.instancia = new LectorDeInstancias();
	this.instancia.leerArchivo(archivo,region);
	this.tamCromsoma = 7;
	this.tamPoblacion =  (N * tamCromsoma)%2 == 0 ? N * tamCromsoma : (N * tamCromsoma)+1;;
	this.poblacion = new  ArrayList<Individuo>();
        this.N = N;
	this.porcMutacion = porcMutacion;
	this.numGeneraciones = numGeneraciones;
    }
    
    public Individuo ejecutaGeneticoEstatico() {
	//System.out.println("Genetico Estatico");
	generaPoblacionInicial();
	this.evaluar(poblacion);
	
	for(int i=0; i<numGeneraciones; i++) {
	    System.out.println("Generacion: " + (i+1) + " de " + numGeneraciones);
	    System.out.print("Seleccion, ");
	    //this.seleccionRuleta();
            this.seleccionTorneoDeterminista();
	    poblacion.clear();
	    System.out.print("Cruza, ");
            //poblacion = this.cruzarConCruzaSBX();
	    poblacion = this.cruzarConCruza2Puntos();
	    //this.evaluar(poblacion);
	    System.out.println("Muta");
	    this.mutacionPorInsersion(poblacion);
	    //System.out.println("Evaluar");
	    //this.evaluar(poblacion);
	    //Collections.sort(poblacion,Individuo.comparadorDistancia);
	}
	//modelo.setParametrosGenerados(escalarValorEn(vector[0], 0.0001,0.1), escalarValorEn(vector[1], 4,12), escalarValorEn(vector[2], 0.01,0.9999), escalarValorEn(vector[3], 4,12), escalarValorEn(vector[4], 4,12), escalarValorEn(vector[5], 0.01,0.9999));
	//double[][] intervalo = {{0.0001,0.1},{4,12},{0.01,0.9999},{4,12},{4,12},{0.01,0.9999}};
	
	Collections.sort(poblacion,Individuo.comparadorDistancia);
	//this.instancia.escalarValorEn(vector[0], 0.000001,0.5), escalarValorEn(vector[1], 1,30), escalarValorEn(vector[2], 0.000001,0.9), escalarValorEn(vector[3], 1,30), escalarValorEn(vector[4], 1,30), escalarValorEn(vector[5], 0.000001,0.9)
	//int tam_c = poblacion.get(0).getN();
	//double[] resultados = new double[tam_c];
	
	/*for(int i=0; i<tam_c; i++) {
	   poblacion.get(0).getCromosoma()[i] = this.instancia.escalarValorEn(poblacion.get(0).getCromosoma()[i],intervalo[i][0],intervalo[i][1]);
	    
	    //resultados[i] = poblacion.get(0).getCromosoma()[i];
	}*/
	
        //System.out.println("DIST: ");
	//System.out.println(poblacion.get(0).getDistancia());
	//System.out.println(poblacion.get(poblacion.size()-1).getDistancia());
	
	return poblacion.get(0);
    }
    
    public Individuo ejecutaGeneticoEstatico_1() {
	//System.out.println("Genetico Estatico");
	generaPoblacionInicial();
	this.evaluar(poblacion);
	
	for(int i=0; i<numGeneraciones; i++) {
	    System.out.println("Generacion: " + (i+1) + " de " + numGeneraciones);
	    System.out.print("Seleccion, ");
	    this.seleccionRuleta();
            //this.seleccionTorneoDeterminista();
	    poblacion.clear();
	    System.out.print("Cruza, ");
            //poblacion = this.cruzarConCruzaSBX();
	    poblacion = this.cruzarConCruza2Puntos();
	    System.out.println("Muta");
	    this.mutacionPorInsersion(poblacion);
	}
	
	Collections.sort(poblacion,Individuo.comparadorDistancia);

	return poblacion.get(0);
    }
    
    public Individuo ejecutaGeneticoEstatico_2() {
	//System.out.println("Genetico Estatico");
	generaPoblacionInicial();
	this.evaluar(poblacion);
	
	for(int i=0; i<numGeneraciones; i++) {
	    System.out.println("Generacion: " + (i+1) + " de " + numGeneraciones);
	    System.out.print("Seleccion, ");
	    this.seleccionRuleta();
            //this.seleccionTorneoDeterminista();
	    poblacion.clear();
	    System.out.print("Cruza, ");
            poblacion = this.cruzarConCruzaSBX();
	    //poblacion = this.cruzarConCruza2Puntos();
	    System.out.println("Muta");
	    this.mutacionPorInsersion(poblacion);
	}
	
	Collections.sort(poblacion,Individuo.comparadorDistancia);

	return poblacion.get(0);
    }
    
    public Individuo ejecutaGeneticoEstatico_3() {
	//System.out.println("Genetico Estatico");
	generaPoblacionInicial();
	this.evaluar(poblacion);
	
	for(int i=0; i<numGeneraciones; i++) {
	    System.out.println("Generacion: " + (i+1) + " de " + numGeneraciones);
	    System.out.print("Seleccion, ");
	    //this.seleccionRuleta();
            this.seleccionTorneoDeterminista();
	    poblacion.clear();
	    System.out.print("Cruza, ");
            //poblacion = this.cruzarConCruzaSBX();
	    poblacion = this.cruzarConCruza2Puntos();
	    System.out.println("Muta");
	    this.mutacionPorInsersion(poblacion);
	}
	
	Collections.sort(poblacion,Individuo.comparadorDistancia);

	return poblacion.get(0);
    }
    
    public Individuo ejecutaGeneticoEstatico_4() {
	//System.out.println("Genetico Estatico");
	generaPoblacionInicial();
	this.evaluar(poblacion);
	
	for(int i=0; i<numGeneraciones; i++) {
	    System.out.println("Generacion: " + (i+1) + " de " + numGeneraciones);
	    System.out.print("Seleccion, ");
	    //this.seleccionRuleta();
            this.seleccionTorneoDeterminista();
	    poblacion.clear();
	    System.out.print("Cruza, ");
            poblacion = this.cruzarConCruzaSBX();
	    //poblacion = this.cruzarConCruza2Puntos();
	    System.out.println("Muta");
	    this.mutacionPorInsersion(poblacion);
	}
	
	Collections.sort(poblacion,Individuo.comparadorDistancia);

	return poblacion.get(0);
    }
    
    public void generaPoblacionInicial() {
	Individuo nuevoIndividuo;
	int i = 0;
	System.out.print("generando pob inicial..");
	while (i < this.tamPoblacion) {
	    nuevoIndividuo = new Individuo(tamCromsoma);
	    nuevoIndividuo.generaAleatorio(instancia);

	    if (!esRepetido(nuevoIndividuo)) {
		System.out.print(".");
		poblacion.add(nuevoIndividuo);
		i++;
	    }
	}
	
    }
    
    public double[] seriesModeloResultadoValsT(double[] vector) {
        return this.instancia.seriesModeloResultadoValsT(vector);
    }
    
    public void seleccionTorneoDeterminista() {
        //->System.out.println("Aplicando seleccion por Torneo Determinista");
        this.nuevosPadres.clear();
        int numGenerado = 0;
        int n = this.tamPoblacion;
        //System.out.println("N: " + n);
        if(mejorIndividuoPop == null) {
            mejorIndividuoPop = poblacion.get(0); // mejor pop
        } else if(poblacion.get(0).getAptitud() > mejorIndividuoPop.getAptitud()) {
            mejorIndividuoPop = poblacion.get(0); // mejor pop
        }
        
        ArrayList<Integer> barajados = new ArrayList<Integer>();
        
        //Barajar
        int i=0;
        while(i<n) {
            numGenerado = getIntRandomNumber(0, n);
            if(!barajados.contains(numGenerado)) {
                barajados.add(numGenerado);
                i++;
            }
        }
        
        // competir
        int i1=-1;
        int i2=-1;
        while(!barajados.isEmpty()) {
            i1 = barajados.remove(0);
            i2 = barajados.remove(0);
            //System.out.println(i1 + " vs " + i2);
            if(poblacion.get(i1).getAptitud() > poblacion.get(i2).getAptitud()) {
                this.nuevosPadres.add(poblacion.get(i1));
            } else {
                this.nuevosPadres.add(poblacion.get(i2));
            }
        }
        
        //Barajar
        i=0;
        while(i<n) {
            numGenerado = getIntRandomNumber(0, n);
            if(!barajados.contains(numGenerado)) {
                //System.out.println("Add " + numGenerado);
                barajados.add(numGenerado);
                i++;
            }
        }
        
        // competir
        i1=-1;
        i2=-1;
        while(!barajados.isEmpty()) {
            i1 = barajados.remove(0);
            i2 = barajados.remove(0);
            //System.out.println(i1 + " vs " + i2);
            if(poblacion.get(i1).getAptitud() > poblacion.get(i2).getAptitud()) {
                this.nuevosPadres.add(poblacion.get(i1));
            } else {
                this.nuevosPadres.add(poblacion.get(i2));
            }
        }
        
        /*System.out.println("************************************");
	System.out.println("Poblacion: " + poblacion.size());
	System.out.println("Nuevo Padres: " + nuevosPadres.size());
	System.out.println("************************************");*/
        
        
    }
    
    public void seleccionRuleta() {
	//System.out.println("Seleccion Ruleta...");
        nuevosPadres.clear();
        
        if(mejorIndividuoPop == null) {
            mejorIndividuoPop = poblacion.get(0); // mejor pop
        } else if(poblacion.get(0).getAptitud() > mejorIndividuoPop.getAptitud()) {
            mejorIndividuoPop = poblacion.get(0); // mejor pop
        }
        
        double T = 0;
        double r = 0;
        double fTest = this.sumaAptitudPoblacion()/(double)this.tamPoblacion;
        for(Individuo ind:poblacion) {
            ind.setVe(ind.getAptitud()/fTest);
        }
        T = sumaVePoblacion();
        //
        double sumaVe;
        int xx=0;
        for(int i=0; i<poblacion.size(); i++) {
            r = getRandomNumber(0.0, T);
            sumaVe = 0;
            for(Individuo ind:poblacion) {
                sumaVe += ind.getVe();
                if(sumaVe > r) {
                    //System.out.println("POB: "+i+" R"+r+" ADD " + (xx++));
                    nuevosPadres.add(ind);
                    break;
                }
            }
        }
        //this.evaluar(nuevosPadres);
	/*System.out.println("Nuevos padres...");
	for(Individuo ind:nuevosPadres) {
	    ind.imprimeCromosoma();
	    System.out.println(ind.getDistancia());
	    System.out.println(ind.getAptitud());
	}*/
    }
    
    public ArrayList<Individuo> cruzaSBX(Individuo ind1, Individuo ind2) {
	//System.out.println("Metodo SBX...");
	int n = ind1.getCromosoma().length;
        Individuo h1 = new Individuo(n);
	Individuo h2 = new Individuo(n);
	ArrayList<Individuo> hijos = new ArrayList<Individuo>();
        
        //ni.setCromosoma(ind1.copyOfCromosoma());
	
	double nc = 1;
	double u = Math.random();
        
	double beta = 0;
	if(u <= 0.5) {
	    beta = Math.pow((2*u), (1/(nc+1)));
	} else {
	    beta = Math.pow((1/(2*(1-u))), (1/(nc+1)));
	}
        
	for(int i=0; i<n; i++) {
	    h1.getCromosoma()[i] = 0.5*((ind1.getCromosoma()[i]+ind2.getCromosoma()[i])-beta*Math.abs(ind2.getCromosoma()[i]-ind1.getCromosoma()[i]));
	    h2.getCromosoma()[i] = 0.5*((ind1.getCromosoma()[i]+ind2.getCromosoma()[i])+beta*Math.abs(ind2.getCromosoma()[i]-ind1.getCromosoma()[i]));
	}
	
	hijos.add(h1);
	hijos.add(h2);
        
        return hijos;
    }
    
    public ArrayList<Individuo> cruzarConCruzaSBX() {
	//System.out.println("Cruza con SBX...");
        //->System.out.println("Aplicando Cruza con Cruza de 2 Puntos");
        ArrayList<Individuo> lista = new ArrayList<Individuo>();
	ArrayList<Individuo> hijosSBX;
        lista.add(mejorIndividuoPop); // agrega al mejor de la generacion anterior
	padresehijos.clear();
        int c=0;
        Individuo i1=null;
        Individuo i2=null;
        Individuo ni=null;
	//System.out.println("Nuevos Padres: " + nuevosPadres.size());
	//System.out.println("Pob: " + poblacion.size());
        while(!this.nuevosPadres.isEmpty()) {
	    //System.out.println("nuevos padres != empty");
            if(c==0) {
                i1 = this.nuevosPadres.remove(0);
		padresehijos.add(i1);
                c++;
            } else if(c==1) {
                ni=null;
                i2 = this.nuevosPadres.remove(0);
		padresehijos.add(i2);
                
		//ni = this.cruza2Puntos(i1, i2);
		hijosSBX=null;
		hijosSBX = this.cruzaSBX(i1, i2);
		ni = hijosSBX.remove(0);
		
                if(this.instancia.cromosomaFactible(ni.getCromosoma())) {
                    //double value = this.instancia.getValorAptitud(ni.getCromosoma());
                    //ni.setDistancia(value);
                    //ni.setAptitud(100/value);
                    ni.evaluaIndividuo(instancia);
                    while (Double.isNaN(ni.getDistancia())) {
                        ni.generaAleatorio(instancia);
                        ni.evaluaIndividuo(instancia);
                    }
                    //System.out.println(">> Nuevo hijo distancia: " + ni.getDistancia() + " aptitud: " + ni.getAptitud());
                    lista.add(ni);
		    padresehijos.add(ni);
                } else {
                    //this.contInfactibles ++;
		    /*System.out.print("Infactible: ");
		    for(int i=0; i<ni.getTamanio(); i++)
			System.out.println(ni.getCromosoma()[i] + " ");*/
		    
                }
                
                ni=null;
		ni = hijosSBX.remove(0);
                if(this.instancia.cromosomaFactible(ni.getCromosoma())) {
                    //double value = this.instancia.getValorAptitud(ni.getCromosoma());
                    //ni.setDistancia(value);
                    //ni.setAptitud(100/value);
                    ni.evaluaIndividuo(instancia);
                    while (Double.isNaN(ni.getDistancia())) {
                        ni.generaAleatorio(instancia);
                        ni.evaluaIndividuo(instancia);
                    }
                    //System.out.println(">> Nuevo hijo distancia: " + ni.getDistancia() + " aptitud: " + ni.getAptitud());
                    lista.add(ni);
		    padresehijos.add(ni);
                } else {
                    //this.contInfactibles ++;
		    /*System.out.print("Infactible: ");
		    for(int i=0; i<ni.getTamanio(); i++)
			System.out.println(ni.getCromosoma()[i] + " ");*/
                }

                c=0;
            }
        }
        
        ////////////////////////////////////
        //Collections.sort(padresehijos,Individuo.comparadorAptitud);
        /*while(lista.size() < this.nIndividuos) {
            lista.add(this.poblacion.remove(0));
        }*/
	//this.evaluar(padresehijos);
	//this.evaluar(lista);
	//System.out.println("*** padresehijos: " + padresehijos.size());
        
	Collections.sort(padresehijos,Individuo.comparadorDistancia);
	/*System.out.println("------------ PHJ");
	for(Individuo i : padresehijos) {
	    System.out.println("Ap PHJ: " + i.getAptitud());
	}*/
	//lista.clear();
        while(lista.size() < this.tamPoblacion) {
	    if(this.padresehijos.size() > 0)
		lista.add(this.padresehijos.remove(0));
        }
        //System.out.println("Lista: " + lista.size());
        Collections.sort(lista,Individuo.comparadorDistancia);
        
        /*for(int i=0; i<lista.size(); i++) {
            System.out.println("i: " + i + " distancia: " + lista.get(i).getDistancia() + " aptitud: " + lista.get(i).getAptitud());
        }*/
        
	/*System.out.println("NPadres---------------------------");
	for(Individuo i : lista) {
	    System.out.println("Ap PHJ: " + i.getAptitud());
	}*/
	//System.out.println("Lista Cruza");
	//imprimeLista(lista);
        return lista;
    }
    
    public Individuo cruza2Puntos(Individuo ind1, Individuo ind2) {
        int n = ind1.getCromosoma().length;
        Individuo ni = new Individuo(n);
        
        ni.setCromosoma(ind1.copyOfCromosoma());
        
        ni.getCromosoma()[n/2] = ind2.getCromosoma()[n/2];
        ni.getCromosoma()[(n/2)+1] = ind2.getCromosoma()[(n/2)+1];
        return ni;
    }
    
    public ArrayList<Individuo> cruzarConCruza2Puntos() {
	//System.out.println("Cruza de 2 Puntos...");
        //->System.out.println("Aplicando Cruza con Cruza de 2 Puntos");
        ArrayList<Individuo> lista = new ArrayList<Individuo>();
        lista.add(mejorIndividuoPop); // agrega al mejor de la generacion anterior
	padresehijos.clear();
        int c=0;
        Individuo i1=null;
        Individuo i2=null;
        Individuo ni=null;
        while(!this.nuevosPadres.isEmpty()) {
            if(c==0) {
                i1 = this.nuevosPadres.remove(0);
		padresehijos.add(i1);
                c++;
            } else if(c==1) {
                ni=null;
                i2 = this.nuevosPadres.remove(0);
		padresehijos.add(i2);
                ni = this.cruza2Puntos(i1, i2);
                if(this.instancia.cromosomaFactible(ni.getCromosoma())) {
                    //ni.setAptitud(this.instancia.getValorAptitud(ni.getCromosoma()));
                    //double value = this.instancia.getValorAptitud(ni.getCromosoma());
                    //ni.setDistancia(value);
                    //ni.setAptitud(100/value);
                    ni.evaluaIndividuo(instancia);
                    while (Double.isNaN(ni.getDistancia())) {
                        ni.generaAleatorio(instancia);
                        ni.evaluaIndividuo(instancia);
                    }
                    lista.add(ni);
		    padresehijos.add(ni);
                } else {
                    //this.contInfactibles ++;
                }
                
                ni=null;
                ni = this.cruza2Puntos(i2, i1);
                if(this.instancia.cromosomaFactible(ni.getCromosoma())) {
                    //ni.setAptitud(this.instancia.getValorAptitud(ni.getCromosoma()));
                    //double value = this.instancia.getValorAptitud(ni.getCromosoma());
                    //ni.setDistancia(value);
                    //ni.setAptitud(100/value);
                    ni.evaluaIndividuo(instancia);
                    while (Double.isNaN(ni.getDistancia())) {
                        ni.generaAleatorio(instancia);
                        ni.evaluaIndividuo(instancia);
                    }
                    lista.add(ni);
		    padresehijos.add(ni);
                } else {
                    //this.contInfactibles ++;
                }

                c=0;
            }
        }
        
        ////////////////////////////////////
        /*Collections.sort(poblacion,Individuo.comparadorAptitud);
        while(lista.size() < this.nIndividuos) {
            lista.add(this.poblacion.remove(0));
        }*/
	//this.evaluar(padresehijos);
	Collections.sort(padresehijos,Individuo.comparadorDistancia);
	/*System.out.println("------------ PHJ");
	for(Individuo i : padresehijos) {
	    System.out.println("Ap PHJ: " + i.getAptitud());
	}*/
	//lista.clear();
        while(lista.size() < this.tamPoblacion) {
            if(this.padresehijos.size() > 0)
                lista.add(this.padresehijos.remove(0));
        }
        Collections.sort(lista,Individuo.comparadorDistancia);
	/*System.out.println("NPadres---------------------------");
	for(Individuo i : lista) {
	    System.out.println("Ap PHJ: " + i.getAptitud());
	}*/
        return lista;
    }
    
    public void mutacionPorInsersion(ArrayList<Individuo> pob) {
	//System.out.println("Mutacion por Insersion...");
	int nMutados = (int)(pob.size()*this.porcMutacion);
	int i=0;
	int indx=-1;
	while(i<nMutados) {
	    indx=this.getIntRandomNumber(0, pob.size());
	    pob.get(indx).mutacionPorInsersion();
	    pob.get(indx).evaluaIndividuo(this.instancia);
	    i++;
	}
        Collections.sort(pob,Individuo.comparadorDistancia);
    }
    /*public void mutacionIntercambioReciproco(ArrayList<Individuo> pob) {
        
        int nMutados = (int)(pob.size()*this.porcMuta);
        int indx=-1;
        
        int i=0;
        Individuo iAux=null;
        while(i<nMutados) {
            indx=this.getIntRandomNumber(0, pob.size());
            iAux=pob.get(indx);
            
            pob.get(indx).mutacionIntercambioReciproco(this.instancia);
            
            i++;
            
        }
    }*/
    
    private int getIntRandomNumber(int min, int max) {
        return (int)((Math.random() * (max - min)) + min);
    }
    
    private double getRandomNumber(double min, double max) {
        return  ((Math.random() * (max - min)) + min);
    }
    
    public double sumaAptitudPoblacion() {
        double suma = 0.0;
        for(Individuo ind:poblacion) {
            suma += ind.getAptitud();
        }
        return suma;
    }
    
    public double sumaVePoblacion() {
        double suma = 0.0;
        for(Individuo ind:poblacion) {
            suma += ind.getVe();
        }
        return suma;
    }
    
    public boolean esRepetido(Individuo individuo) {
        boolean esRepetido=false;
        for(Individuo ind:this.poblacion) {
            if(ind.compararIguales(individuo)) {
                esRepetido=true;
                break;
            }
        }
        return esRepetido;
    }
    
    public void evaluar(ArrayList<Individuo> lista) {
	//double suma=0.0;
	double value=0.0;
	for(Individuo ind : lista) {
	    //value = instancia.getValorAptitud(ind.getCromosoma()); 
	    //ind.setDistancia(value);
            //ind.setAptitud(100/value);
            ind.evaluaIndividuo(instancia);
	    //suma += value;
	}
	
	/*for(Individuo ind : lista) {
	    //ind.setDistancia(instancia.getValorAptitud(ind.getCromosoma()));
	    //ind.setAptitud((1-(instancia.getValorAptitud(ind.getCromosoma())/suma)));
	    ind.setAptitud((1-(ind.getDistancia()/suma)));
	}*/
	
	//ind.setAptitud(instancia.getValorAptitud(ind.getCromosoma()));
    }
   
    
}
