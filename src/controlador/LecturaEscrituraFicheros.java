package controlador;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import operaciones.Comunidad;
import operaciones.ComunidadDAO;
import operaciones.Jugador;
import operaciones.JugadorDAO;

public class LecturaEscrituraFicheros {
	
	public LecturaEscrituraFicheros () {}
	

	
	//METODOS COMUNDAD
	
	//Guarde en un fichero las comunidades que saldran en un ronda
	public void enviarComuidades(String nombreFichero) {
		ComunidadDAO cd = new ComunidadDAO();
		//llama a metodo de base de datos
		String comunidades = cd.comunidadesSacadas();
		//separa en arrays
		try {
			BufferedWriter bw=new BufferedWriter(new FileWriter(nombreFichero));
			if(comunidades!=null) {
				//substring quita el ultimo "|" del string mandado por base de datos
				comunidades = comunidades.substring(0, comunidades.length()-1);
				String[] split = comunidades.split("\\|");
				if(split.length>0) {
					for(int i=0;i<split.length;i++) {
						bw.write(split[i]);
						bw.newLine();
					}
					bw.close();
				}else {
					comunidades="Envio de información erróneo";
				}
				
			}
		} catch (IOException e) {
			System.out.println("Error al escribir el fichero");
			comunidades = "";
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
    
    //METODOS ACTUALIZAR Y SACAR PUNTUACION DE FICHERO
    
	//Sumar puntuacon en fichero
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
    public void restaurarPuntuacionFichero(String ruta, int puntuacion) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ruta))) {
            writer.write(String.valueOf(puntuacion)); 
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //METODOS JUGADOR
 
    public void guardarNombreFichero(Jugador player, String ruta) {
        JugadorDAO jd = new JugadorDAO();
        //separa en arrays
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(ruta));
            if (player != null) {
                JOptionPane.showMessageDialog(null,
                    "Nombre: " + player.getNombre() +
                    "\nFecha: " + player.fechaFormateada() +
                    "\nHora: " + player.horaFormateada(),
                    "Datos del jugador", JOptionPane.INFORMATION_MESSAGE);

                bw.write(player.getNombre());
                bw.newLine();
                bw.write(player.fechaFormateada());
                bw.newLine();
                bw.write(player.horaFormateada());
                bw.newLine();
            } else {
                JOptionPane.showMessageDialog(null,
                    "Envío de información erróneo, vuelva a intentarlo",
                     "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
            bw.close();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                "Error al escribir el fichero:\n" + e.getMessage(),
                "Error de escritura", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public String[] sacarjugadorFichero(String ruta) {
    	
		JugadorDAO jd = new JugadorDAO();
		String datosConcatenados[] = new String[3];
		
		//leemos el fichero
		 try (BufferedReader reader = new BufferedReader(new FileReader(ruta))) {
        	String name = reader.readLine();
        	String date = reader.readLine();
        	String hour = reader.readLine();
        	reader.close();
            
        	datosConcatenados[0] = name;
        	datosConcatenados[1] = date;
        	datosConcatenados[2] = hour;
        	
        } catch (IOException e) {
            e.printStackTrace();
        }
		 
		 
		 
		 return datosConcatenados;
    }
    
}