 import java.util.*;

public class Deck {
	private CardStack cards;

	public Deck() {
		cards = new CardStack();
		for (int i = 0; i < Util.rankHash.length; i++)
			for (int j = 0; j < Util.suitHash.length; j++)
				cards.push(new Card(Util.rankHash[i], Util.suitHash[j]));
	}

	public void shuffle() {
		Random r = new Random();
		for (int i = 0; i < cards.size(); i++)
			swap(cards, i, r.nextInt(cards.size() - i) + i);
	}

	public Card draw() {
		if (cards.isEmpty())
			return null;
		return cards.pop();
	}

	public CardStack draw(int n) {
		if (n < 1 || cards.isEmpty())
			return null;
		CardStack temp = new CardStack();
		for (int i = 0; i < n && !cards.empty(); i++)
			temp.push(cards.pop());
		return temp;
	}

	private void swap(Vector l, int i1, int i2) {
		Object temp = l.get(i1);
		l.set(i1, l.get(i2));
		l.set(i2, temp);
	}
}