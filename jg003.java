import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;

public class jg003 extends JFrame {
	
	public void Init(){
		// JFrame equivale ao Canvas do antigo Applet
		setTitle("HelloWorldSwing");
		setSize(600, 70);
		// Layout: (Linhas, Colunas, espaço entre linhas, espaço entre colunas)
		setLayout(new GridLayout(1,2,10,10));
		// Instancia um JLabel
		final JLabel label = new JLabel("Nome:");
		final JTextField text = new JTextField(10);
		text.setPreferredSize(new Dimension(100, 10));
		// Insere o Label e o campo de texto no painel (Canvas) da Frame
		getContentPane().add(label);
		getContentPane().add(text);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Ajusta as superfícies dos controles na Frame
		//frame.pack();
		setVisible(true);
		}
	
	public static void main(String[] args) {
		(new jg003()).Init();
		}
	}