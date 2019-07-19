import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;

import javax.swing.JFrame;

public class PixelatedDemo extends PixelatedPanel implements Runnable{
	private Thread hilo = new Thread(this);
	private int color;
	
	public PixelatedDemo(JFrame frame) {
		super(frame);
		this.color = 0;
		hilo.start();
	}
	
	@Override
	public void resolver(int color) {
		this.squares[0][0].setFigura(true);
		expandirFigura(squares[0][0],color,0,0);
		pasadoFalse();
	}
	
	@Override        
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		resaltarBoton(g,this.color);
	}
	
	private void resaltarBoton(Graphics g, int boton) {
		Graphics2D g2d = (Graphics2D) g;
		g2d.setColor(Color.black);
		g2d.setStroke(new BasicStroke(4));
		g2d.draw(recs[color].getBounds2D());
	}
	
	public void resolverDemo() {
		while(this.figuraSize != 400) {
			if(this.color == 6) {
				this.color = 0;
			}
			resolver(this.color);
			try {
				hilo.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.color++;
		}
	}

	@Override
	public void run() {
		resolverDemo();
	}
	
}
