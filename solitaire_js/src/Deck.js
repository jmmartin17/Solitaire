class Deck {
  constructor() {
    this.cards = new CardStack();
    for (let i = 0; i < Ranks.length; i++)
      for (let j = 0; j < Suits.length; j++)
        this.cards.push(new Card(Ranks[i], Suits[j]));
  }

  shuffle() {
    for (let i = 0; i < this.cards.length; i++)
      swap(this.cards, i, getRandomInt(this.cards.length) + i);
  }

  draw() {
    return this.cards.pop();
  }

  draw(n) {
    if (n < 1 || this.cards.length == 0)
      return null;
    else {
      let temp = new CardStack();
      for (let i = 0; i < n && (this.cards.length > 0); i++)
        temp.push(cards.pop());
      return temp;
    }
  }

  swap(c, i, j) {
    let temp = c[i];
    c[i] = c[j];
    c[j] = temp;
  }
}