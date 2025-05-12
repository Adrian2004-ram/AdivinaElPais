package vistas;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import java.awt.FlowLayout;
import java.awt.event.*;



public class PanelInicio {

    public static void main(String[] args) {

        JFrame ventana = new JFrame("Adivina la Comunidad");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(400, 200);
        ventana.setLayout(new FlowLayout()); 


        JLabel label = new JLabel("Ingresa tu nombre(minimo 3 letras):");
        ventana.add(label); 


        JTextField nombreField = new JTextField(20);
        ventana.add(nombreField);

        JButton comenzarButton = new JButton("Comenzar");
        ventana.add(comenzarButton);


        comenzarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombre = nombreField.getText();
                if (nombre.isEmpty()) {
                    JOptionPane.showMessageDialog(ventana, "Por favor, ingresa tu nombre.");
                } else if (nombre.length() < 3) {
                    JOptionPane.showMessageDialog(ventana, "El nombre debe tener al menos 3 letras.");
                } else {

                    JOptionPane.showMessageDialog(ventana, "¡Hola, " + nombre + "! Comienza el juego.");
                    ventana.dispose(); 
                }
            }
        });

        ventana.setVisible(true);
    }
}