package vistas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controlador.LecturaEscrituraFicheros;
import operaciones.Comunidad;

public class PanelJuego extends JFrame{
	
	private Comunidad preguntaActual;
	
	public PanelJuego() {
        setTitle("Adivina la Comunidad");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear paneles
        JPanel panelJuego = new JPanel();
        panelJuego.add(new JLabel());

        JPanel panelPuntuaciones = new JPanel();
        panelPuntuaciones.add(new JLabel());

        PanelInicio panelInicio = new PanelInicio(this, panelJuego, panelPuntuaciones);
        add(panelInicio);

        
        setVisible(true);
    }

    public static void main(String[] args) {
        new PanelJuego();
    }

	//----------------------------------------------------------------------------------
	
	public void listaComunidad() {
		//ruta a fichero
		String ruta = "../comunidad.txt";
		
		//escribe las comunidades en el fichero
		LecturaEscrituraFicheros ficheros = new LecturaEscrituraFicheros();
		ficheros.enviarComuidades(ruta);
	}
	   
    public boolean respuestaPositivaJugador(String respuesta) {
    	//variables
		String ruta = "../puntuacion.txt";
    	boolean esCorrecto = false;
    	
    	//confirmamos que la respuesta del usuario sea igual a la correcta
    	if(respuesta.equalsIgnoreCase(preguntaActual.getNombre())) {
        	//leemos fichero para ver puntuacion y actualizarla
    		LecturaEscrituraFicheros le = new LecturaEscrituraFicheros();
    		le.sumarPunto(ruta);
            return true;
    	} else {
    		return false;
    	}
    }
	
}


