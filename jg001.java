import javax.swing.*;

public class jg001 {
	public static void main(String[] args) {
		// JFrame equivale ao Canvas do antigo Applet
		JFrame frame = new JFrame("HelloWorldSwing");
		String html01 = "<html><p style=\"width:200px;line-height:350%;font-size: 24px;color:teal;padding: 10px;\">";
		html01 += "Este � o primeiro exemplo de Java gr�fico. Estamos demonstrando o componente Label.</p></html>";
		// Instancia um JLabel
		final JLabel label = new JLabel();
		// Coloca o conte�do HTML no texto do Label
		label.setText(html01);
		// Insere o Label no painel (Canvas) da Frame
		frame.getContentPane().add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Ajusta as superf�cies dos controles na Frame
		frame.pack();
		frame.setVisible(true);
		}
	}