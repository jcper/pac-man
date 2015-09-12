package es.practicapoo;
import java.awt.Color;
import javax.swing.*;

import java.awt.EventQueue;

public class Ventana extends JFrame{
    
	public Ventana(){
	add(new Tablero());
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setSize(800,800);
	setLocationRelativeTo(null);
	setTitle("pac-man");
	setVisible(true);
	
	
	getContentPane().setBackground(Color.black);
	}
	public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Ventana ex = new Ventana();
                
                ex.setVisible(true);
            }
        });
    }
}

