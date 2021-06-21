class CardStack extends Array {
  constructor() {
    this.indexOfStack = 0;
  }

  // override
  push(c) {
    c.setContainer(this);
    setCoords();
    return super.push(c);
  }

  setIndexOfStack(i) {
    this.indexOfStack = i;
  }

  validPlacement(base, toPlace) {
    return true;
  }

  draw(ctx, cardMap, stackMap) {
    if (this.length > 0)
      this.peek().drawAndPopulate(ctx, cardMap);
  }

  peek() {
    if (this.length > 0)
      return this[this.length - 1];
    else
      return null;
  }

  populate() { }

  append(cs) {
    for (c in cs)
      this.push(c);
  }
}