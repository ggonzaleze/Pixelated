import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelatedScore extends JPanel implements ActionListener{
	JFrame frame;
	int movimientos;
	Image fondo;
	JButton boton;
	
	public PixelatedScore(JFrame frame, int mov) {
		super();
		this.setPreferredSize(new Dimension(600,650));
		this.frame = frame;
		this.movimientos = mov;
		this.fondo = new ImageIcon("pixels4.jpg").getImage();
		this.boton = new JButton("Menú");
		this.boton.addActionListener(this);
		this.add(this.boton);
	}
	
	public void paintComponent(Graphics g) {
		g.drawImage(this.fondo,0,0,this.getWidth(),this.getHeight(),this);
		Font font = g.getFont().deriveFont( 30.0f );
		g.setFont(font);
		g.drawString("¡Muy bien!", 230, 150);
		g.drawString("Número de movimientos: " + this.movimientos, 130, 200);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.boton) {
			this.frame.getContentPane().removeAll();
			PixelatedMenu panelMenu = new PixelatedMenu(this.frame);
			this.frame.add(panelMenu);
			this.frame.revalidate();
		}
		
	}

}
