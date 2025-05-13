package vistas;

import javax.swing.*;
import java.awt.*;

public class PanelInicio extends JPanel {

    private JTextField nombreField;
    private JButton jugarButton;
    private JButton puntuacionesButton;
    private JButton salirButton;

    public PanelInicio(JFrame ventanaPrincipal, JPanel panelJuego, JPanel panelPuntuaciones) {
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

           
            ventanaPrincipal.getContentPane().removeAll();
            ventanaPrincipal.add(panelJuego);
            ventanaPrincipal.revalidate();
            ventanaPrincipal.repaint();
        });

        puntuacionesButton.addActionListener(e -> {
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
}
