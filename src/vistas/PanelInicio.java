package vistas;

import javax.swing.*;

import controlador.LecturaEscrituraFicheros;
import operaciones.Jugador;

import java.awt.*;

public class PanelInicio extends JPanel {

    private JTextField nombreField;
    private JButton botonJugar;
    private JButton botonPuntuaciones;
    private JButton botonSalir;
    private JFrame ventanaPrincipal;
    
    public PanelInicio(JFrame ventanaPrincipal) {
        setLayout(new BorderLayout());
        //Creamos el título y lo ponemos en la parte superior centrado.
        JLabel titulo = new JLabel("ADIVINA LA COMUNIDAD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);
        //Creamos el panel
        JPanel centro = new JPanel(new GridLayout(5, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        //Pedimos el nombre del jugador y añadimos el campo donde lo podra escribir
        centro.add(new JLabel("Introduce tu nombre:", SwingConstants.CENTER));
        nombreField = new JTextField();
        centro.add(nombreField);
        //Añadimos los 3 botones principales
         botonJugar = new JButton("Jugar");
         botonPuntuaciones = new JButton("Ver puntuaciones");
         botonSalir = new JButton("Salir");

        centro.add(botonJugar);
        centro.add(botonPuntuaciones);
        centro.add(botonSalir);

        add(centro, BorderLayout.CENTER);

        botonJugar.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            if (nombre.length() < 3) {
                JOptionPane.showMessageDialog(this, "El nombre debe tener al menos 3 letras.");
                return;
            }
            Jugador player = new Jugador(nombre);
            guardarJugadorFichero(player);

            // Crear el PanelJuego cuando se haga clic en Jugar
            PanelJuego panelJuego = new PanelJuego(ventanaPrincipal);

            // Cambia el contenido de la ventana principal y agregar el panel de juego
            ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.add(panelJuego);
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        botonPuntuaciones.addActionListener(e -> {
            // Cambiar a panel de puntuaciones 
        	PanelPuntuacion panelPuntuaciones = new PanelPuntuacion(ventanaPrincipal);
            ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.add(panelPuntuaciones);
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        botonSalir.addActionListener(e -> System.exit(0));
    }

    public PanelInicio() {
		// TODO Auto-generated constructor stub
	}

	public String getNombreJugador() {
        return nombreField.getText();
    }
    
    //----------------------------------------
    
    //llamamos a este metodo para guardar el nombre del jugador, y la fecha y la hora a la que empezo la partida
    public void guardarJugadorFichero(Jugador player) {
		//ruta donde guardaremos los datos
    	String ruta = "../jugador.txt";
    	//llamamos al metodo que guarda los datos del jugador en un fichero
		LecturaEscrituraFicheros le = new LecturaEscrituraFicheros();
		le.guardarNombreFichero(player, ruta);
		
    }
    


    
}