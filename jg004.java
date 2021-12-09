import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridLayout;

public class jg004 extends JFrame {
	
	public JTextField addInput(int size){
		final JTextField text = new JTextField(size);
		getContentPane().add(text);
		return text;
		}
	
	public void addLabel(String txt){
		final JLabel label = new JLabel(txt);
		getContentPane().add(label);
		}
	
	public void Init(){
		// JFrame equivale ao Canvas do antigo Applet
		setTitle("HelloWorldSwing");
		setSize(600, 70);
		// Layout: (Linhas, Colunas, espaço entre linhas, espaço entre colunas)
		setLayout(new GridLayout(1,2,10,10));
		// Instancia um JLabel e o acrescenta ao frame
		addLabel("Nome: ");
		// Instancia um JTextField e o acrescenta ao frame
		JTextField text1 = addInput(20);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Ajusta as superfícies dos controles na Frame
		//frame.pack();
		setVisible(true);
		}
	
	public static void main(String[] args) {
		(new jg004()).Init();
		}
	}