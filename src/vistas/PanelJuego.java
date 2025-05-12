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
	
	
	public void listaComunidad() {
		//ruta a fichero
		String ruta = "../comunidad.txt";
		
		//escribe las comunidades en el fichero
		LecturaEscrituraFicheros ficheros = new LecturaEscrituraFicheros();
		ficheros.enviarCouidades(ruta);
		
		//leemos las lineas del fichero
        String[] datos = new String[4];
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
            int cont = 0;
            while ((datos[cont] = reader.readLine()) != null && cont<4) {
                cont++;
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
	
}


