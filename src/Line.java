/*
 * Line.java
 * This class contains all the methods to create a line or a rectangle
 * 
 * Created by Thicha Thepsongkroh 53270626
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Line extends Shape {
	private static final long serialVersionUID = -3733804702867708494L;
	private int width;
	private int height;

	public Line() {
		this(0, 0, 10, 50, Color.gray);
	}

	public Line(int x, int y, int w, int h, Color c) {
		setLocation(x, y);
		this.width = w;
		this.height = h;
		this.color = c;
	}

	public Line(int x, int y, int w, int h) {
		this(x, y, w, h, Color.gray);
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(color);
		g2d.fillRect(0, 0, width, height);
	}

}