 import java.util.Stack;

public class CardStack extends Stack<Card> {
	protected int arrayIndex;

	public void setCoords() {
	}

	public Card push(Card c) {
		c.setContainer(this);
		super.push(c);
		setCoords();
		return c;
	}

	public void setIndex(int i) {
		arrayIndex = i;
	}

	public boolean validPlacement(Card base, Card toPlace) {
		return true;
	}

	public void draw(java.awt.Graphics g) {
		if (size() != 0)
			get(size() - 1).drawAndPopulate(g);
	}
	
	public Card peek() {
		if (empty())
			return null;
		return super.peek();
	}
	
	public void populate() {
		
	}
}