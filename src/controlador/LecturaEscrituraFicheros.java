package controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import operaciones.ComunidadDAO;
import operaciones.JugadorDAO;

public class LecturaEscrituraFicheros {
	
	public LecturaEscrituraFicheros () {}
	
	//Jugador
	public void puntuacinJug(String fichero) {
		
		
	}
	
	public void creaJugador(String nombre) {		
		//cojes el nombre escrito por jugador
		JugadorDAO jd = new JugadorDAO();
		//llamas al metodo de la base de datos para meterlo
		jd.insertJugador(nombre);
	}
	
	//Pais
	public void enviarCouidades(String nombreFichero) {
		ComunidadDAO cd = new ComunidadDAO();
		//llama a metodo de base de datos
		String comunidades = cd.comudadesSacadas();
		//separa en arrays
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(nombreFichero));
			if(comunidades!=null) {
				//substring quita el ultimp "|" del string mandado por bas4e de datos
				comunidades = comunidades.substring(0, comunidades.length()-1);//Quito la última coma
				String[] split = comunidades.split("|");
				if(split.length>0) {
					for(int i=0;i<split.length;i++) {
						bw.write(split[i]);
						bw.newLine();
					}
					bw.close();
				}else {
					comunidades="Envio de información erróneo,vuelvalo a intentar";
				}
				
			}
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero");
			comunidades = "";
		}
	}	
	
}
