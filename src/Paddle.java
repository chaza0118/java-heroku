/*
 * Paddle.java
 * This class contains all the methods to create a paddle
 * 
 * Created by Thicha Thepsongkroh 53270626
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Paddle extends Shape {
	private static final long serialVersionUID = -3733804702867708494L;
	private int width;
	private int height;

	public Paddle() {
		this(0, 0, 10, 50, Color.blue);
	}

	public Paddle(int x, int y, int w, int h, Color c) {
		setLocation(x, y);
		this.width = w;
		this.height = h;
		this.color = c;
	}

	public Paddle(int x, int y, int w, int h) {
		this(x, y, w, h, Color.blue);
	}

	public Paddle(int x, int y) {
		this(x, y, 10, 50, Color.blue);
	}

	public void setColor(Color c) {
		color = c;
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