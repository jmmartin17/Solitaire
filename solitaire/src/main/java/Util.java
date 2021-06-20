public class Util {
	
	public static Card[][] cardMap = new Card[95][80];
	public static CardStack[][] stackMap = new CardStack[95][80];

public static final Ranks[] rankHash = { Ranks.ace, Ranks.two, Ranks.three, Ranks.four, Ranks.five, Ranks.six, Ranks.seven, Ranks.eight, Ranks.nine, Ranks.ten, Ranks.jack, Ranks.queen, Ranks.king };

public static final Suits[] suitHash = { Suits.clubs, Suits.hearts, Suits.diamonds, Suits.spades };

public static int equateRank(Ranks rank) {
	for(int i = 0; i < rankHash.length; i++)
		if (rankHash[i] == rank)
			return i;
	return -1;
}

public static int equateSuit(Suits suit) {
	for(int i = 0; i < suitHash.length; i++)
		if (suitHash[i] == suit)
			return i;
	return -1;
}

public static final byte[][][] rankBitmaps = 	{
							{ 
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 1, 0, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 1, 0, 0, 0, 0, 0, 1 },
								{ 1, 1, 1, 1, 1, 1, 1 },
								{ 1, 0, 0, 0, 0, 0, 1 },
								{ 1, 0, 0, 0, 0, 0, 1 },
								{ 1, 0, 0, 0, 0, 0, 1 }
							},
							{
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 1, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 1, 0, 0, 0, 0 },
								{ 0, 1, 1, 1, 1, 1, 0 }
							},
							{
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 1, 0, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 1, 1, 1, 0, 0 }
							},
							{
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 1, 1, 1, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 }
							},
							{
								{ 0, 1, 1, 1, 1, 1, 0 },
								{ 0, 1, 0, 0, 0, 0, 0 },
								{ 0, 1, 0, 0, 0, 0, 0 },
								{ 0, 1, 1, 1, 1, 0, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 1, 1, 1, 1, 0, 0 }
							},
							{
								{ 0, 0, 0, 1, 1, 0, 0 },
								{ 0, 0, 1, 0, 0, 0, 0 },
								{ 0, 1, 0, 0, 0, 0, 0 },
								{ 0, 1, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 1, 1, 1, 0, 0 }
							},
							{
								{ 0, 1, 1, 1, 1, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 1, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 1, 0, 0, 0, 0 },
								{ 0, 0, 1, 0, 0, 0, 0 }
							},
							{
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 1, 1, 1, 0, 0 }
							},
							{
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 0, 1, 1, 1, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 }
							},
							{
								{ 1, 0, 0, 1, 1, 1, 0 },
								{ 1, 0, 1, 0, 0, 0, 1 },
								{ 1, 0, 1, 0, 0, 0, 1 },
								{ 1, 0, 1, 0, 0, 0, 1 },
								{ 1, 0, 1, 0, 0, 0, 1 },
								{ 1, 0, 1, 0, 0, 0, 1 },
								{ 1, 0, 0, 1, 1, 1, 0 }
							},
							{
								{ 0, 1, 1, 1, 1, 1, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 0, 0, 1, 0, 0, 0 },
								{ 0, 1, 1, 1, 0, 0, 0 }
							},
							{
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 1, 0, 1, 0 },
								{ 0, 0, 1, 1, 1, 0, 0 },
								{ 0, 0, 0, 0, 0, 1, 0 }
							},
							{
								{ 0, 1, 0, 0, 0, 1, 0 },
								{ 0, 1, 0, 0, 1, 0, 0 },
								{ 0, 1, 0, 1, 0, 0, 0 },
								{ 0, 1, 1, 0, 0, 0, 0 },
								{ 0, 1, 0, 1, 0, 0, 0 },
								{ 0, 1, 0, 0, 1, 0, 0 },
								{ 0, 1, 0, 0, 0, 1, 0 }
							}
};
								

public static final byte[][][] suitBitmaps = {
	{
		{ 0, 0, 1, 1, 1, 0, 0 },
		{ 0, 0, 1, 1, 1, 0, 0 },
		{ 0, 0, 0, 1, 0, 0, 0 },
		{ 1, 1, 0, 1, 0, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 0, 1, 0, 1, 1 },
		{ 0, 0, 0, 1, 0, 0, 0 }
	},
	{
		{ 0, 1, 1, 0, 1, 1, 0 },
		{ 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 1 },
		{ 1, 1, 1, 1, 1, 1, 1 },
		{ 0, 1, 1, 1, 1, 1, 0 },
		{ 0, 0, 1, 1, 1, 0, 0 },
		{ 0, 0, 0, 1, 0, 0, 0 },
	},
	{
		{ 0, 0, 0, 1, 0, 0, 0 },
		{ 0, 0, 1, 1, 1, 0, 0 },
		{ 0, 1, 1, 1, 1, 1, 0 },
		{ 1, 1, 1, 1, 1, 1, 1 },
		{ 0, 1, 1, 1, 1, 1, 0 },
		{ 0, 0, 1, 1, 1, 0, 0 },
		{ 0, 0, 0, 1, 0, 0, 0 }
	},
	{
		{ 0, 0, 0, 1, 0, 0, 0 },
		{ 0, 0, 1, 1, 1, 0, 0 },
		{ 0, 1, 1, 1, 1, 1, 0 },
		{ 1, 1, 1, 1, 1, 1, 1 },
		{ 0, 1, 1, 1, 1, 1, 0 },
		{ 0, 0, 0, 1, 0, 0, 0 },
		{ 0, 0, 0, 0, 0, 0, 0 }
	}
};



public static void drawBitmap(byte[][] bitmap, int x, int y, int scale, java.awt.Graphics g) {
		for(int i = 0; i < bitmap.length; i++)
			for(int j = 0; j < bitmap[i].length; j++)
				if (bitmap[i][j] != 0)
					g.fillRect(x + scale * j, y + scale * i, scale, scale);
	}

public static MainStack toMainStack(CardStack c) {
	MainStack m = new MainStack();
	for(int i = 0; i < c.size(); i++)
		m.push(c.get(i));
	return m;
}

public static ToDraw toToDraw(CardStack c) {
	ToDraw t = new ToDraw();
	for(int i = 0; i < c.size(); i++)
		t.push(c.get(i));
	return t;
}

}