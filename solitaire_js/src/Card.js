class Card {
  constructor(r, s) {
    this.faceUp = false;
    this.rank   = r;
    this.suit   = s;
    this.x      = 0;
    this.y      = 0;
    this.container = null;
    this.isRed  = (this.suit == "hearts" || this.suit == "diamonds");
  }

  constructor() {
    this.faceUp = false;
    this.rank   = "ace";
    this.suit   = "clubs";
    this.x      = 0;
    this.y      = 0;
    this.container = null;
    this.isRed  = false;
  }

  isRed() {
    return this.isRed;
  }

  faceUp() {
    return this.faceUp;
  }

  flip() {
    faceUp = true;
  }

  getRank() {
    return this.rank;
  }

  getSuit() {
    return this.suit;
  }

  drawAndPopulate(ctx, cardMap) {
    for (let i = 0; i < 10; i++)
      for (let j = 0; j < 10; j++)
        cardMap[this.x / 10 + i][this.y / 10 + j] = this;
    drawOnly(ctx);
  }

  drawOnly(ctx) {
    ctx.fillStyle('white');
    ctx.fillRect(this.x, this.y, 100, 180);

    ctx.strokeStyle('black');
    ctx.strokeRect(this.x, this.y, 100, 180);

    if (this.faceUp) {
      ctx.fillStyle(this.isRed ? 'red' : 'black');
      drawMonochromeBitmap(rankBitmaps[this.rank], x + 5,  y + 5,   2, ctx);
      drawMonochromeBitmap(rankBitmaps[this.rank], x + 5,  y + 161, 2, ctx);
      drawMonochromeBitmap(rankBitmaps[this.rank], x + 81, y + 5,   2, ctx);
      drawMonochromeBitmap(rankBitmaps[this.rank], x + 81, y + 161, 2, ctx);

      drawMonochromeBitmap(suitBitmaps[this.suit], x + 36, y + 76,  4, ctx);
      drawMonochromeBitmap(suitBitmaps[this.suit], x + 20, y + 5,   2, ctx);
      drawMonochromeBitmap(suitBitmaps[this.suit], x + 20, y + 161, 2, ctx);
      drawMonochromeBitmap(suitBitmaps[this.suit], x + 65, y + 5,   2, ctx);
      drawMonochromeBitmap(suitBitmaps[this.suit], x + 65, y + 161, 2, ctx);
    } else {
      ctx.fillStyle('cyan');
      ctx.fillRect(x + 1, y + 1, 99, 179);
    }
  }

  getRankIndex() {
    return Ranks.findIndex(this.rank);
  }

  getSuitIndex() {
    return Suits.findIndex(this.suit);
  }

  compareTo(c) {
    return c.getRankIndex() - this.getRankIndex();
  }

  getContainer() {
    return this.container;
  }

  setContainer(c) {
    this.container = c;
  }

  getX() { 
    return this.x;
  }

  getY() {
    return this.y;
  }

  setX(x) {
    this.x = x;
  }

  setY(y) {
    this.y = y;
  }

  clone() {
    let clone = new Card(this.rank, this.suit);
    if (faceUp)
      clone.flip();
    clone.setX(this.x);
    clone.setY(this.y);
    return clone;
  }
}