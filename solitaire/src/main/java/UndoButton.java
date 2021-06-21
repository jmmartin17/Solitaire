import javax.swing.JButton;
import java.awt.event.*;
import java.util.Stack;

public class UndoButton extends JButton {
	private Stack<GameState> gameStates;

	public UndoButton(String text) {
		super(text);
    reset();
	}
  
  public final void reset() {
		gameStates = new Stack<>();
  }
  
  public final void start() {
    if (getActionListeners().length < 1)
		  addActionListener(new Undo());
  }
  
  public void stop() {
		removeActionListener(getActionListeners()[0]);
  }

	public void newState() {
		gameStates.push(new GameState(Solitaire.game.getHomeStacks(),
				Solitaire.game.getMainStacks(), Solitaire.game.getDrawn(),
				Solitaire.game.getToDraw()));
	}
	

	private class Undo implements ActionListener {
		public Undo() {
		}

    @Override
		public void actionPerformed(ActionEvent arg0) {
			if (!gameStates.empty())
				gameStates.pop().implementStates(Solitaire.game);
		}

	}

}
