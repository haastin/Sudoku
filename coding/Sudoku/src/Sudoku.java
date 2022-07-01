import java.util.Scanner;
import java.lang.Math;
import java.lang.System;

public class Sudoku {

	int[][] map;

	boolean isValid(int guess, int guess_x, int guess_y, int[][] map) {

		// check each row and column our guess is in to see if any box also in that
		// row/column contains our guess

		for (int i = 0; i < 9; i++) {

			if (map[guess_x][i] == guess) { // check row
				return false;
			}
			if (map[i][guess_y] == guess) { // check column
				return false;
			}

			/*
			 * check the 3x3 box the number is in
			 * 
			 * So to make sure we know what indices to check we need to know exactly where
			 * the guess' x and y coordinates are within the 3x3 box.
			 * You could create a new array of the 9 elements in the box and loop through
			 * it, but that would cause us to use more memory. It
			 * doesn't help with speed either, since both creating a new array and looping
			 * through it versus finding how far to the left/right or
			 * up/down we have to go from our guess inside the box can be implemented in
			 * constant time since we know there will always be 9 elements
			 * in the 3x3 box. So, we choose the latter option for memory efficiency.
			 * 
			 */

			// Case: x is at the left most edge of the box

			if (guess_x % 3 == 0) {

				if (guess_y % 3 == 0) { // Case: y is at the top of the box

					for (int y = guess_y; y < guess_y + 3; y++) {

						for (int x = guess_x; x < guess_x + 3; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}
					}
				}

				else if ((guess_y + 1) % 3 == 0) { // Case: y is at the bottom of the box

					for (int y = guess_y - 2; y < guess_y + 1; y++) {

						for (int x = guess_x; x < guess_x + 3; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}

					}

				}

				else { // Case: y is at the middle of the box

					for (int y = guess_y - 1; y < guess_y + 2; y++) {

						for (int x = guess_x; x < guess_x + 3; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}

					}

				}
			}

			// Case: x is at the right most edge of the box

			else if ((guess_x + 1) % 3 == 0) {

				if (guess_y % 3 == 0) { // Case: y is at the top of the box

					for (int y = guess_y; y < guess_y + 3; y++) {

						for (int x = guess_x - 2; x < guess_x + 1; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}
					}
				}

				else if ((guess_y + 1) % 3 == 0) { // Case: y is at the bottom of the box

					for (int y = guess_y - 2; y < guess_y + 1; y++) {

						for (int x = guess_x - 2; x < guess_x + 1; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}

					}

				}

				else { // Case: y is at the middle of the box

					for (int y = guess_y - 1; y < guess_y + 2; y++) {

						for (int x = guess_x - 2; x < guess_x + 1; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}

					}

				}
			}

			// Case: x is at the middle of the box

			else {

				if (guess_y % 3 == 0) { // Case: y is at the top of the box

					for (int y = guess_y; y < guess_y + 3; y++) {

						for (int x = guess_x - 1; x < guess_x + 2; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}
					}
				}

				else if ((guess_y + 1) % 3 == 0) { // Case: y is at the bottom of the box

					for (int y = guess_y - 2; y < guess_y + 1; y++) {

						for (int x = guess_x - 1; x < guess_x + 2; x++) {

							if (map[x][y] == guess) { // Case: check specific box
								return false;
							}

						}

					}

				}

				else { // Case: y is at the middle of the box

					for (int y = guess_y - 1; y < guess_y + 2; y++) {

						for (int x = guess_x - 1; x < guess_x + 2; x++) {

							if (map[x][y] == guess) { // check specific box
								return false;
							}

						}

					}

				}
			}

		}

		return true;

	}

	boolean isComplete(int[][] map) {

		for (int x = 0; x < 9; x++) {
			for (int y = 0; y < 9; y++) {
				if (map[x][y] == 0) {
					return false;
				}
			}
		}

		return true;
	}

	public Sudoku() {

		map = new int[9][9];

		for (int x = 0; x < 9; x++) {

			for (int y = 0; y < 9; y++) {

				map[x][y] = 0;
			}
		}

	}

	static int random_x() {

		double f = Math.random() * 10;

		int x = (int) f; // must be in this order or else the double is casted to an int before it is
							// multiplied by 10

		// all indices must be between 0-8

		while (x > 8) {

			x = (int) Math.random() * 10;

		}

		return x;
	}

	static int random_y() {

		double f = Math.random() * 10;
		int y = (int) f;

		// all indices must be between 0-8

		while (y > 8) {

			y = (int) Math.random() * 10;

		}

		return y;
	}

