import java.awt.BorderLayout;

import javax.swing.*;

public class Solitaire {
	public static JFrame frame;
	public static MainPanel game;
	public static UndoButton button;
	public static void main (String[] args) {
		frame = new JFrame("Solitaire");
		game = new MainPanel();
		frame.getContentPane().setLayout(new BorderLayout());
		button = new UndoButton("Undo");
		frame.getContentPane().add(button, BorderLayout.NORTH);
		frame.getContentPane().add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);
	}
	
}