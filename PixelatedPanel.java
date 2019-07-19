import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Rectangle2D;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class PixelatedPanel extends JPanel implements MouseListener,ActionListener{
	protected Color[] colors;
	protected PixelatedSquare[][] squares;
	protected int figuraSize;
	protected boolean[][] pasado;
	protected Rectangle2D[] recs;
	protected JButton volver;
	protected JFrame frame;
	protected int movimientos;
	
	public PixelatedPanel(JFrame frame) {
		super();
		this.frame = frame;
		this.setPreferredSize(new Dimension(600,650));
		this.colors = new Color[6];
		this.colors[0] = new Color(135, 206, 250);//BLUE
		this.colors[1] = new Color(255, 99, 71);//RED
		this.colors[2] = new Color(205, 201, 201);//GREY
		this.colors[3] = new Color(60, 179, 113);//GREEN
		this.colors[4] = new Color(186, 85, 211);//VIOLET
		this.colors[5] = new Color(255, 215, 0);//YELLOW
		this.squares = new PixelatedSquare[20][20];
		this.crearCuadros();
		this.figuraSize = 1;
		this.movimientos = 0;
		this.pasado = new boolean[20][20];
		this.recs = new Rectangle2D[6];
		for(int i = 0; i < recs.length; i++) {
			recs[i] = new Rectangle2D.Double();
		}
		this.addMouseListener(this);
		this.volver = new JButton("Menú");
		this.volver.addActionListener(this);
		this.add(this.volver);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		int color;
		for(int i = 0; i < squares.length; i++) {
			for(int j = 0; j < squares[i].length; j++) {
				color = this.squares[i][j].getColorNum();
				g.setColor(this.colors[color]);
				g.fillRect(this.squares[i][j].getX(), this.squares[i][j].getY(), 25, 25);
			}
		}
		pintaBotones(g);
	}
	
	protected void crearCuadros() {
		Random rand = new Random();
		int randomColor;
		int row = 0,
			column;
		for(int i = 50; i < 550; i+= 25) {
			column = 0;
			for(int j = 50; j < 550; j += 25) {
				randomColor = rand.nextInt(6);
				this.squares[row][column] = new PixelatedSquare(randomColor,i,j);
				column++;
			}
			row++;
		}
	}
	
	protected void pintaBotones(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int x = 50;
		for(int i = 0; i < recs.length; i++) {
			g2d.setColor(colors[i]);
			recs[i].setFrame(x,575,50,50);
			g2d.fill(recs[i]);
			x += 75;
		}
	}
	
	protected void expandirFigura(PixelatedSquare square, int color, int i, int j) {
		pasado[i][j] = true;
		if(square.getFigura() == true) {
			square.setColorNum(color);
			this.repaint();
			if(validar(i, j-1)) {
				expandirFigura(squares[i][j-1],color,i,j-1);
			}
			if(validar(i+1, j-1)) {
				expandirFigura(squares[i+1][j-1],color,i+1,j-1);
			}
			if(validar(i+1, j)) {
				expandirFigura(squares[i+1][j],color,i+1,j);
			}
			if(validar(i+1, j+1)) {
				expandirFigura(squares[i+1][j+1],color,i+1,j+1);
			}
			if(validar(i, j+1)) {
				expandirFigura(squares[i][j+1],color,i,j+1);
			}
			if(validar(i-1, j+1)) {
				expandirFigura(squares[i-1][j+1],color,i-1,j+1);
			}
			if(validar(i-1, j)) {
				expandirFigura(squares[i-1][j],color,i-1,j);
			}
			if(validar(i-1, j-1)) {
				expandirFigura(squares[i-1][j-1],color,i-1,j-1);
			}
		}
		else {
			if(square.getColorNum() == color) {
				square.setFigura(true);
				this.figuraSize++;
			}
		}
	}
	
	protected boolean validar(int i, int j) {
		if(i < 0 || i >= 20) {
			return false;
		}
		else if(j < 0 || j >= 20) {
			return false;
		}
		else if(pasado[i][j] == true) {
			return false;
		}
		return true;
	}
	
	protected void pasadoFalse() {
		for(int i = 0; i < this.pasado.length; i++) {
			for(int j = 0; j < this.pasado[i].length;j++) {
				pasado[i][j] = false;
			}
		}
	}
	
	public void resolver(int color) {
		this.squares[0][0].setFigura(true);
		expandirFigura(squares[0][0],color,0,0);
		pasadoFalse();
		if(this.figuraSize == 400) {
			this.frame.getContentPane().removeAll();
			PixelatedScore panelScore = new PixelatedScore(this.frame,this.movimientos);
			this.frame.add(panelScore);
			this.frame.revalidate();
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.movimientos++;
		if(this.recs[0].contains(getMousePosition())) {
			resolver(0);
		}
		else if(this.recs[1].contains(getMousePosition())) {
			resolver(1);
		}
		else if(this.recs[2].contains(getMousePosition())) {
			resolver(2);
		}
		else if(this.recs[3].contains(getMousePosition())) {
			resolver(3);
		}
		else if(this.recs[4].contains(getMousePosition())) {
			resolver(4);
		}
		else if(this.recs[5].contains(getMousePosition())) {
			resolver(5);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == this.volver) {
			this.frame.getContentPane().removeAll();
			PixelatedMenu panelMenu = new PixelatedMenu(this.frame);
			this.frame.add(panelMenu);
			this.frame.revalidate();
		}
		
	}
	
}
