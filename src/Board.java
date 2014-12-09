/*
 * Board.java
 * 
 * This class will create a Board for a Pong game.
 * This class contains all required methods and components.
 * 
 * Created by Thicha Thepsongkroh 53270626
 * 
 */

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

public class Board {
	public JFrame frame;
	public JInternalFrame internalFrame;
	private JButton button;
	private JLabel lblScore1;
	private JLabel lblScore2;

	private Paddle paddle1;
	private Paddle paddle2;

	private Line top;
	private Line bottom;
	private Line left;
	private Line right;
	private Line mid;

	private Ball ball;

	private boolean keyW;
	private boolean keyS;
	private boolean keyUp;
	private boolean keyDown;
	private int speedX = 1; // Speed of a ball in horizontal direction
	private int speedY = 1; // Speed of a ball in vertical direction
	private int score1 = 0;
	private int score2 = 0;
	private int dirX = 1;
	private int dirY = 1;
	private int locX;
	private int locY;
	private int paddleSpeed = 20; // Speed of a paddle
	private int count;
	private Dimension size;

	public Board(final int mode) {
		/* Create a frame */
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setBounds(100, 125, 600, 325);
		frame.setTitle("Pong Game by Thicha #53270626");
		frame.setVisible(true);

		/* Get content pane dimension */
		size = frame.getContentPane().getSize();
		final int w = size.width;
		final int h = size.height;

		/* Create new paddle for player1 */
		paddle1 = new Paddle(10, h / 2 - 25);
		paddle1.setBounds(paddle1.getX(), paddle1.getY(), paddle1.getWidth(),
				paddle1.getHeight());
		frame.getContentPane().add(paddle1);

		/* Create new paddle for player2 */
		paddle2 = new Paddle(w - 20, h / 2 - 25);
		paddle2.setColor(Color.red);
		paddle2.setBounds(paddle2.getX(), paddle2.getY(), paddle2.getWidth(),
				paddle2.getHeight());
		frame.getContentPane().add(paddle2);

		/* Create an invisible button that check input from keyboard */
		button = new JButton();
		button.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				/* If player press 'W' or 'S' */
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					keyW = true;
					break;
				case KeyEvent.VK_S:
					keyS = true;
					break;
				}
				/* For 2 Players mode */
				if (mode == 2) {
					/* If player press 'Up' or 'Down' */
					switch (e.getKeyCode()) {

					case KeyEvent.VK_UP:
						keyUp = true;
						break;
					case KeyEvent.VK_DOWN:
						keyDown = true;
						break;
					}
				}
				movePaddle();

			}

			/* If player release the key */
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_W:
					keyW = false;
					break;
				case KeyEvent.VK_S:
					keyS = false;
					break;
				}

				/* For 2 Players mode */
				if (mode == 2) {
					switch (e.getKeyCode()) {
					case KeyEvent.VK_UP:
						keyUp = false;
						break;
					case KeyEvent.VK_DOWN:
						keyDown = false;
						break;
					}
				}
			}

		});
		frame.getContentPane().add(button);

		/* Create new ball */
		ball = new Ball(282, 145, 10, Color.black);
		ball.setBounds(ball.getX(), ball.getY(), ball.getDiameter(),
				ball.getDiameter());
		frame.getContentPane().add(ball);
		ball.setVisible(true);

		/* Create all the border and middle line */
		bottom = new Line(0, h - 5, w, 5);
		bottom.setBounds(bottom.getX(), bottom.getY(), bottom.getWidth(),
				bottom.getHeight());
		frame.getContentPane().add(bottom);

		top = new Line(0, 0, w, 5);
		top.setBounds(top.getX(), top.getY(), top.getWidth(), top.getHeight());
		frame.getContentPane().add(top);

		left = new Line(0, 0, 5, h);
		left.setBounds(left.getX(), left.getY(), left.getWidth(),
				left.getHeight());
		frame.getContentPane().add(left);

		right = new Line(w - 5, 0, 5, h);
		right.setBounds(right.getX(), right.getY(), right.getWidth(),
				right.getHeight());
		frame.getContentPane().add(right);

		mid = new Line(w / 2 - 2, 0, 5, h);
		mid.setBounds(mid.getX(), mid.getY(), mid.getWidth(), mid.getHeight());
		frame.getContentPane().add(mid);

		/* Create labels to show score */
		lblScore1 = new JLabel("0");
		lblScore1.setHorizontalAlignment(SwingConstants.LEFT);
		lblScore1.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblScore1.setBounds(226, 16, 64, 69);
		lblScore1.setVisible(true);

		lblScore2 = new JLabel("0");
		lblScore2.setHorizontalAlignment(SwingConstants.LEFT);
		lblScore2.setFont(new Font("Tahoma", Font.BOLD, 48));
		lblScore2.setBounds(336, 16, 64, 69);
		lblScore2.setVisible(true);

		frame.getContentPane().add(lblScore1);
		frame.getContentPane().add(lblScore2);

		ActionListener listener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				/* If a player can't receive the ball, restart and wait a bit */
				if (count > 0) {
					count--;
				}

				else {
					/* Player 2 return the ball */
					if (ball.getX() + ball.getDiameter() >= paddle2.getX()
							&& ball.getY() + ball.getDiameter() >= paddle2
									.getY()
							&& ball.getY() <= paddle2.getY()
									+ paddle2.getHeight()) {
						/* Change direction to left */
						dirX = -1 * speedX;
					} 
					else if (ball.getX() + ball.getDiameter() >= paddle2
							.getX() + paddle2.getWidth()) {
						/* If player 2 can't return the ball, add score to player 1 */
						score1++;
						lblScore1.setText(score1 + "");
						/* Reset every objects */
						dirX = speedX;
						count = 200;
						paddle1.setLocation(10, h / 2 - 25);
						paddle2.setLocation(w - 20, h / 2 - 25);
						ball.setLocation(282, 145);
					}
					/* Player 1 return the ball */
					if (ball.getX() <= paddle1.getX() + paddle1.getWidth()
							&& ball.getY() + ball.getDiameter() >= paddle1
									.getY()
							&& ball.getY() <= paddle1.getY()
									+ paddle1.getHeight()) {
						/* Change the direction to right */
						dirX = speedX;
					} else if (ball.getX() <= paddle1.getX()) {
						/* If player 1 can't return the ball, add score to player 2 */
						score2++;
						lblScore2.setText(score2 + "");
						/* Reset every objects */
						dirX = -1 * speedX;
						count = 200;
						paddle1.setLocation(10, h / 2 - 25);
						paddle2.setLocation(w - 20, h / 2 - 25);
						ball.setLocation(282, 145);
					}

					/* If the circle hit the bottom border */
					if (ball.getY() + ball.getDiameter() >= h)
						/* Change direction to up */
						dirY = -1 * speedY;
					/* If the circle hit the top border */
					else if (ball.getY() <= 0)
						/* Change direction to down */
						dirY = speedY;

					/* Set a coordinate of the circle */
					locX = (ball.getX() + dirX);
					locY = (ball.getY() + dirY);

					/* Set a location of the circle */
					ball.setLocation(locX, locY);

					/* Start AI if it's 1 Player mode */
					if (mode == 1) {
						AI();
					}
				}
			}
		};
		Timer displayTimer = new Timer(5, listener);
		displayTimer.start();

	}

	/* Move paddle when player press the key */
	public void movePaddle() {
		if (keyW == true && paddle1.getY() >= 20) {
			paddle1.setLocation(paddle1.getX(), paddle1.getY() - paddleSpeed);
		}
		if (keyS == true && paddle1.getY() <= 230) {
			paddle1.setLocation(paddle1.getX(), paddle1.getY() + paddleSpeed);
		}
		if (keyUp == true && paddle2.getY() >= 20) {
			paddle2.setLocation(paddle2.getX(), paddle2.getY() - paddleSpeed);
		}
		if (keyDown == true && paddle2.getY() <= 230) {
			paddle2.setLocation(paddle2.getX(), paddle2.getY() + paddleSpeed);
		}
	}

	/* AI for 1 Player mode */
	public void AI() {
		if (paddle2.getY() > ball.getY() && paddle2.getY() > 20) {
			paddle2.setLocation(paddle2.getX(), paddle2.getY() - 1);
		} else if (paddle2.getY() < ball.getY()
				&& paddle2.getY() + paddle2.getHeight() < 290) {
			paddle2.setLocation(paddle2.getX(), paddle2.getY() + 1);
		}
	}

}
