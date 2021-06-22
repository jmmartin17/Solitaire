class MainPanel {
  constructor() {
    this.deck         = null;
    this.stockPile    = null;
    this.drawn        = null;
    this.allStacks    = null;
    this.mainStacks   = null;
    this.held         = null;
    this.gameRunning  = false;
    this.stackMap     = null;
    this.cardMap      = null;
    this.mousePressed = false;
    this.gameStates   = null;
    this.mouseX       = null;
    this.mouseY       = null;

    this.canvas = document.getElementById('gameCanvas');
    this.ctx    = this.canvas.getContext('2d');

    this.restartGame();
  }

  restartGame() {
    this.gameRunning = true;
    this.deck = new Deck();
    this.deck.shuffle();
    this.mainStacks = [];
    this.allStacks  = [];
    this.homeStacks = [];
    this.gameStates = [];

    this.stackMap = new Array(95, 80);
    this.cardMap  = new Array(95, 80);

    for (let i = 0; i < 7; i++) {
      let temp = deck.draw(i + 1).toMainStack();
      temp.setIndexOfStack(i);
      temp.setCoords();
      this.mainStacks.push(temp);
      mainStacks[i].peek().flip();
      for (let j = 0; j < 10; j++)
        for (let k = 0; k < 18; k++)
          this.stackMap[3 + 13 * i + j][24 + k] = mainStacks[i];
      this.allStacks.push(mainStacks[i]);
    }

    for (let i = 0; i < 4; i++) {
      this.homeStacks.push(new HomeStack());
      this.homeStacks[i].setIndexOfStack(i);
      this.homeStacks[i].populate(this.stackMap);
      this.allStacks.push(this.homeStacks[i]);
    }
    this.stockPile = deck.draw(52 - 28);
    this.allStacks.push(this.stockPile);

    this.drawAndUpdate();
  }



  drawAndUpdate() {
    for (let i = 0; i < 95; i++)
      for (let j = 0; j < 80; j++)
        this.cardMap[i][j] = null;
    
    this.ctx.fillStyle('#146414');
    this.ctx.fillRect(0, 0, this.canvas.width, this.canvas.height);

    this.drawCardOutlines();

    if (this.drawn.length > 0)
      this.drawn.peek().drawAndPopulate(ctx, this.cardMap);

    for (s in this.allStacks)
      s.draw(this.ctx, this.cardMap, this.stackMap);
    
    if (this.held != null)
      for (c in this.held) {
        c.setX(this.mouseX - c.getX());
      }
  }

  drawCardOutlines() {
    this.ctx.strokeStyle('black');
    this.ctx.strokeRect(30, 30, 100, 180);
    for (let i = 0; i < 4; i++)
      this.ctx.strokeRect(30 + 130 * (3 + i), 30, 100, 180);

    this.ctx.fillStyle('#00ff00');
    this.ctx.ellipse(30 + 50 - 30, 30 + 90 - 30, 60, 60, 0, 0, 0);
    this.ctx.fill();
    this.ctx.fillStyle('#146414');
    this.ctx.ellipse(30 + 50 - 25, 30 + 90 - 25, 50, 50, 0, 0, 0);
    this.ctx.fill();
  }
}