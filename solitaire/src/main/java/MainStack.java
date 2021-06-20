 
public class MainStack extends CardStack {

	public void setCoords() {
		int curY = 0;
		for(int i = 0; i < size(); i++) {
		get(i).setX(30 + arrayIndex * 130);
		get(i).setY(240 + curY);
		if(get(i).faceUp())
			curY += 10;
		curY += 10;
		}
	}
	
	public boolean validPlacement(Card base, Card toPlace) {
		if (base == null)
			return toPlace.getRank() == Ranks.king;
		return toPlace.compareTo(base) == 1
				&& xor(base.isRed(), toPlace.isRed());
	}
	
	private boolean xor(boolean b1, boolean b2) {
		return (b1 && !b2) || (!b1 && b2);
	}
	
	public void draw(java.awt.Graphics g) {
		for(int i = 0; i < 10; i++)
			for(int j = 0; j < 18 + 2 * size(); j++)
				Util.stackMap[3 + i + 13 * arrayIndex][24 + j] = this;
		for(int i = 0; i < size(); i++) {
			get(i).drawAndPopulate(g);
		}
	}
	
}
