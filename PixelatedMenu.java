import java.awt.Color;
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

public class PixelatedMenu extends JPanel implements ActionListener{
	private JButton botonInicio,
	                botonDemo;
	private Image fondo;
	private JFrame frame;
	
	public PixelatedMenu(JFrame frame) {
		super();
		this.frame = frame;
		this.fondo = new ImageIcon("pixels4.jpg").getImage();
		this.setPreferredSize(new Dimension(600,650));
		this.botonInicio = new JButton("Jugar");
		this.botonInicio.setFont(this.botonInicio.getFont().deriveFont(18.0f));
		this.botonInicio.addActionListener(this);
		this.botonDemo = new JButton("Ver demostración");
		this.botonDemo.setFont(this.botonDemo.getFont().deriveFont(18.0f));
		this.botonDemo.addActionListener(this);
		this.add(botonInicio);
		this.add(botonDemo);	
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(this.fondo,0,0,this.getWidth(),this.getHeight(),this);
		g.setColor(Color.BLACK);
		Font font = g.getFont().deriveFont( 20.0f );
		g.setFont(font);
		g.drawString("Haz que la cuadrícula quede de un sólo color.", 100, 100);
		g.drawString("Comenzando del cuadro de la esquina superior izquierda,", 70, 150);
		g.drawString("presiona los seis recuadros de la parte inferior para cambiar ", 50, 200);
		g.drawString("el color de la figura. Esta se expandirá con los cuadros ", 60, 250);
		g.drawString("próximos que sean del mismo color.", 150, 300);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.frame.getContentPane().removeAll();
		if(e.getSource() == this.botonInicio) {
			PixelatedPanel panel = new PixelatedPanel(this.frame);
			this.frame.add(panel);
		}
		else if(e.getSource() == this.botonDemo) {
			PixelatedDemo panelDemo = new PixelatedDemo(this.frame);
			this.frame.add(panelDemo);
		}
		this.frame.revalidate();
	}
	
}
