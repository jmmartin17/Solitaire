class StockPile extends CardStack {
  setCoords() {
    this.peek().setX(30);
    this.peek().setY(30);
  }
}