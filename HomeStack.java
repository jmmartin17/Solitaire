public class HomeStack extends CardStack {
	private Suits suit;

	public HomeStack() {
	}

	public void setCoords() {
		if (size() != 0) {
			get(size() - 1).setX(30 + (3 + arrayIndex) * 130);
			get(size() - 1).setY(30);
		}
	}

	public Suits getSuit() {
		return suit;
	}

	public boolean validPlacement(Card base, Card toPlace) {
		if (toPlace == null)
			return false;
		if (suit == null && toPlace.getRank() == Ranks.ace)
			return true;
		if (base == null)
			return false;
		return base.compareTo(toPlace) == 1 && toPlace.getSuit() == suit;
	}
	
	public void reset() {
		suit = null;
	}
	
	public Card push(Card c) {
		if (suit == null)
			suit = c.getSuit();
		return super.push(c);
	}
	
	public Card pop() {
		if (empty())
			return super.pop();
		if (peek().getRank() == Ranks.ace)
			reset();
		return super.pop();
	}
	
	public void populate() {
		for(int i = 0; i < 18; i++)
			for(int j = 0; j < 10; j++)
				Util.stackMap[3 + 13 * (arrayIndex + 3) + j][3 + i] = this;
	}

}
