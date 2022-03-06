package conways;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;
import java.util.Scanner;

public class Drawing extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	static final int screenWidth = 400;
	static final int screenHeight = 400;
	static final int screenHalfWidth = screenWidth/2;
	static final int screenHalfHeight = screenHeight/2;
	static final int screenLength = screenWidth*screenHeight;
	
	static int[] currentState, previousState;
	static int step = 0;
	
	public static void main(String[] args) {
		JFrame frame = new JFrame("Mandelbrot Set");
		Drawing drawing = new Drawing();
		drawing.setPreferredSize(new Dimension(screenWidth, screenHeight));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(drawing);
		frame.pack();
		frame.setVisible(true);
		
		currentState = new int[screenLength];
		previousState = new int[screenLength];
		
		currentState[2010] = 1;
		currentState[2011] = 1;
		currentState[2012] = 1;
		currentState[1612] = 1;
		currentState[1211] = 1;
		
		
		// Use scanner to ask for next update
		Scanner scObject = new Scanner(System.in);
		drawNext(drawing.getGraphics());
		askForNext(scObject, drawing.getGraphics());
		scObject.close();

		System.out.println("Terminating...");
		System.exit(0);
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawNext(g);
	}
	
	public static void askForNext(Scanner scObject, Graphics g) {
		int steps = 1;
		String line = scObject.next();
		if (line.equals("End")) return;
		if (line.matches("-?\\d+")) {
			steps = Integer.parseInt(line);
		}
		for (int i = 0; i < steps; i++)
			updateNext();
		drawNext(g);
		System.out.println(step);
		askForNext(scObject, g);
	}
	
	public static void drawNext(Graphics g) {
		//g.setColor(new Color(255,255,255));
		//g.fillRect(screenLength, screenHeight, screenHalfWidth, screenHalfHeight);
		
		for (int j = 1; j < screenHeight - 1; j++) {
			for (int i = 1; i < screenWidth - 1; i++) {
				int cIndex = (j * screenWidth) + i;
				int cell = previousState[cIndex];
				
				if (cell != 0) g.setColor(new Color(0,0,0));
				else g.setColor(new Color(255,255,255));
				g.fillRect(i, j, 1, 1);
			}
		}
	}
	
	public static void updateNext() {
		step++;
		previousState = currentState;
		currentState = new int[screenLength];
		
		for (int j = 1; j < screenHeight - 1; j++) {
			for (int i = 1; i < screenWidth - 1; i++) {
				int cIndex = (j * screenWidth) + i;
				int cell = previousState[cIndex];
				
				int startx = i - 1, starty = j - 1;
				int cellsAround = 0 - cell;
				
				for (int y = starty; y < starty + 3; y++) {
					for (int x = startx; x < startx + 3; x++) {
						int index = (y * screenWidth) + x;
						if (previousState[index] == 1) cellsAround++;
					}
				}
				
				switch(cellsAround) {
					case 0:
					case 1:
						// kill
						currentState[cIndex] = 0;
						break;
					case 2:
						currentState[cIndex] = previousState[cIndex];
						break;
					case 3:
						// birth/live
						currentState[cIndex] = 1;
						break;
					case 4:
					case 5:
					case 6:
					case 7:
					case 8:
						// kill
						currentState[cIndex] = 0;
						break;
				}
			}
		}
	}
}
