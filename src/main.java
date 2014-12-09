import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 This program is a number guessing game. It will random number and ask user to
 * guess. If the user guess the wrong number, it will print higher or lower. If
 * it's the right number, it will wring correct!. User can guess 6 times. If the
 * user can't guess the correct number in 6 times then it will print
 * "You Lose!", otherwise it will print "You Win!". Statistics such as number of
 * games, number of win, shortest guess will be kept in savescore.txt
 * 
 * @version 1.0 18 Feb 2013 * @author Thicha Thepsongkroh 53270626
 */

public class main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int randomNum = 0;		//Random number
		int guess = 0;			//Number of guesses
		int win = 0;			//Win Flag
		int i = 0;
		/* Variables for statistics */
		int numGame = 0;
		int shortGuess = 0;
		int numWin = 0;	
		String s = null;
		Scanner sc;
		
		/* Read statistics from savescore.txt */
		try {
			sc = new Scanner(new File("savescore.txt")).useDelimiter("\\s+");

			while (sc.hasNext()) {
				sc.next();
				numGame = sc.nextInt();
				sc.next();
				numWin = sc.nextInt();
				sc.next();
				shortGuess = sc.nextInt();
			}
			sc.close();
		} catch (FileNotFoundException e1) {
			/* Create new file if file not found */
			PrintWriter pwC;
			try {
				pwC = new PrintWriter(new FileWriter("savescore.txt"));
				pwC.println("Played: 0 Won: 0 FastestGuess: 0");
				pwC.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		/* Print the statistics */
		System.out.println("Played:" + numGame + ", Won:" + numWin
				+ ", Fastest Guess:" + shortGuess);
		
		/* Random a number */
		randomNum = 1 + (int) (Math.random() * ((100 - 1) + 1));
		//System.out.println(randomNum);
		
		/* Ask user to guess the number */
		for (i = 1; i < 7; i++) {
			System.out.print("#" + i + " Guess: ");
			BufferedReader bufferRead = new BufferedReader(
					new InputStreamReader(System.in));
			try {
				s = bufferRead.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			guess = Integer.parseInt(s);
			
			/* If the user guess correctly */
			if (guess == randomNum) {
				System.out.println("Correct!");
				win = 1;
				if ((i < shortGuess) || (shortGuess == 0)) {
					shortGuess = i;
				}
				i = 7;
				
			/* If the guess number is wrong */
			} else if (guess < randomNum) {
				System.out.println("Higher!");
			} else if (guess > randomNum) {
				System.out.println("Lower!");
			}
		}
		
		/* Print if the user wins */
		if (win == 1) {
			System.out.println("You Win!");
			numWin++;

		}
		
		/* Print if the user loses */
		if (win == 0) {
			System.out.println("You Lose!");
			System.out.println("The correct answer is: " + randomNum);
		}

		/* Increment number of played games */
		numGame++;

		/* Update statistics in to savescore.txt */
		PrintWriter pwC;
		try {
			pwC = new PrintWriter(new FileWriter("savescore.txt"));
			pwC.println("Played: " + numGame + " Won: " + numWin
					+ " FastestGuess: " + shortGuess);
			pwC.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
