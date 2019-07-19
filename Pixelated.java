import javax.swing.JFrame;

public class Pixelated extends JFrame{
	
	public Pixelated() {
		super("Pixelated");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		PixelatedMenu panelMenu = new PixelatedMenu(this);
	 	this.add(panelMenu);
		this.pack();
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		Pixelated pixelated = new Pixelated();
	}
}
