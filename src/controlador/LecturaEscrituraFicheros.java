package controlador;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import operaciones.JugadorDAO;
import operaciones.PaisDAO;

public class LecturaEscrituraFicheros {

	public String escribirJugs(String infoEnviar) {
		//------------------------------------------------------------
		/*
		 * MODIFICAE
		 */
		//------------------------------------------------------------
		String resultado="Adivina la comunidad";
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter("infoPedido.txt"));
			if(infoEnviar!=null) {
				infoEnviar=	infoEnviar.substring(0, infoEnviar.length()-1);//Quito la última coma
				String[] split = infoEnviar.split(",");
				if(split.length>0) {
					for(int i=0;i<split.length;i++) {
						bw.write(split[i]);
						bw.newLine();
					}
					bw.close();
					//Una vez escrito el fichero se envía a la parte de servidor para almacenar la información en BBDD
					JugadorDAO jd=new JugadorDAO();
				jd.meterUsuario("infoPedido.txt");
				}else {
					resultado="Envio de información erróneo,vuelvalo a intentar";
				}
				
			}
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero");
			resultado="No se ha podido enviar la información";
		}
		
		return resultado;
		
	}
	
	public String enviarJuga(String nombreFichero) {
		
		return "nada";
		
	}
	
	public String enviarPais(String nombreFichero) {

		String resultado="ADIVINA LA COMUNIDAD";
		PaisDAO psDao=new PaisDAO();
		resultado = psDao.paisSacado(nombreFichero);
		return resultado;
	
	}	
	
}
