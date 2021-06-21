 
public class StockPile extends CardStack {

  @Override
	public void setCoords() {
		peek().setX(30);
		peek().setY(30);
	}

}
