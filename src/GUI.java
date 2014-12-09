/*
 * GUI.java
 * 
 * This class will create a GUI screen that player can choose
 * either 1 or 2 Players mode
 * 
 * Created by Thicha Thepsongkroh 53270626
 * 
 */

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;

import javax.swing.Timer;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JRadioButton;

public class GUI {
	private JFrame frame;
	private JLabel lblCount;
	private JButton btnStart;
	private JRadioButton rdbtn1Player;
	private JRadioButton rdbtn2Player;
	private int count;
	private int mode;

	public GUI() {
		/* Create new frame */
		frame = new JFrame();
		frame.setBounds(100, 100, 600, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().setBackground(Color.white);
		frame.getContentPane().setBounds(100, 125, 600, 325);
		frame.setTitle("Pong Game by Thicha #53270626");
		frame.setVisible(true);

		final Timer timer = new Timer(1000, null);
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				/* Wait 3 seconds after player click "Start" */
				if (count > 0) {
					lblCount.setText(count + "");
					count--;
				} else {
					/* Create new board */
					timer.stop();
					frame.setVisible(false);
					new Board(mode);
				}
			}
		};
		timer.addActionListener(listener);

		/* Create Start button */
		btnStart = new JButton("Start");
		btnStart.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnStart.setBounds(253, 173, 98, 49);
		btnStart.setVisible(true);
		frame.getContentPane().add(btnStart);
		btnStart.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				count = 2;
				btnStart.setVisible(false);
				rdbtn1Player.setVisible(false);
				rdbtn2Player.setVisible(false);
				lblCount.setVisible(true);
				timer.start();
			}
		});
		
		lblCount = new JLabel("3");
		lblCount.setFont(new Font("Tahoma", Font.PLAIN, 48));
		lblCount.setBounds(287, 99, 32, 49);
		lblCount.setVisible(false);
		frame.getContentPane().add(lblCount);
		
		/* Create buttons for choosing mode */
		rdbtn1Player = new JRadioButton("1 Player");
		rdbtn1Player.setBackground(Color.WHITE);
		rdbtn1Player.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtn1Player.setBounds(193, 89, 118, 49);
		rdbtn1Player.setSelected(true);
		mode = 1;
		frame.getContentPane().add(rdbtn1Player);
		rdbtn1Player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn1Player.isSelected())
				{
					/* 1 player mode */
					rdbtn2Player.setSelected(false);
					mode=1;
				}
			}
		});
		
		rdbtn2Player = new JRadioButton("2 Player");
		rdbtn2Player.setBackground(Color.WHITE);
		rdbtn2Player.setFont(new Font("Tahoma", Font.PLAIN, 20));
		rdbtn2Player.setBounds(325, 89, 118, 49);
		frame.getContentPane().add(rdbtn2Player);
		rdbtn2Player.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdbtn2Player.isSelected())
				{
					/* 2 players mode */
					rdbtn1Player.setSelected(false);
					mode=2;
				}
			}
		});

	}
	


	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GUI(); // Let the constructor do the job

			}
		});
	}
}
