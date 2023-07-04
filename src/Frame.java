import javax.swing.JFrame;

public class Frame extends JFrame {
	 

	Frame(){
		this.setTitle("SNAKE");
		this.add(new Panel());
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
}
}