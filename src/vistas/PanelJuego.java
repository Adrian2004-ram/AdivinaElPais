package vistas;

import java.awt.*;
import javax.swing.*;
import java.io.File;
import controlador.LecturaEscrituraFicheros;
import operaciones.Comunidad;

public class PanelJuego extends JFrame{
	
	//variables
    private JTextField respuestaField;
    private JButton comprobarButton;
    private JLabel preguntaLabel;
    private JPanel imagenesPanel;
    
    
    private Comunidad preguntaActual;
    private String imagenBandera;
    private String imagenComida;
    private String imagenLugar;
    private int intentosRealizados = 0;
    private final int LIMITE_INTENTOS = 18;
	
	public PanelJuego(JFrame ventanaPrincipal) {
        setLayout(new BorderLayout());

        // Título del panel de juego
        JLabel titulo = new JLabel("Adivina la Comunidad", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        add(titulo, BorderLayout.NORTH);

        // Panel para mostrar la pregunta
        preguntaLabel = new JLabel("¿Cuál es la comunidad asociada a estas imágenes?", SwingConstants.CENTER);
        preguntaLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        add(preguntaLabel, BorderLayout.CENTER);

        // Panel con las imágenes de la comunidad (bandera, comida, lugar)
        imagenesPanel = new JPanel(new GridLayout(1, 3, 10, 10));
        add(imagenesPanel, BorderLayout.WEST);
        
        
        // Panel con cuadro de texto y botón
        JPanel ingresarRespuesta = new JPanel();
        ingresarRespuesta.setLayout(new BoxLayout(ingresarRespuesta, BoxLayout.Y_AXIS));

        respuestaField = new JTextField(20);
        comprobarButton = new JButton("Comprobar");

        ingresarRespuesta.add(new JLabel("Escribe el nombre de la comunidad:"));
        ingresarRespuesta.add(respuestaField);
        ingresarRespuesta.add(comprobarButton);
        // Cargar imágenes de la comunidad
        listaComunidad();
     // Cargar la primera pregunta al empezar
        cargarImagenesComunidad();

        // Añadir el panel de entrada una sola vez
        add(ingresarRespuesta, BorderLayout.SOUTH);

        // Añadir el ActionListener una sola vez
        comprobarButton.addActionListener(e -> {
        	  if (intentosRealizados >= LIMITE_INTENTOS) {
        	        JOptionPane.showMessageDialog(this, "Fin del Juego, tu puntuacion es: " + mostrarPuntuacion());
        	        comprobarButton.setEnabled(false);
        	        ingresarRespuesta.setEnabled(false);
        	        return;
        	    }
            String respuestaJugador = respuestaField.getText().trim();
            boolean esCorrecta = respuestaPositivaJugador(respuestaJugador);

            if (esCorrecta) {
                JOptionPane.showMessageDialog(this, "¡Respuesta correcta!");
            } else {
                JOptionPane.showMessageDialog(this, "Respuesta incorrecta");
            }
            intentosRealizados++;
            cargarImagenesComunidad(); // Siguiente pregunta
            respuestaField.setText(""); // Limpiar campo
        });      	
    }
	
    private void cargarImagenesComunidad() {
        
		LecturaEscrituraFicheros lectura = new LecturaEscrituraFicheros();
    	preguntaActual= lectura.preguntaActual();
    	imagenBandera = preguntaActual.getImgBandera();
    	imagenComida= preguntaActual.getImgComida();
    	imagenLugar = preguntaActual.getImgLugar();
    
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

            // Limpiar el panel de imágenes y agregar las nuevas imágenes
            imagenesPanel.removeAll();
            imagenesPanel.add(crearLabelConImagen(imagenBandera));
            imagenesPanel.add(crearLabelConImagen(imagenComida));
            imagenesPanel.add(crearLabelConImagen(imagenLugar));
            imagenesPanel.revalidate();
            imagenesPanel.repaint();

            respuestaPositivaJugador(respuestaField.getText());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }	

    private JLabel crearLabelConImagen(String ruta) {
        // Cargar la imagen y redimensionarla
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
    	
    	return punutacion;
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


