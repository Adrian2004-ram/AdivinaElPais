package controlador;

import operaciones.PaisDAO;

public class LecturaEscrituraFicheros {

	public String escribirInfo(String infoEnviar) {
		
		return "nada";
		
	}
	
	public String enviarInfo(String nombreFichero) {
		
		return "nada";
		
	}
	
	public String enviarPais(String nombrefichero) {
		String resultado="Pais almacenado correctamente";
		PaisDAO paDao=new PaisDAO();
		resultado = paDao.paisSacado(nombrefichero);
		return resultado;
	}
	
}
