package vistas;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;

import controlador.LecturaEscrituraFicheros;
import operaciones.Jugador;
import operaciones.JugadorDAO;

public class PanelPuntuacion extends JPanel {
	//Creamos la tabla para mostrar las puntuaciones.
    private JTable tabla;
    //Tambien la ventanaPrincipal para poder volver a ella
    private JFrame ventanaPrincipal;

    
    //Constructor
    public PanelPuntuacion(JFrame ventanaPrincipal) {
        this.ventanaPrincipal = ventanaPrincipal;

        
        //Añadimos el titulo
        JLabel titulo = new JLabel("Historial de puntuaciones", SwingConstants.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 24));
        titulo.setBounds(0, 50, 600, 30);
        add(titulo);
        //Creamos las columnas y cargamos los Datos.
        String[] columnas = {"Nombre", "Puntuación", "Fecha", "Hora"};
        Object[][] datos = cargarDatosJugadores(columnas);
        //Con los datos cargados creamos una tabla
        JTable tabla = new JTable(datos, columnas);
        
        //Creamos el Jscroll para que si se sale de los limites se pueda deslizar hacia abajo y ver mas 
        //Puntuaciones.
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBounds(50, 90, 500, 250);
        add(scroll);

        //Creamos el boton Volver para que se pueda ir a la pantalla principal.
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(10, 10, 80, 30);
        add(btnVolver);

        btnVolver.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ventanaPrincipal.getContentPane().removeAll();
                ventanaPrincipal.add(new PanelInicio(ventanaPrincipal));
                ventanaPrincipal.revalidate();
                ventanaPrincipal.repaint();
            }
        });
    }
    //Creamos el método que cargará los datos en la tabla de puntuaciones.
    private Object[][] cargarDatosJugadores(String[] columnas) {
        ArrayList<Jugador> jugadores = mostrarHistorial();
        Object[][] datos = new Object[jugadores.size()][4];

        for (int i = 0; i < jugadores.size(); i++) {
            Jugador j = jugadores.get(i);
            datos[i][0] = j.getNombre();
            datos[i][1] = j.getPuntuacionPartida();
            datos[i][2] = j.getFecha();
            datos[i][3] = j.getHora();
        }
        return datos;
    }
    //Este método obtiene del Dao las puntuaciones.
    public ArrayList<Jugador> mostrarHistorial() {
        JugadorDAO jDAO = new JugadorDAO();

        String infoConcatenada = jDAO.logPuntuaciones();
        //Quitamos el ultimo separador
        infoConcatenada = infoConcatenada.substring(0, infoConcatenada.length() - 1);

        String[] split = infoConcatenada.split("\\|");
        //Comprobamos que los datos estén con el formato correcto(Multiplo de 4, ya que deben ser 4 datos por comunidad)
        if (split.length % 4 != 0) {
            System.out.println("Formato de datos incorrecto.");
        }

        ArrayList<Jugador> listJugadores = new ArrayList<>();
        //Recorremos el array cada 4 elementos.
        for (int i = 0; i < split.length; i += 4) {
            String nombre = split[i].trim();
            int puntuacion = Integer.parseInt(split[i + 1].trim());
            LocalDate fecha = LocalDate.parse(split[i + 2].trim());
            LocalTime hora = LocalTime.parse(split[i + 3].trim());

            Jugador jugador = new Jugador(nombre, puntuacion, fecha, hora);
            listJugadores.add(jugador);
        }

        return listJugadores;

    }
}
