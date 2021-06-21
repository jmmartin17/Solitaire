import java.util.*;


public class GameState {
	private final ArrayList<HomeStack> homeStacks;
	private final ArrayList<MainStack> mainStacks;
	private final Drawn drawn;
	private final StockPile toDraw;
	
	public GameState(ArrayList<HomeStack> h, ArrayList<MainStack> m, Drawn d,
			StockPile t) {
		homeStacks = new ArrayList<>();
		for(int i = 0; i < h.size(); i++) {
			homeStacks.add(new HomeStack());
			Iterator<Card> it = h.get(i).iterator();
			while(it.hasNext())
				homeStacks.get(i).push(it.next().clone());
			homeStacks.get(i).setIndex(i);
			homeStacks.get(i).setCoords();
		}
		mainStacks = new ArrayList<>();
		for(int i = 0; i < m.size(); i++) {
			mainStacks.add(new MainStack());
			Iterator<Card> it = m.get(i).iterator();
			while(it.hasNext())
				mainStacks.get(i).push(it.next().clone());
			mainStacks.get(i).setIndex(i);
			mainStacks.get(i).setCoords();
		}
		drawn = new Drawn();
		Iterator<Card> it = d.iterator();
		while(it.hasNext())
			drawn.push(it.next().clone());
		toDraw = new StockPile();
		it = t.iterator();
		while(it.hasNext())
			toDraw.push(it.next().clone());
	}
	
	public void implementStates(MainPanel p) {
		for(int i = 0; i < homeStacks.size(); i++)
			homeStacks.get(i).populate();
		p.setStates(homeStacks, mainStacks, drawn, toDraw);
	}

}
