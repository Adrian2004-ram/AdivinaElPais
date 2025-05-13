package vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import controlador.LecturaEscrituraFicheros;
import operaciones.Comunidad;

public class PanelJuego {
	
	private Comunidad preguntaActual;
	
	public void main(String[] args) {
		
		respuestaJugador("Adrian");
		
		
	}
	
	
	public void listaComunidad() {
		//ruta a fichero
		String ruta = "../comunidad.txt";
		
		//escribe las comunidades en el fichero
		LecturaEscrituraFicheros ficheros = new LecturaEscrituraFicheros();
		ficheros.enviarComuidades(ruta);
	}
	
    public void preguntaActual() {
		//ruta a fichero
		String ruta = "../comunidad.txt";
		
		//leemos las lineas del fichero
        String[] datos = new String[4];
        ArrayList<String> todasLasLineas = new ArrayList<>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
        	String linea;
        	int cont = 0;
            while ((linea = reader.readLine()) != null) {
                if (cont < 4) {
                    datos[cont] = linea;
                    cont++;
                } else {
                    todasLasLineas.add(linea);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	    
        //creamos clase comunidad
        preguntaActual = new Comunidad(datos[0], datos[1], datos[2], datos[3]);
        
        //eliminamos la info del fichero
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (int i = 0; i < todasLasLineas.size(); i++) {
                writer.write(todasLasLineas.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
        
	}
    
    public void respuestaJugador(String respuesta) {
    	//variables
		String ruta = "../puntuacion.txt";
    	boolean esCorrecto = false;
    	int puntuacion = 0;
    	//confirmamos que la respuesta del usuario sea igual a la correcta
    	//if(respuesta.equalsIgnoreCase(preguntaActual.getNombre())) {
    	if(respuesta.equalsIgnoreCase("Adrian")) {
    		esCorrecto = true;
    	}
    	//leemos fichero para ver puntuacion y actualizarla
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
        	String linea = reader.readLine();
        	if(linea != null) {
        		puntuacion = Integer.parseInt(linea);
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reescribimos el fichero
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(puntuacion);
        } catch (IOException e) {
            e.printStackTrace();
        }
	
    }
	
}


