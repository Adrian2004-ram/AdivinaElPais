package vistas;

import javax.swing.*;

import controlador.LecturaEscrituraFicheros;
import operaciones.Jugador;

import java.awt.*;

public class PanelInicio extends JPanel {

    private JTextField nombreField;
    private JButton jugarButton;
    private JButton puntuacionesButton;
    private JButton salirButton;

    public PanelInicio(JFrame ventanaPrincipal) {
        setLayout(new BorderLayout());

        JLabel titulo = new JLabel("ADIVINA LA COMUNIDAD", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        JPanel centro = new JPanel(new GridLayout(5, 1, 10, 10));
        centro.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));

        centro.add(new JLabel("Introduce tu nombre:", SwingConstants.CENTER));

        nombreField = new JTextField();
        centro.add(nombreField);

        jugarButton = new JButton("Jugar");
        puntuacionesButton = new JButton("Ver puntuaciones");
        salirButton = new JButton("Salir");

        centro.add(jugarButton);
        centro.add(puntuacionesButton);
        centro.add(salirButton);

        add(centro, BorderLayout.CENTER);

        jugarButton.addActionListener(e -> {
            String nombre = nombreField.getText().trim();
            if (nombre.length() < 3) {
                JOptionPane.showMessageDialog(this, "El nombre debe tener al menos 3 letras.");
                return;
            }

            // Crear el PanelJuego cuando se haga clic en "Jugar"
            PanelJuego panelJuego = new PanelJuego(ventanaPrincipal);

            // Cambiar el contenido de la ventana principal y agregar el panel de juego
            ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.add(panelJuego);
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        puntuacionesButton.addActionListener(e -> {
            // Cambiar a panel de puntuaciones (en caso de que lo tengas)
            JPanel panelPuntuaciones = new JPanel(); // Aquí deberías tener el panel de puntuaciones
            ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.add(panelPuntuaciones);
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        salirButton.addActionListener(e -> System.exit(0));
    }

    public String getNombreJugador() {
        return nombreField.getText();
    }
    
    //----------------------------------------
    
    //llamamos a este metodo paraq guardar el nombre del jugador, y la fecha y la hora a la que empezo la partida
    public void guardarJugadorFichero(Jugador player) {
		//ruta donde guardaremos los daots
    	String ruta = "../jugador.txt";
    	//llamamos al metodo que guarda los datos del jugador en un fichero
		LecturaEscrituraFicheros le = new LecturaEscrituraFicheros();
		le.guardarNombreFichero(player, ruta);
		
    }
    
    //sacamos los datos del jugador e insertamos en la base de datos
    public void sacarDatosySumarPunutacion() {
    	
		LecturaEscrituraFicheros le = new LecturaEscrituraFicheros();
		
		//sacamos la punutacion del fichero
		String rutaPuntuacion = "../punutacion.txt";
    	int punutacion = le.sacarPuntuacion(rutaPuntuacion);
    	
    	
    	//sacamos el nombre, fecha y horta del fichero
    	String rutaJugador = "../jugador.txt";
    	String datos[] = le.sacarjugadorFichero(rutaJugador);
    	
    	//insertamos en la base de datos al jugador
    	
    	/*
    	 * Ho haemos que los datos del jugador se inserten en la propia llamada 
    	 * a este metoso, o traemos el jugador de un fichero
    	 * */
	
    }

    
}















