import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class PvP {

		// Global List For Players Placements
		
		static ArrayList<Integer> player1Positions = new ArrayList<Integer>();
		static ArrayList<Integer> player2Positions = new ArrayList<Integer>();
		static String player1Name, player2Name;
		static int playerPosition;
		static int player1Score = 0;
		static int player2Score = 0;
		static java.util.Date date = new java.util.Date();    

		
		@SuppressWarnings("resource")
		public static void game() throws IOException {
			
			// Create text file for record
			FileWriter fw = new FileWriter("C:\\temp\\highscore.txt");
			PrintWriter pw = new PrintWriter(fw);
			
			// Introduce Program and Get Players Information
			Scanner scannerName = new Scanner(System.in);
			System.out.println("Welcome to Player vs. Player!");
			System.out.println("Player 1 please type your name: ");
			player1Name = scannerName.nextLine();
			System.out.println("Player 2 please type your name: ");
			player2Name = scannerName.nextLine();
			
			// Get The Grid Shape Using 2D Array 
			
			char [] [] gameGrid = {{ '1', '|', '2', '|', '3'},
					 {'-', '+', '-', '+', '-'},
					 { '4', '|', '5', '|', '6'},
					 {'-', '+', '-', '+', '-'},
					 { '7', '|', '8', '|', '9'}};
		
			printBoard(gameGrid);
			
			// Set Scanner
			
			while (true) {
				Scanner playerInput = new Scanner (System.in);
				
				// Get Player 1 Input
				System.out.println(player1Name + " Enter your placement (1-9)");
				playerPosition = playerInput.nextInt();
				while (player1Positions.contains(playerPosition) || player2Positions.contains(playerPosition) || playerPosition < 1 || playerPosition > 9)
				{
					System.out.println("Invalid Position! Enter A New Position:");
					playerPosition = playerInput.nextInt();
				}
				placement(gameGrid, playerPosition, "player1");
				printBoard(gameGrid);
				String result = winCondition();
				if (result.length() > 0) {
					System.out.println(result);
					player1Score++;
					pw.println("High Scores:");
					pw.println(player1Name + " is the winner of the last round!");
					pw.println(player1Name + " has won " + player1Score + " times!");
					pw.println("Date: " + date);
					pw.close();
					break;

				}

				
				// Get Player 2 Input
				
				System.out.println(player2Name + " Enter your placement (1-9)");
				playerPosition = playerInput.nextInt();
				while (player1Positions.contains(playerPosition) || player2Positions.contains(playerPosition) || playerPosition < 1 || playerPosition > 9)
				{
					System.out.println("Invalid Position! Enter A New Position:");
					playerPosition = playerInput.nextInt();
				}
				placement(gameGrid, playerPosition, "player2");
				printBoard(gameGrid);
				result = winCondition();
				if (result.length() > 0) {
					System.out.println(result);
					player2Score++;
					pw.println("High Scores:");
					pw.println(player2Name + " is the winner of the last round!");
					pw.println(player2Name + " has won " + player2Score + " times!");
					pw.println("Date: " + date);
					pw.close();
					break;
				}
			}
		}


		// Method to print Board 
		
		public static void printBoard(char[][] gameGrid)
		{
			for(char [] row : gameGrid) 
			{
				for(char c: row) 
				{
					System.out.print(c);
				}
				System.out.println();
			}
		}
		
		// Method to Use Player Input to Modify The Grid
		
		public static void placement(char[][] gameGrid, int playerPosition, String user) {
			
			// Set the symbols for player 1 and player 2
			char symbol = 'X';
			
			if(user.equals("player1")) 
			{
				symbol = 'X';
				player1Positions.add(playerPosition);
			} else if(user.equals("player2")) 
			{
				symbol = 'O';
				player2Positions.add(playerPosition);

			}
			
			switch(playerPosition) 
			{
				case 1:
					gameGrid[0][0] = symbol;
					break;
				case 2:
					gameGrid[0][2] = symbol;
					break;
				case 3:
					gameGrid[0][4] = symbol;
					break;
				case 4:
					gameGrid[2][0] = symbol;
					break;
				case 5:
					gameGrid[2][2] = symbol;
					break;
				case 6:
					gameGrid[2][4] = symbol;
					break;
				case 7:
					gameGrid[4][0] = symbol;
					break;
				case 8:
					gameGrid[4][2] = symbol;
					break;
				case 9:
					gameGrid[4][4] = symbol;
					break;
			}
		}
		
		// Method to verify the winner
		
		@SuppressWarnings("rawtypes")
		public static String winCondition() 
		{
			List topRow = Arrays.asList(1, 2, 3);
			List midRow = Arrays.asList(4, 5, 6);
			List lowRow = Arrays.asList(7, 8, 9);
			List leftCol = Arrays.asList(1, 4, 7);
			List midCol = Arrays.asList(2, 5, 8);
			List rightCol = Arrays.asList(3, 6, 9);
			List diag1 = Arrays.asList(1, 5, 9);
			List diag2 = Arrays.asList(7, 5, 3);

			List<List> winning = new ArrayList<List>();
			winning.add(topRow);
			winning.add(midRow);
			winning.add(lowRow);
			winning.add(leftCol);
			winning.add(midCol);
			winning.add(rightCol);
			winning.add(diag1);
			winning.add(diag2);
			
			for(List l: winning) 
			{
				if(player1Positions.containsAll(l))
				{
					player1Positions.clear();
					player2Positions.clear();
					return player1Name + " Wins!";
				} 
				else if(player2Positions.containsAll(l))
				{
					player1Positions.clear();
					player2Positions.clear();
					return player2Name +" Wins!";
				} 
			}
			if(player1Positions.size() + player2Positions.size() == 9 )
			{
				player1Positions.clear();
				player2Positions.clear();
				return "It's a tie!";
			}
			return "";
		}
	
		}
	
	
