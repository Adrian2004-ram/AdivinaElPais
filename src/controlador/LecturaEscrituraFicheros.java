package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import operaciones.Comunidad;
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
	public void enviarComuidades(String nombreFichero) {
		ComunidadDAO cd = new ComunidadDAO();
		//llama a metodo de base de datos
		String comunidades = cd.comudadesSacadas();
		//separa en arrays
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(nombreFichero));
			if(comunidades!=null) {
				//substring quita el ultimp "|" del string mandado por bas4e de datos
				comunidades = comunidades.substring(0, comunidades.length()-1);//Quito la última coma
				String[] split = comunidades.split("\\|");
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

	//respuesta jugador comprobar y sumar puntuacon en fichero
	
	public void sumarPunto(String ruta) {
		int puntuacion = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
        	String linea = reader.readLine();
        	if (linea != null && !linea.trim().isEmpty()) {
        	    puntuacion = Integer.parseInt(linea.trim());
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        //reescribimos el fichero
        puntuacion++;
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
        	writer.write(String.valueOf(puntuacion)); 
        } catch (IOException e) {
            e.printStackTrace();
        }   
	}
	//reinicia la pregunta a la nueva comunidad
	
    public Comunidad preguntaActual() {
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
        Comunidad preguntaActual = new Comunidad(datos[0], datos[1], datos[2], datos[3]);
        
        //eliminamos la info del fichero
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            for (int i = 0; i < todasLasLineas.size(); i++) {
                writer.write(todasLasLineas.get(i));
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return preguntaActual;
 
        
	}
    
    public int sacarPuntuacion(String ruta) {
		int puntuacion = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
        	String linea = reader.readLine();
        	if (linea != null && !linea.trim().isEmpty()) {
        	    puntuacion = Integer.parseInt(linea.trim());
        	}
        } catch (IOException e) {
            e.printStackTrace();
        }
        return puntuacion;
    }
 
}
