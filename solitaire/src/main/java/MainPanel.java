import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

public class MainPanel extends JPanel {
  // members
	private Deck deck;
	private StockPile toDraw;
	private Drawn drawn;
	private ArrayList<CardStack> allStacks;
	private ArrayList<MainStack> mainStacks;
	private ArrayList<HomeStack> homeStacks;
	private Click click;
	private Drag drag;
	private boolean game;
	private javax.swing.Timer timer;

	public MainPanel() {
		setPreferredSize(new Dimension(950, 660));
		System.out.println("//");
    restartGame();
	}
  
  public final void restartGame()
  {
    game = true;
		deck = new Deck();
		deck.shuffle();
		mainStacks = new ArrayList<>();
		allStacks  = new ArrayList<>();
		homeStacks = new ArrayList<>();
    
    if (timer != null) {
      timer.stop();
      timer = null;
    }
    
		for (int i = 0; i < 7; i++) {
			MainStack temp = Util.toMainStack(deck.draw(i + 1));
			temp.setIndex(i);
			temp.setCoords();
			mainStacks.add(temp);
			mainStacks.get(i).peek().flip();
			for (int j = 0; j < 10; j++)
				for (int k = 0; k < 18; k++)
					Util.stackMap[3 + 13 * i + j][24 + k] = mainStacks.get(i);
			allStacks.add(mainStacks.get(i));
		}
		for (int i = 0; i < 4; i++) {
			homeStacks.add(new HomeStack());
			homeStacks.get(i).setIndex(i);
			homeStacks.get(i).populate();
			allStacks.add(homeStacks.get(i));
		}
		toDraw = Util.toStockPile(deck.draw(52 - 28));
		allStacks.add(toDraw);
		drawn = new Drawn();
		allStacks.add(drawn);
		setBackground(new Color(20, 100, 20));
    while (getMouseListeners().length > 0)
      removeMouseListener(getMouseListeners()[0]);
    while (getMouseMotionListeners().length > 0)
      removeMouseMotionListener(getMouseMotionListeners()[0]);
		click = new Click();
		addMouseListener(click);
		drag = new Drag();
		addMouseMotionListener(drag);
    Solitaire.undoButton.start();
    Solitaire.resetButton.start();
		repaint();
  }

	private class Click implements MouseListener {
		private ArrayList<Card> held;
		private CardStack source, destination;
		private int x, y; // mouse location relative to card clicked

		public Click() {
		}
    
