import java.util.Stack;

public class CardStack extends Stack<Card> {
	protected int arrayIndex;

	public void setCoords() {
	}

  @Override
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
		if (!isEmpty())
			get(size() - 1).drawAndPopulate(g);
	}
	
  @Override
	public Card peek() {
		if (empty())
			return null;
		return super.peek();
	}
	
	public void populate() { }
  
  public void append(CardStack c) {
		for(int i = 0; i < c.size(); i++)
			add(c.get(i));
	}
}