import java.awt.GridLayout;

import javax.swing.JFrame;

public class Application extends JFrame {

	private static final long serialVersionUID = -2517616123689799182L;

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setLayout(new GridLayout(1,1));
		f.add(new VueClickChallenge());
		f.pack();
		f.setVisible(true);
		f.setSize(500,300);
		f.setTitle("Click Challenge");
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);

	}

}
