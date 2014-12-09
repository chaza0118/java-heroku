/*
 * Ball.java
 * This class contains all the methods to create a circle
 * 
 * Created by Thicha Thepsongkroh 53270626
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class Ball extends Shape {
	private static final long serialVersionUID = -629144081400642128L;
	private Color color;
	private int diameter;

	public Ball() {
		this(0, 0, 10, Color.blue);
	}

	public Ball(int x, int y, int d, Color c) {
		setLocation(x, y);
		this.diameter = d;
		this.color = c;
	}

	public Ball(int x, int y, int d) {
		this(x, y, d, Color.blue);
	}

	public Ball(int x, int y) {
		this(x, y, 10, Color.blue);
	}

	public void setColor(Color c) {
		color = c;
	}

	public int getDiameter() {
		return diameter;
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;

		g2d.setColor(color);
		g2d.fillOval(0, 0, diameter, diameter);
	}

}
