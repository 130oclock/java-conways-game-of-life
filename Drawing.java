package conways;

import javax.swing.*;

public class Drawing extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final int screenWidth = 1000;
	static final int screenHeight = 1000;
	static final int screenHalfWidth = screenWidth/2;
	static final int screenHalfHeight = screenHeight/2;
	static final int screenLength = screenWidth*screenHeight;
	
	static int[] currentState, previousState;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mandelbrot Set");
		Drawing drawing = new Drawing();
		drawing.setPreferredSize(new Dimension(screenWidth, screenHeight));
		drawing.addMouseListener(drawing);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawing);
		frame.pack();
		frame.setVisible(true);
		
		currentState = new int[screenLength];
		previousState = new int[screenLength];
	}
	
	public static void updateNext() {
		previousState = currentState;
		currentState = new int[screenLength];
		
		for (int j = 0; j < screenHeight; j++) {
			for (int i = 0; i < screenWidth; i++) {
				int cIndex = (j * screenWidth) + i;
				int cell = previousState[cIndex];
				
			}
		}
	}
}
