
import javax.swing.JButton;
import java.awt.event.*;

public class ResetButton extends JButton {

	public ResetButton(String text) {
		super(text);
	}
  
  public final void start() {
    if (getActionListeners().length < 1)
		  addActionListener(new Reset());
  }
  
  public void stop() {
		removeActionListener(getActionListeners()[0]);
  }
	

	private class Reset implements ActionListener {
		public Reset() {
		}

    @Override
		public void actionPerformed(ActionEvent arg0) {
      Solitaire.game.restartGame();
      Solitaire.undoButton.reset();
		}

	}

}
