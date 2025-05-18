package vistas;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import controlador.LecturaEscrituraFicheros;
import operaciones.Comunidad;
import operaciones.Jugador;
import operaciones.JugadorDAO;

public class PanelJuego extends JPanel{
	
	//variables
    private JTextField campoIntento;
    private JButton botonComprobar;
    private JLabel labelPregunta;
    private JPanel panelImagenes;
    
    private Jugador jugador;
    private Comunidad preguntaActual;
    private String imagenBandera;
    private String imagenComida;
    private String imagenLugar;
    private int intentosRealizados = 0;
    private final int limite_intentos = 18;
	
	public PanelJuego(JFrame ventanaPrincipal) {
        setLayout(new BorderLayout());

        // Título del panel de juego
        LecturaEscrituraFicheros lec = new LecturaEscrituraFicheros();
        lec.restaurarPuntuacionFichero("../puntuacion.txt", 0);
        JLabel titulo = new JLabel("Adivina la Comunidad", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Panel para mostrar la pregunta
        labelPregunta = new JLabel("¿Cuál es la comunidad asociada a estas imágenes?", SwingConstants.CENTER);
        labelPregunta.setFont(new Font("Arial", Font.PLAIN, 18));
        add(labelPregunta, BorderLayout.CENTER);

        // Panel con las imágenes de la comunidad (bandera, comida, lugar)
        panelImagenes = new JPanel(new GridLayout(1, 3, 10, 10));
        add(panelImagenes, BorderLayout.WEST);
        
        
        // Panel con cuadro de texto y botón
        JPanel ingresarRespuesta = new JPanel();
        ingresarRespuesta.setLayout(new BoxLayout(ingresarRespuesta, BoxLayout.Y_AXIS));

        campoIntento = new JTextField(20);
        botonComprobar = new JButton("Comprobar");

        ingresarRespuesta.add(new JLabel("Escribe el nombre de la comunidad:"));
        ingresarRespuesta.add(campoIntento);
        ingresarRespuesta.add(botonComprobar);
        // Cargamos imágenes de la comunidad
        listaComunidad();
        //Cargamos la primera pregunta al empezar
        cargarImagenesComunidad();

        // Añadimos el panel de entrada
        add(ingresarRespuesta, BorderLayout.SOUTH);

        // Añadimos el ActionListener del boton de comprobacion
        botonComprobar.addActionListener(e -> {
        	// Si nos pasamos de los intentos mostramos puntuacion por pantalla 
        	  if (intentosRealizados >= limite_intentos) {
        	        JOptionPane.showMessageDialog(this, "Fin del Juego, tu puntuacion es: " + mostrarPuntuacion());
        	        sacarDatosySumarPunutacion();        	        
        	        ventanaPrincipal.getContentPane().removeAll();
        	        ventanaPrincipal.add(new PanelInicio(ventanaPrincipal));
        	        ventanaPrincipal.revalidate(); 
        	        ventanaPrincipal.repaint();
        	        return;
        	    }
            String respuestaJugador = campoIntento.getText().trim();
            boolean esCorrecta = respuestaPositivaJugador(respuestaJugador);

            if (esCorrecta) {
                JOptionPane.showMessageDialog(this, "¡Respuesta correcta!");
            } else {
                JOptionPane.showMessageDialog(this, "Respuesta incorrecta");
            }
            intentosRealizados++;
            cargarImagenesComunidad(); 
            campoIntento.setText(""); 
        });  
        
    }
	// Método que utilizamos para cargar las imágenes desde el metodo preguntaActual
    private void cargarImagenesComunidad() {
        
		LecturaEscrituraFicheros lectura = new LecturaEscrituraFicheros();
    	preguntaActual= lectura.preguntaActual();
    	imagenBandera = preguntaActual.getImgBandera();
    	imagenComida= preguntaActual.getImgComida();
    	imagenLugar = preguntaActual.getImgLugar();
    // Se avisará por consola tanto si se han cargado como si ha habido algún error
        try {
            if (new File(imagenBandera).exists()) {
                System.out.println("Imagen bandera cargada correctamente.");
            } else {
                System.out.println("Error: La imagen de la bandera no se encuentra.");
            }

            if (new File(imagenComida).exists()) {
                System.out.println("Imagen comida cargada correctamente.");
            } else {
                System.out.println("Error: La imagen de la comida no se encuentra.");
            }

            if (new File(imagenLugar).exists()) {
                System.out.println("Imagen lugar cargada correctamente.");
            } else {
                System.out.println("Error: La imagen del lugar no se encuentra.");
            }

            // Limpiamos el panel de imágenes y se agregan las nuevas imágenes
            panelImagenes.removeAll();
            panelImagenes.add(crearLabelConImagen(imagenBandera));
            panelImagenes.add(crearLabelConImagen(imagenComida));
            panelImagenes.add(crearLabelConImagen(imagenLugar));
            panelImagenes.revalidate();
            panelImagenes.repaint();

            
        }catch (Exception e) {
            e.printStackTrace();
        }
    }	

    private JLabel crearLabelConImagen(String ruta) {
        // Cargar la imagen y redimensionarla para que todas tengan las mismas dimensiones
        ImageIcon icono = new ImageIcon(ruta);
        Image imagen = icono.getImage().getScaledInstance(200, 150, Image.SCALE_SMOOTH);
        return new JLabel(new ImageIcon(imagen));
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
    
    public int mostrarPuntuacion() {
        String ruta = "../puntuacion.txt";

        LecturaEscrituraFicheros le = new LecturaEscrituraFicheros();
        int punutacion = le.sacarPuntuacion(ruta);

        //añadimos otra vez la punutacion
        le.restaurarPuntuacionFichero(ruta, punutacion);

        return punutacion;
    }
    public void sacarDatosySumarPunutacion() {

        LecturaEscrituraFicheros le = new LecturaEscrituraFicheros();

        //sacamos la punutacion del fichero
        String rutaPuntuacion = "../puntuacion.txt";
        int punutacion = le.sacarPuntuacion(rutaPuntuacion);


        //sacamos el nombre, fecha y horta del fichero
        String rutaJugador = "../jugador.txt";
        String datos[] = le.sacarjugadorFichero(rutaJugador);

        //insertamos en la base de datos al jugador

        // Parseo de fecha y hora
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy"); // adapta el formato si es distinto
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");      // adapta si tu hora es con segundos

        String nombre = datos[0];
        LocalDate fecha = LocalDate.parse(datos[1], dateFormatter);
        LocalTime hora = LocalTime.parse(datos[2], timeFormatter);

        JugadorDAO jDAO = new JugadorDAO();
        Jugador player = new Jugador(nombre, punutacion, fecha, hora);

        jDAO.insertJugador(player);

    }
    
    //----------------------------
    

    public static void main(String[] args) {
        // Crear el JFrame y añadir el panel de inicio
        JFrame ventanaPrincipal = new JFrame("Adivina la Comunidad");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(600, 400);

        // Crear el panel de inicio
        PanelInicio panelInicio = new PanelInicio(ventanaPrincipal);

        // Añadir el panel de inicio a la ventana principal
        ventanaPrincipal.add(panelInicio);

        // Hacer visible la ventana
        ventanaPrincipal.setVisible(true);
    }
	
}