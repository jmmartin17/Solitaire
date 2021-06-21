class MainStack extends CardStack {
  setCoords() {		
    let curY = 0;
		for(let i = 0; i < this.length; i++) {
		  this[i].setX(30 + arrayIndex * 130);
		  this[i].setY(240 + curY);
		  if([i].faceUp())
  			curY += 10;
		  curY += 10;
		}
  }

  // override
  validPlacement(base, toPlace) {
    if (base == null)
      return toPlace.getRank() == 'king';
    else
      return toPlace.compareTo(base) == 1 && xor(base.isRed(), toPlace.isRed());
  }

  // override
  draw(ctx, cardMap, stackMap) {
    for (let i = 0; i < 10; i++)
      for (let j = 0; j < 18 + 2 * this.length; j++)
        stackMap[3 + i + 13 * this.indexOfStack][24 + j] = this;
    for (let i = 0; i < this.length; i++)
      this[i].drawAndPopulate(ctx, cardMap, stackMap);
  }
}