import java.util.*;

public class XO_game {

	public static void main(String[] args) {
		Scanner ip = new Scanner(System.in);
		String[][] board = { { ".", ".", "." }, { ".", ".", "." }, { ".", ".", "." } }; // board initialization
		boolean win = false, draw = false, ctrl = true;	// conditions for win, draw, and control flow
		int[] players = { 1, 2 }; // players 1 and 2
		int hx = 0; // toggle value to switch between players
		String[] tiles = { "X", "O" }; // player tiles X and O

		// Display the initial board
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " "); // printing the initial board
			}
			System.out.println(); // next row
		}

		while (ctrl) {
			// Indicate whose turn it is
			System.out.println("Player " + players[hx] + "'s turn");
			int x = ip.nextInt();
			int y = ip.nextInt();

			// Check for out-of-bounds or occupied tile
			boolean cheat = check(x, y);
			if (!cheat) {
				cheat = check(board[x][y]);
			}

			// Ask for valid inputs while there's a cheat (invalid input)
			while (cheat) {
				x = ip.nextInt();
				y = ip.nextInt();
				cheat = check(x, y);
				if (!cheat) {
					cheat = check(board[x][y]);
				}
			}

			// Place the current player's tile
			board[x][y] = tiles[hx];

			// Print the updated board after each move
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					System.out.print(board[i][j] + " ");
				}
				System.out.println(); // new row
			}

			// Check if there's a win on diagonals
			if (board[0][0].equals(board[1][1]) && board[0][0].equals(board[2][2]) && !board[0][0].equals(".")) {
				win = true;
			} else if (board[2][0].equals(board[1][1]) && board[1][1].equals(board[0][2]) && !board[2][0].equals(".")) {
				win = true;
			}

			// Check for a win on rows and columns
			if (!win) {
				for (int i = 0; i < 3; i++) {
					if (board[i][0].equals(board[i][1]) && board[i][0].equals(board[i][2]) && !board[i][0].equals(".")) {
						win = true;
						break;
					}
					if (board[0][i].equals(board[1][i]) && board[0][i].equals(board[2][i]) && !board[0][i].equals(".")) {
						win = true;
						break;
					}
				}
			}

			// Announce the winner if found
			if (win) {
				System.out.println("Player " + players[hx] + " wins!");
				break;
			}

			// Check for a draw (i.e., no more empty spots)
			boolean flag = false; // flag for tracking empty spaces
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					if (board[i][j].equals(".")) { // if there's still an empty spot
						draw = false;
						flag = true; // exit the draw check
						break;
					}
				}
				if (flag) break; // exit outer loop if an empty spot is found
			}

			// Announce draw if no empty spots are left
			if (!flag) {
				System.out.println("Draw");
				break;
			}

			// Toggle the player using your original logic
			if (hx == 0) {
				hx++; // switch to player 2
			} else {
				hx--; // switch back to player 1
			}
		}

		ip.close(); // close the scanner
	}

	// Check if the tile is already occupied
	public static boolean check(String c) {
		if (!c.equals(".")) {
			System.out.println("Invalid move, try again");
			return true;
		}
		return false;
	}

	// Check if the input is within bounds
	public static boolean check(int x, int y) {
		if (x > 2 || y > 2 || x < 0 || y < 0) {
			System.out.println("Invalid move, try again");
			return true;
		}
		return false;
	}
}
