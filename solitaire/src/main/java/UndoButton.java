import javax.swing.JButton;
import java.awt.event.*;
import java.util.Stack;

public class UndoButton extends JButton {
	private Stack<GameState> gameStates;

	public UndoButton(String text) {
		super(text);
		gameStates = new Stack<GameState>();
		addActionListener(new Undo());
	}

	public void newState() {
		gameStates.push(new GameState(Solitaire.game.getHomeStacks(),
				Solitaire.game.getMainStacks(), Solitaire.game.getDrawn(),
				Solitaire.game.getToDraw()));
	}
	

	private class Undo implements ActionListener {
		public Undo() {
		}

		public void actionPerformed(ActionEvent arg0) {
			if (!gameStates.empty())
				gameStates.pop().implementStates(Solitaire.game);
		}

	}

}
