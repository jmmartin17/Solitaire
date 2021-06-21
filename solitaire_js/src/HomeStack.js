class HomeStack extends CardStack {
  constructor() {
    this.reset();
  }

  reset() {
    this.suit = null;
  }

  setCoords() {
    if (this.length > 0) {
      this.peek().setX(30 + (3 + this.indexOfStack) * 130);
      this.peek().setY(30);
    }
  }

  getSuit() {
    return this.suit;
  }

  // override
  validPlacement(base, toPlace) {
    if (toPlace == null)
      return false;
    else if (this.suit == null && toPlace.getRank() == 'ace')
      return true;
    else if (base == null)
      return false;
    else
      return (base.compareTo(toPlace) == 1 && toPlace.getSuit() == this.suit);
  }

  // override
  push(c) {
    if (suit == null)
      suit = c.getSuit();
    return super.push();
  }

  // override
  pop() {
    if (this.length == 0 || this.peek().getRank() == 'ace')
      this.reset();
    return super.pop();
  }
  
  // override
  populate(stackMap) {
    for (let i = 0; i < 18; i++)
      for (let j = 0; j < 10; j++)
        stackMap[3 + 13 * (this.indexOfStack + 3) + j][3 + i] = this;
  }
}