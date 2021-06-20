import java.awt.*;
 
public class Card implements Comparable<Card> {
	private Ranks rank;
	private Suits suit;

	private boolean faceUp, isRed;

	private int rankIndex, suitIndex, x, y;
	
	private CardStack container;

	public Card(Ranks r, Suits s) {
		faceUp = false;
		rank = r;
		suit = s;
		rankIndex = Util.equateRank(rank);
		suitIndex = Util.equateSuit(suit);
		isRed = (getSuitIndex() == 1 || getSuitIndex() == 2);
	}

	public Card() {
		faceUp = false;
		rank = Ranks.ace;
		suit = Suits.clubs;
		isRed = false;
	}

	public boolean isRed() {
		return isRed;
	}

	public boolean faceUp() {
		return faceUp;
	}

	public void flip() {
		faceUp = true;
	}

	public void reset() {
		faceUp = false;
	}

	public Ranks getRank() {
		return rank;
	}

	public Suits getSuit() {
		return suit;
	}

	public void drawAndPopulate(java.awt.Graphics g) {
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 18; j++)
				Util.cardMap[x / 10 + i][y / 10 + j] = this;
		drawOnly(g);
	}
	
	public void drawOnly(java.awt.Graphics g) {
		g.setColor(Color.WHITE);
		g.fillRect(x, y, 100, 180);
		g.setColor(Color.BLACK);
		g.drawRect(x,  y, 100, 180);
		if(faceUp) {
			if(isRed)
				g.setColor(Color.RED);
			else
				g.setColor(Color.BLACK);
			Util.drawBitmap(Util.rankBitmaps[rankIndex], x + 5, y + 5, 2, g);
			Util.drawBitmap(Util.rankBitmaps[rankIndex], x + 95 - 14, y + 5, 2, g);
			Util.drawBitmap(Util.rankBitmaps[rankIndex], x + 5, y + 175 - 14, 2, g);
			Util.drawBitmap(Util.rankBitmaps[rankIndex], x + 95 - 14, y + 175 - 14, 2, g);
			Util.drawBitmap(Util.suitBitmaps[suitIndex], x + 36, y + 76, 4, g);
			Util.drawBitmap(Util.suitBitmaps[suitIndex], x + 20, y + 5, 2, g);
			Util.drawBitmap(Util.suitBitmaps[suitIndex], x + 95 - 30, y + 5, 2, g);
			Util.drawBitmap(Util.suitBitmaps[suitIndex], x + 20, y + 175 - 14, 2, g);
			Util.drawBitmap(Util.suitBitmaps[suitIndex], x + 95 - 30, y + 175 - 14, 2, g);
		} else {
		g.setColor(Color.CYAN);
		g.fillRect(x + 1, y + 1, 99, 179);
		}
	}
	
	public int compareTo(Card c) {
		return c.rankIndex - this.rankIndex;
	}

	public int getRankIndex() {
		return rankIndex;
	}

	public int getSuitIndex() {
		return suitIndex;
	}
	
	public CardStack getContainer() {
		return container;
	}
	
	public void setContainer(CardStack c) {
		container = c;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int k) {
		x = k;
	}
	
	public void setY(int k) {
		y = k;
	}
	
	public Card clone() {
		Card clone = new Card(rank, suit);
		if (faceUp)
			clone.flip();
		clone.setX(x);
		clone.setY(y);
		return clone;
	}
}