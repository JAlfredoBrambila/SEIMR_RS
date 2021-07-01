/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package domain;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import support.ConexionDB;

/**
 *
 * @author J. Alfredo Brambila Hdez.
 */
public class CSVDataGet {

    //BDMadero
    //BDBogota
    private String ruta="";
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\tsim\\";
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDTam_1\\";
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDTMA\\";
    //private String ruta = "BDTMA";
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDTam_2\\";
    
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDTam_2_2\\";
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDMadero\\";
    
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDBogota\\";
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDBogotaA1\\";
    
    //private String ruta = "C:\\Users\\al_x3\\Documents\\Maestria\\OPTEX\\TMA\\BDBogotaA\\";
    
    //String DBStr;
    
    public CSVDataGet() {
	//System.out.println(""+System.getProperty("user.dir"));
	this.ruta = "" + System.getProperty("user.dir") + "\\BDTMA_1S\\";
	
    }
    
    public String getRuta() {
	return ruta;
    }
    // obtener poblacion total de la macro-region
    public int poblacionTotal() {
	int resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT PMRG FROM DATA_GEN");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt("PMRG");
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("poblacionTotal Error:  " + e);
	}
	return resultado;
    }
    
    //obtener numero de regiones
    public int getNumeroRegiones() {
	int resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT COUNT(DISTINCT COD_UBT) FROM MAE_UBT WHERE COD_UBT <> 'TMA'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getNumeroRegiones Error:  " + e);
	}
	return resultado;
    }
    
    //obtener numero de estratos
    public int getNumeroEstratos() {
	int resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT COUNT(DISTINCT COD_STE) FROM MAE_STE WHERE COD_STE <> 'UN' AND COD_STE <> 'TM'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getNumeroEstratos Error:  " + e);
	}
	return resultado;
    }
    
    //obtener numero de segmentos
    public int getNumeroSegmentos() {
	int resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT COUNT(DISTINCT COD_SDS) FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getNumeroSegmentos Error:  " + e);
	}
	return resultado;
    }
    
    // obtener numero de estados de infeccion I_i
    public int getNumeroiEstados() {
	int resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT COUNT(DISTINCT COD_STA) FROM PBIO_STA");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getNumeroiEstados Error:  " + e);
	}
	return resultado;
    }
    
    // llenar arrays con valores de paramatros por estado de infeccion
    public void cargaArraysParametros(double[] EPFX,double[] EPQX,double[] BETAB,double[] BETAQ,double[] PCFX,double[] PCQX,double[] DELXB,double[] PHIXB,double[] ETAXB) {
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM PBIO_STA");
	    
	    int i=0;
	    while(resultSet.next()) {
		EPFX[i] = resultSet.getDouble("EPFX");
		EPQX[i] = resultSet.getDouble("EPQX");
		BETAB[i] = resultSet.getDouble("BETAB");
		BETAQ[i] = resultSet.getDouble("BETAQ");
		PCFX[i] = resultSet.getDouble("PCFX");
		PCQX[i] = resultSet.getDouble("PCQX");
		DELXB[i] = resultSet.getDouble("DELXB");
		PHIXB[i] = resultSet.getDouble("PHIXB");
		ETAXB[i++] = resultSet.getDouble("ETAXB");
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("cargaArraysParametros Error:  " + e);
	}
    }
    
    public void obtenerPobRegionST(int[][] pob) {
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    String[] regiones = this.getCODRegionesSTR();
	    double pobTotal = this.poblacionTotal();
	    int segmentos = this.getNumeroSegmentos();
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		//System.out.println("SELECT COD_UBT,COD_SDS,FPOB FROM UBT_SDS WHERE COD_UBT='"+regiones[i]+"' --t: " + regiones.length);
		resultSet = stmt.executeQuery("SELECT COD_UBT,COD_SDS,FPOB FROM UBT_SDS WHERE COD_UBT='"+regiones[i]+"'");
		j=0;
		while(resultSet.next()) {
		    //System.out.println("P" + resultSet.getDouble("FPOB") + " PTOT: " + pobTotal);
		    pob[i][j++] = (int)(resultSet.getDouble("FPOB") * pobTotal);
		    if(j>=segmentos) {
			//System.out.println("J: " + j);
			j=0;
		    }
		}
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("obtenerPobRegionST Error:  " + e);
	}
    }
    
    /*public void cargaArraysParametrosPBIOGEN(double AINF, double AMAX, double AMIN, double DINF, double DMAX, double DMIN, double FAMC, double PAGK, double PAGL, double PAGQ, double DELT, double MIUN, double PRTK, double PRTL, double PRTQ, double KAPP, double PINF, double MIUUB, double PCONB, double PTRA) {
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM PBIO_GEN");
	    
	    int i=0;
	    while(resultSet.next()) {
		AINF = resultSet.getDouble(1);
		AMAX = resultSet.getDouble(2);
		AMIN = resultSet.getDouble(3);
		DINF = resultSet.getDouble(4);
		DMAX = resultSet.getDouble(5);
		DMIN = resultSet.getDouble(6);
		FAMC = resultSet.getDouble(7);
		PAGK = resultSet.getDouble(8);
		PAGL = resultSet.getDouble(9);
		PAGQ = resultSet.getDouble(10);
		DELT = resultSet.getDouble(11);
		System.out.println("---> DELT QUERY: "+DELT);
		MIUN = resultSet.getDouble(12);
		PRTK = resultSet.getDouble(13);
		PRTL = resultSet.getDouble(14);
		PRTQ = resultSet.getDouble(15);
		KAPP = resultSet.getDouble(16);
		PINF = resultSet.getDouble(17);
		MIUUB = resultSet.getDouble(18);
		PCONB = resultSet.getDouble(19);
		PTRA = resultSet.getDouble(20);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("Error:  " + e);
	}
    }*/
    
    public void cargaArraysParametrosPBIOGEN(double[] PBIOGEN ) {
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT * FROM PBIO_GEN");
	    
	    int i=0;
	    while(resultSet.next()) {
		PBIOGEN[0] = resultSet.getDouble(1);
		PBIOGEN[1] = resultSet.getDouble(2);
		PBIOGEN[2] = resultSet.getDouble(3);
		PBIOGEN[3] = resultSet.getDouble(4);
		PBIOGEN[4] = resultSet.getDouble(5);
		PBIOGEN[5] = resultSet.getDouble(6);
		PBIOGEN[6] = resultSet.getDouble(7);
		PBIOGEN[7] = resultSet.getDouble(8);
		PBIOGEN[8] = resultSet.getDouble(9);
		PBIOGEN[9] = resultSet.getDouble(10);
		PBIOGEN[10] = resultSet.getDouble(11);
		PBIOGEN[11] = resultSet.getDouble(12);
		PBIOGEN[12] = resultSet.getDouble(13);
		PBIOGEN[13] = resultSet.getDouble(14);
		PBIOGEN[14] = resultSet.getDouble(15);
		PBIOGEN[15] = resultSet.getDouble(16);
		PBIOGEN[16] = resultSet.getDouble(17);
		PBIOGEN[17] = resultSet.getDouble(18);
		PBIOGEN[18] = resultSet.getDouble(19);
		PBIOGEN[19] = resultSet.getDouble(20);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("cargaArraysParametrosPBIOGEN Error:  " + e);
	}
    }
    
    public String[] getCODRegionesSTR() {
	int nregiones = getNumeroRegiones();
	String[] regionesStr = new String[nregiones];
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT COD_UBT FROM MAE_UBT WHERE COD_UBT <> 'TMA'");
	    
	    int i=0;
	    while(resultSet.next()) {
		//System.out.println(resultSet.getString(1));
		regionesStr[i++] = resultSet.getString(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getCODRegionesSTR Error:  " + e);
	}
	return regionesStr;
    }
    
    public String[] getCODSegmentosSTR() {
	int nsegmentos = this.getNumeroSegmentos();
	String[] segmentosStr = new String[nsegmentos];
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI' ORDER BY COD_SDS");
	    
	    int i=0;
	    while(resultSet.next()) {
		//System.out.println(resultSet.getString(1));
		segmentosStr[i++] = resultSet.getString(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getCODSegmentosSTR Error:  " + e);
	}
	return segmentosStr;
    }
    
    // obtener nombres de regiones
    public String[][] getRegionesSTR() {
	int nregiones = getNumeroRegiones();
	String[][] regionesStr = new String[nregiones][2];
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT COD_UBT, DES_UBT FROM MAE_UBT WHERE COD_UBT <> 'TMA' ORDER BY COD_UBT DESC");
	    
	    int i=0;
	    while(resultSet.next()) {
		//System.out.println(resultSet.getString(1));
		regionesStr[i][0] = resultSet.getString(1);
		regionesStr[i++][1] = resultSet.getString(2);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getRegionesSTR Error:  " + e);
	}
	return regionesStr;
    }
            
    public String[][] getEstratosSTR() {
	int nestratos = getNumeroEstratos();
	String[][] estratosStr = new String[nestratos][2];
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT COD_STE, DES_STE FROM MAE_STE WHERE DES_STE <> 'Undefined' AND DES_STE <> 'TMA'");
	    
	    int i=0;
	    while(resultSet.next()) {
		//System.out.println(resultSet.getString(1));
		estratosStr[i][0] = resultSet.getString(1);
		estratosStr[i++][1] = resultSet.getString(2);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getEstratosSTR Error:  " + e);
	}
	return estratosStr;
    }
    
    public String[][] getSegmentosSTR() {
	int nsegmentos = getNumeroSegmentos();//
	String[][] segmentosStr = new String[nsegmentos][2];
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS, DES_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    int i=0;
	    while(resultSet.next()) {
		//System.out.println("SEGMENTOS: "+resultSet.getString(1));
		segmentosStr[i][0] = resultSet.getString(1);
		segmentosStr[i++][1] = resultSet.getString(2);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getSegmentosSTR Error:  " + e);
	}
	return segmentosStr;
    }
    
    public void getPoblacion(double[][] pob) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT DISTINCT COD_SDS, FPOB FROM UBT_SDS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_SDS <> 'UNI' ORDER BY COD_SDS";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " "+j+" "+resultSet.getString(1) + " " + resultSet.getString(2));
		    pob[i][j++] = resultSet.getDouble(2);
		    //j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getPoblacion Error:  " + e);
	}
    }
    
    public void getSU(double[][][] S) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'SU'";
		//System.out.println(query);
		resultSet = stmt.executeQuery(query);
		j=0;
		//System.out.println("***********************");
		//System.out.println("SUSCEPTIBLES");
		//System.out.println("***********************");
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    S[i][j][0] = resultSet.getDouble(5);
		    //System.out.println("SU["+i+","+j+",0] " + S[i][j][0]);
		    //System.out.println("REG: " + regiones[i] + " S: [" + i + ", " + j + ", 0 " + S[i][j][0]);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getSU Error:  " + e);
	}
    }
    
    public void getI0(double[][][] I0) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'I0' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    I0[i][j][0] = resultSet.getDouble(5);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getI0 Error:  " + e);
	}
    }
    
    public void getI1(double[][][] I1) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'I1' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    I1[i][j][0] = resultSet.getDouble(5);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getI1 Error:  " + e);
	}
    }
    
    public void getI2(double[][][] I2) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'I2' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    I2[i][j][0] = resultSet.getDouble(5);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getI2getI2 Error:  " + e);
	}
    }
	
    public void getI3(double[][][] I3) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'I3' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    I3[i][j][0] = resultSet.getDouble(5);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getI3 Error:  " + e);
	}
    }
    
    public void getEX(double[][][] EX) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'EX' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    EX[i][j][0] = resultSet.getDouble(5);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getEX Error:  " + e);
	}
    }
    
    public void getED(double[][][] ED) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'ED' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    ED[i][j][0] = resultSet.getDouble(5);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getED Error:  " + e);
	}
    }
    
    public void getND(double[][][] ND) {
	String[] regiones = getCODRegionesSTR();
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;
	    int nSegmentos = this.getNumeroSegmentos();
	    //resultSet = stmt.executeQuery("SELECT DISTINCT COD_SDS FROM MAE_SDS WHERE COD_SDS <> 'UNI'");
	    
	    String query="";
	    int j=0;
	    for(int i=0; i<regiones.length; i++) {
		query="SELECT * FROM POB_ST_RG_SS WHERE COD_UBT = '"+ regiones[i] +"' AND COD_STA = 'ND' ORDER BY FECHA";
		resultSet = stmt.executeQuery(query);
		j=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    ND[i][j][0] = resultSet.getDouble(5);
		    //System.out.println("ND["+i+","+j+",0] " + ND[i][j][0]);
		    j++;
		    if(j>=nSegmentos) {
			j=0;
		    }
		}
		resultSet = null;
	    }
 
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getND Error:  " + e);
	}
    }
    
    public double LAME(String rg, String ss) {
	double resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT LAME FROM UBT_SDS WHERE COD_UBT ='"+rg+"' AND COD_SDS ='"+ss+"'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("LAME Error:  " + e);
	}
	return resultado;
    }
    
    public double LAMS(String rg, String ss) {
	double resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT LAMS FROM UBT_SDS WHERE COD_UBT ='"+rg+"' AND COD_SDS ='"+ss+"'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("LAMS Error:  " + e);
	}
	return resultado;
    }
    
    public double LAMI(String rg, String ss) {
	double resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT LAMI FROM UBT_SDS WHERE COD_UBT ='"+rg+"' AND COD_SDS ='"+ss+"'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("LAMI Error:  " + e);
	}
	return resultado;
    }
    
    public double LAMR(String rg, String ss) {
	double resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT LAMR FROM UBT_SDS WHERE COD_UBT ='"+rg+"' AND COD_SDS ='"+ss+"'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("LAMR Error:  " + e);
	}
	return resultado;
    }
    
    public int getIndexVector(String[] v, String p) {
	int valor = -1;
	for(int i=0; i<v.length; i++) {
	    if(v[i].equals(p)) {
		valor = i;
		break;
	    }
	}
	return valor;
    }
    
    public void getFPRR_FTRR(double[][][] FPRR, double[][][] FTRR) {
	
	String[] regiones = getCODRegionesSTR();
	String[] segmentos = getCODSegmentosSTR();
        //System.out.println("Reg + Seg: " + regiones.length + " " + segmentos.length);
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet;// = stmt.executeQuery("SELECT FPRR FROM UBT_UBT_SDS WHERE COD_UBTO ='"+rgo+"' AND COD_UBT ='"+rgd+"' AND COD_SDS ='"+ss+"'");
	    
	    String query="";
	    int rd=0;
	    int ss=0;
	    for(int ro=0; ro<regiones.length; ro++) {
		query="SELECT COD_UBTO, COD_UBT, COD_SDS, FPRR, FTRR FROM UBT_UBT_SDS WHERE COD_UBTO = '"+ regiones[ro] +"' ";
		resultSet = stmt.executeQuery(query);
		rd=0;
		ss=0;
		while(resultSet.next()) {
		    //System.out.println(i + " " + j+" "+resultSet.getString(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3) + " " + resultSet.getString(4) + " " + resultSet.getString(5));
		    //System.out.println("RD" + rd);
		    //System.out.println("SS" + ss);
		    
		    FPRR[ro][rd][ss] = resultSet.getDouble(4);
		    FTRR[ro][rd][ss] = resultSet.getDouble(5);
		    //System.out.println("["+ro+","+rd+","+ss+"]" + FPRR[ro][rd][ss] + "  **  "+"["+ro+","+rd+","+ss+"]" + FTRR[ro][rd][ss]);
		    //j++;
		}
		resultSet = null;
	    }
	    
	    conn.close();
	} catch(Exception e) {
	    System.out.println("getFPRR_FTRR Error:  " + e);
	}
	
    }
    
    public double FPRR(String rgo,String rgd, String ss) {
	double resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT FPRR FROM UBT_UBT_SDS WHERE COD_UBTO ='"+rgo+"' AND COD_UBT ='"+rgd+"' AND COD_SDS ='"+ss+"'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("FPRR Error:  " + e);
	}
	return resultado;
    }
    
    public double FTRR(String rgo,String rgd, String ss) {
	double resultado = 0;
	try {
	    ConexionDB conDB = new ConexionDB();
	    Connection conn = conDB.getConexion(ruta);
	    Statement stmt = conn.createStatement();
	    ResultSet resultSet = stmt.executeQuery("SELECT FTRR FROM UBT_UBT_SDS WHERE COD_UBTO ='"+rgo+"' AND COD_UBT ='"+rgd+"' AND COD_SDS ='"+ss+"'");
	    
	    if(resultSet.next()) {
		resultado = resultSet.getInt(1);
	    }
	    conn.close();
	} catch(Exception e) {
	    System.out.println("FTRR Error:  " + e);
	}
	return resultado;
    }
}
