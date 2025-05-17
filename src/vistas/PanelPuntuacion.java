package vistas;

import controlador.LecturaEscrituraFicheros;
import operaciones.JugadorDAO;

public class PanelPuntuacion {

	
	
	public void mostrarHistorial() {
		
		JugadorDAO jDAO = new JugadorDAO();
		
		
		String infoConcatenada = jDAO.logPunutacoines();
		
		//substring quita el ultimp "|" del string mandado por base de datos
		infoConcatenada = infoConcatenada.substring(0, infoConcatenada.length()-1);//Quito la última coma
		String[] split = infoConcatenada.split("\\|");
		
		//cada cuatro huecos seria un jugador con su info
		// | nombre | punutacion | fecha | hora |
		
		/*
		 * Falta terminar el metos y ver como devolver los daots
		 * para poder imprimirlos por pantalla
		 * */
	}
	
}
