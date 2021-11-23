import javax.swing.*;

public class jg001 {
	public static void main(String[] args) {
		JFrame frame = new JFrame("HelloWorldSwing");
		String html01 = "<html><p style=\"width:200px;line-height:350%;font-size: 24px;color:teal;padding: 10px;\">Este é o primeiro exemplo de Java gráfico. Estamos demonstrando o componente Label.</p></html>";
		final JLabel label = new JLabel();
		label.setText(html01);
		frame.getContentPane().add(label);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		}
	}