    @Override
		public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.BUTTON3) {
				returnHome();
				repaint();
				return;
			}
			if (e.getX() >= 30 && e.getX() <= 130 && e.getY() >= 30
					&& e.getY() <= 210) {
				Solitaire.undoButton.newState();
				nextCard();
			}
			repaint();
		}

    @Override
		public void mousePressed(MouseEvent e) {
			Card clicked = Util.cardMap[e.getX() / 10][e.getY() / 10];
			if (clicked == null || !clicked.faceUp())
				return;
			source = clicked.getContainer();
			held = new ArrayList<>();
			for (int i = find(clicked, source); i < source.size(); i++)
				held.add(source.get(i));
			for (int i = 0; i < held.size(); i++)
				source.pop();
			x = e.getX() - clicked.getX();
			y = e.getY() - clicked.getY();
		}

    @Override
		public void mouseReleased(MouseEvent e) {
			if (held != null) {
				boolean validRelease;
				destination = Util.stackMap[drag.getX() / 10][drag.getY() / 10];
				if (destination == null)
					validRelease = false;
				else
					validRelease = destination.validPlacement(
							Util.cardMap[drag.getX() / 10][drag.getY() / 10],
							held.get(0));
				if (validRelease) {
					append(source, held);
					Solitaire.undoButton.newState();
					for (int i = 0; i < held.size(); i++) {
						source.pop();
						destination.push(held.get(i));
					}
					if (source.getClass().toString().equals("class MainStack")
							&& !source.empty())
						source.peek().flip();
				} else {
					for (int i = 0; i < held.size(); i++)
						source.push(held.get(i));
				}
				held = null;
			}
			repaint();
		}
    
    @Override
    public void mouseExited(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}

		public ArrayList<Card> getHeld() {
			return held;
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}

	private class Drag implements MouseMotionListener {
		private int x, y;

    @Override
		public void mouseDragged(MouseEvent e) {
			x = e.getX();
			y = e.getY();
			repaint();
		}

    @Override
		public void mouseMoved(MouseEvent arg0) {
		}

		public int getX() {
			return x;
		}

		public int getY() {
			return y;
		}
	}

	private class Blink implements ActionListener {
		private boolean on;

		public Blink() {
			super();
			on = false;
		}
		
    @Override
		public void actionPerformed(ActionEvent arg0) {
  	on = !on;
  	if (on)
  		drawWin(getGraphics());
      else
      	repaint();
    }
	}

	private void nextCard() {
		if (toDraw.empty())
			resetDraw();
		else {
			toDraw.peek().flip();
			drawn.push(toDraw.pop());
		}
	}

	private void resetDraw() {
		while (!drawn.empty()) {
			drawn.peek().reset();
			toDraw.push(drawn.pop());
		}
	}

	private int find(Card c, CardStack s) {
		for (int i = 0; i < s.size(); i++)
			if (c == s.get(i))
				return i;
		return -1;
	}

	private void returnHome() {
		for (int outer = 0; outer < 200; outer++) {
			for (int i = 0; i < mainStacks.size(); i++)
				for (int j = 0; j < homeStacks.size(); j++)
					if (homeStacks.get(j).validPlacement(
							homeStacks.get(j).peek(), mainStacks.get(i).peek())) {
						Solitaire.undoButton.newState();
						homeStacks.get(j).push(mainStacks.get(i).pop());
						if (!mainStacks.get(i).empty()) {
							mainStacks.get(i).peek().flip();
						}
						break;
					}
			for (int j = 0; j < homeStacks.size(); j++)
				if (!drawn.empty()
						&& homeStacks.get(j).validPlacement(
								homeStacks.get(j).peek(), drawn.peek())) {
					Solitaire.undoButton.newState();
					homeStacks.get(j).push(drawn.pop());
					break;
				}
		}
	}

  @Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int i = 0; i < 95; i++)
			for (int j = 0; j < 80; j++)
				Util.cardMap[i][j] = null;
		g.setColor(Color.BLACK);
		g.drawRect(30, 30, 100, 180);
		for (int i = 0; i < 4; i++)
			g.drawRect(30 + 130 * (3 + i), 30, 100, 180);
		g.setColor(new Color(0, 255, 0));
		g.fillOval(30 + 50 - 30, 30 + 90 - 30, 60, 60);
		g.setColor(new Color(20, 100, 20));
		g.fillOval(30 + 50 - 25, 30 + 90 - 25, 50, 50);
		if (!toDraw.empty()) {
			toDraw.peek().drawAndPopulate(g);
		}
		if (!drawn.empty())
			drawn.peek().drawAndPopulate(g);
		for (int i = 0; i < allStacks.size(); i++)
			allStacks.get(i).draw(g);
		if (click.getHeld() != null) {
			for (int i = 0; i < click.getHeld().size(); i++) {
				click.getHeld().get(i).setX(drag.getX() - click.getX());
				click.getHeld().get(i)
						.setY(drag.getY() - click.getY() + i * 20);
				click.getHeld().get(i).drawOnly(g);
			}
		}
		for (int i = 0; i < homeStacks.size(); i++)
			if (homeStacks.get(i).size() != 13 || !game)
				return;
		timer = new javax.swing.Timer(500, new Blink());
		timer.start();
		removeMouseListener(click);
		removeMouseMotionListener(drag);
    Solitaire.undoButton.stop();
		game = false;
	}

	public void drawWin(Graphics g) {
		g.setColor(Color.MAGENTA);
		Util.drawBitmap(winMap, 100, 400, 20, g);
	}
	
	public void setStates(ArrayList<HomeStack> h, ArrayList<MainStack> m, Drawn d,
			StockPile t) {
		homeStacks = h;
		mainStacks = m;
		drawn = d;
		toDraw = t;
		allStacks = new ArrayList<>();
		for(int i = 0; i < homeStacks.size(); i++)
			allStacks.add(homeStacks.get(i));
		for(int i = 0; i < mainStacks.size(); i++)
			allStacks.add(mainStacks.get(i));
		allStacks.add(drawn);
		allStacks.add(toDraw);
		repaint();
	}

	public ArrayList<HomeStack> getHomeStacks() {
		return homeStacks;
	}
	
	public ArrayList<MainStack> getMainStacks() {
		return mainStacks;
	}
	
	public Drawn getDrawn() {
		return drawn;
	}
	
	public StockPile getToDraw() {
		return toDraw;
	}
	
	public static void append(AbstractList<Card> s1, AbstractList<Card> s2) {
		for(int i = 0; i < s2.size(); i++)
			s1.add(s2.get(i));
	}
	
	private static final byte[][] winMap = {
		{ 1, 0, 0, 0, 1, 0, 0, 1, 1, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 },
		{ 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 1 },
		{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1 },
		{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1 },
		{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 1 },
		{ 0, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1, 0, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 1, 1 },
		{ 0, 0, 1, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 1, 1, 1, 1, 1, 0, 1, 0, 0, 0, 1 }
	};
}