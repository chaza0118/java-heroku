/*
 * Shape.java
 * This class contains all the methods for every shape.
 * 
 * Created by Thicha Thepsongkroh 53270626
 * 
 */

import java.awt.Color;

import javax.swing.JPanel;

public abstract class Shape extends JPanel {
	private static final long serialVersionUID = -4161599535242513201L;
	public Color color;

	public Shape() {
		this(Color.blue);
		setLocation(0, 0);
		setSize(1, 1);
	}

	public Shape(Color c) {
		color = c;
		setLocation(0, 0);
		setSize(1, 1);
	}

	public Shape(int s) {
		this(Color.blue);
		setSize(s, s);
	}

	public Shape(int x, int y) {
		this(Color.blue);
		setLocation(x, y);
	}

	public Shape(int x, int y, int s) {
		this(x, y);
		setSize(s, s);
	}

	public Shape(int x, int y, int s, Color c) {
		this(x, y, s);
		this.color = c;
	}

}
