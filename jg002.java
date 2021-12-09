import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;

public class jg002 {
	public static void main(String[] args) {
		// JFrame equivale ao Canvas do antigo Applet
		JFrame frame = new JFrame("HelloWorldSwing");
		frame.setSize(600, 70);
		// Layout: (Linhas, Colunas, espaço entre linhas, espaço entre colunas)
		frame.setLayout(new GridLayout(1,2,10,10));
		// Instancia um JLabel
		final JLabel label = new JLabel("Nome:");
		final JTextField text = new JTextField(10);
		text.setPreferredSize(new Dimension(100, 10));
		// Insere o Label e o campo de texto no painel (Canvas) da Frame
		frame.getContentPane().add(label);
		frame.getContentPane().add(text);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Ajusta as superfícies dos controles na Frame
		//frame.pack();
		frame.setVisible(true);
		}
	}