	static int random_number() {

		double f = Math.random() * 10;
		int starting_number = (int) f;

		// all numbers must be between 1-9, inclusive, so if the first random number
		// isn't, keep trying

		while (starting_number == 0) {

			f = Math.random() * 10;
			;
			starting_number = (int) f;

		}

		return starting_number;
	}

	void print_Map(int[][] map) {

		for (int y = 0; y < 9; y++) {
			for (int x = 0; x < 9; x++) {

				System.out.print(map[x][y]);

				if ((x + 1) % 3 == 0) {
					System.out.print(" ");
				}

			}
			if ((y + 1) % 3 == 0) {
				System.out.println();
			}
			System.out.println();
		}

	}

	public static void main(String[] args) {

		while (true) { // this allows us to play as many maps as we want without having to re-execute
						// the file to play

			Sudoku sudoku = new Sudoku();

			/*
			 * For a sudoku to have one unique solution, there must be 17 starting numbers
			 * in the sudoku. However, we will populate these 17
			 * numbers randomly, and at random spots in the map, so that each map will be
			 * different than the one before it (most likely).
			 * 
			 */
			for (int i = 0; i < 17; i++) {

				while (true) {

					int x = random_x();
					int y = random_y();
					int num = random_number();

					if (sudoku.isValid(num, x, y, sudoku.map)) {

						sudoku.map[x][y] = num;
						break;
					}

				}

			}

			System.out.println("Here is your starting map:\n");

			sudoku.print_Map(sudoku.map);

			while (!sudoku.isComplete(sudoku.map)) {

				System.out.println("Enter the value you'd like to guess (between 1-9):");
				Scanner scan = new Scanner(System.in);
				int num = scan.nextInt();
				while (num < 1 || num > 9) {
					System.out.println("That is not a valid number to guess.");
					System.out.println("Enter the value you'd like to guess (between 1-9):");
					num = scan.nextInt();
				}

				System.out.println(
						"Enter the x coordinate of the value you'd like to guess (between 0-8, where the left most part of the map is 0, and the right most part is 8):");
				int x = scan.nextInt();
				while (x < 0 || x > 8) {
					System.out.println("That is not a valid coordinate for your guess.");
					System.out.println(
							"Enter the x coordinate of the value you'd like to guess (between 0-8, where the left most part of the map is 0, and the right most part is 8):");
					x = scan.nextInt();
				}

				System.out.println(
						"Enter the y coordinate of the value you'd like to guess (between 0-8, where the top of the map is 0, and the bottom is 8):");
				int y = scan.nextInt();
				while (y < 0 || y > 8) {
					System.out.println("That is not a valid coordinate for your guess.");
					System.out.println(
							"Enter the y coordinate of the value you'd like to guess (between 0-8, where the top of the map is 0, and the bottom is 8):");
					y = scan.nextInt();
				}

				if (sudoku.isValid(num, x, y, sudoku.map)) {
					System.out.println("Your guess is correct!");
					System.out.println("Here's the updated map:\n");
					sudoku.map[x][y] = num;
					sudoku.print_Map(sudoku.map);
				}

				else {
					System.out.println("Your guess is not correct.");
					System.out.println("Take a fresh look at the current state of the map:\n");
					sudoku.print_Map(sudoku.map);
				}
			}

			System.out.println("Congratulations! You've solved this sudoku!");
			System.out.println("Would you like to play another round? Please type yes or no below.");
			Scanner again = new Scanner(System.in);
			String result = again.nextLine();
			result = result.toLowerCase();

			while (!result.equals("no") && !result.equals("yes")) {
				System.out.println("Your answer is unclear. Please try again.");
				System.out.println("Would you like to play another round? Please type yes or no below.");
				result = again.nextLine();
				result = result.toLowerCase();
			}

			if (result.equals("no")) {

				System.out.println("Thank you for playing! Exiting now...");
				System.exit(0);
			} else {
				System.out.println("Good luck on your next round!");
				System.out.println();
			}

			// Future improvements:
			// make GUI for map and user IO instead of stdout
			// Should be MUCH faster for a user to guess
			// Implement algo to solve sudoku if user wants the solution before finishing
			// Hints?
			// Timer?

		}

	}

	// Rough outline:

	// initialize map
	// Display the current state of the map (this will be a pre-GUI
	// build)
	// Ask the user for input
	// Check if the guess is correct by the rules of sudoku
	// Track whether the map is complete or not

}
