import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.GridLayout;

public class jg005 extends JFrame implements ActionListener {
	
	public JButton addButton(String txt){
		JButton btn = new JButton(txt);
		getContentPane().add(btn);
		return btn;
		}
	
	public JTextField addInput(int size){
		JTextField text = new JTextField(size);
		getContentPane().add(text);
		return text;
		}
	
	public JLabel addLabel(String txt){
		JLabel label = new JLabel(txt);
		getContentPane().add(label);
		return label;
		}
	
	public void Init(){
		// JFrame equivale ao Canvas do antigo Applet
		setTitle("HelloWorldSwing");
		setSize(600, 150);
		// Layout: (Linhas, Colunas, espaço entre linhas, espaço entre colunas)
		setLayout(new GridLayout(3,2,10,10));
		// Instancia um JLabel e o acrescenta ao frame
		JLabel label1 = addLabel("Nome: ");
		// Instancia um JTextField e o acrescenta ao frame
		JTextField text1 = addInput(20);
		// Instancia um JLabel e o acrescenta ao frame
		JLabel label2 = addLabel("CPF: ");
		// Instancia um JTextField e o acrescenta ao frame
		JTextField text2 = addInput(10);	
		// Instancia um JTextField e o acrescenta ao frame
		JButton btn1 = addButton("Envia");
		// Preferível pois pertence à classe geral JComponent 
		btn1.setName("Botao1");
		// Evento
		btn1.addActionListener(this); 

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Ajusta as superfícies dos controles na Frame
		//pack();
		setVisible(true);
		}
	
	public void btn1click(){
		System.out.println("Botão foi pressionado");
		}
	public static void main(String[] args) {
		jg005 jg = new jg005();
		jg.Init();

		}
		@Override
		public void actionPerformed(ActionEvent e) {
			System.out.println(((JComponent) e.getSource()).getName());
			}
	}