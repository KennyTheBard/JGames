/*
	File Name: Window.java
	Author: Teculescu Octavian
	Date: 24 iulie 2017, 18:15:24
*/
import java.awt.Canvas;
import java.awt.Dimension;
import javax.swing.*;

public class Window extends Canvas {
	
	public static final long serialVersionUID = 142346753128712398L;
	
	public Window(int width, int height, String title, Game game) {
		JFrame frame = new JFrame(title);
		
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		//frame.setUndecorated(true);
		//frame.setVisible(true);
		
		//frame.getContentPane().addMouseListener(listener);
		
		frame.setPreferredSize(new Dimension(width,height));
		frame.setMaximumSize(new Dimension(width,height));
		frame.setMinimumSize(new Dimension(width,height));
		
		// Stop the program at the window's closing
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Resizable windows break a lot of things
		frame.setResizable(false);
		// Window will start in the middle of the screen
		frame.setLocationRelativeTo(null);
		frame.add(game);
		frame.setVisible(true);
		game.start();	
	}	
}
