import java.awt.BorderLayout;

import javax.swing.*;

public class Solitaire {
  public static JFrame      frame;
  public static MainPanel   game;
  public static UndoButton  undoButton;
  public static ResetButton resetButton;
    
	public static void main (String[] args) {
		undoButton  = new UndoButton("Undo");
    resetButton = new ResetButton("Restart");
		game        = new MainPanel();
    
		frame = new JFrame("Solitaire");
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(undoButton, BorderLayout.NORTH);
		frame.getContentPane().add(game, BorderLayout.CENTER);
    frame.getContentPane().add(resetButton, BorderLayout.SOUTH);
		frame.pack();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}