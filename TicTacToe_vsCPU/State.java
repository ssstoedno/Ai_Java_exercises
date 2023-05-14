import java.util.Random;
import java.util.Scanner;

public class State {
	String [][] board=new String[3][3];
	String USERsign;
	String AIsign;
	boolean isAIfirst;
	
	//constructor with signs "x" and "o"---player 1 always "x" and player 2
	//always "o"
	State(String sign1,String sign2) {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				board[i][j]=" ";
			}
		}
		USERsign=sign1;
		AIsign=sign2;
		
		if (AIsign=="x") {
			isAIfirst=true;
		}
		else {
			isAIfirst=false;
		}
	}
	
	//checks if there are available cells on the board[3][3] for a move
	boolean areMovesLeft() {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (board[i][j]==" ") {
					return true;
				}
			}
		}
		return false;
	}
	
	//checks if we have a winner and returns 1 or -1 or 0 if there is no winner
	int winner() {
			for (int row=0;row<3;row++) {
				if(board[row][0].equals("x") 
						&& board[row][1].equals("x") && board[row][2].equals("x")){
					return 1;
				}
				else if (board[row][0].equals("o") 
						&& board[row][1].equals("o") && board[row][2].equals("o")) {
					return -1;
				}
			}
			
			for (int column=0;column<3;column++) {
				if(board[0][column].equals("x") 
						&& board[1][column].equals("x") && board[2][column].equals("x")){
					return 1;
				}
				else if (board[0][column].equals("o") 
						&& board[1][column].equals("o") && board[2][column].equals("o")) {
					return -1;
				}
			}
			
			if(board[0][0].equals("x") 
					&& board[1][1].equals("x") && board[2][2].equals("x")){
				return 1;
			}
			
			else if(board[0][0].equals("o") 
					&& board[1][1].equals("o") && board[2][2].equals("o")){
				return -1;
			}
			
			
			if(board[2][0].equals("x") 
					&& board[1][1].equals("x") && board[0][2].equals("x")){
				return 1;
			}
			
			else if(board[2][0].equals("o") 
					&& board[1][1].equals("o") && board[0][2].equals("o")){
				return -1;
			}
			return 0;
	}
	
	//checks if the move entered by the user is possible
	boolean isMovePossible(int row, int column) {
		if (board[row][column]==" ") {
			return true;
		}
		return false;
	}
	
	//prints the board
	void print(){
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (j==0) {
					System.out.print("|"+board[i][j]+"|");
					continue;
				}
				System.out.print(board[i][j]+"|");
			}
			System.out.println();
			System.out.println("-------");
		}
		System.out.println("---------------------------------");
	}
	
	//checks if the board is empty(no moves made)
	boolean emptyBoard() {
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (board[i][j]!=" ") {
					return false;
				}
			}
		}
		return true;
	}
	
	//checks the board and prints the outcome of the game
	boolean printWinner() {
		int winner=winner();
		if (winner()==1) {
			if (isAIfirst=true) {
				System.out.println("Winner is cpu!");
			}
			else {
				System.out.println("Winner is user!");
			}
			print();
			return true;
		}
		if (winner==-1) {
			if (isAIfirst==true) {
				System.out.println("Winner is user!");
			}
			else {
				System.out.println("Winner is cpu!");
			}
			print();
			return true;
		}
		if (winner==0 && areMovesLeft()==false) {
			System.out.println("DRAW");
			print();
			return true;
		}
		return false;
	}
	
	//minimax used to find the best possible move for the AI with depth and alpha-
	//beta pruning
	int minimax(boolean isMaximizingPlayer,int depth ,int alpha,int beta) {
		int winner=winner();
		
		if(winner==1) {
			return 100-depth;
		}
		if(winner==-1) {
			return -100+depth;
		}
		//term.node draw
		if (areMovesLeft()==false) {
			return 0;
		}
		
		if (isMaximizingPlayer==true) {
			int best=Integer.MIN_VALUE;
			
			for (int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					if (board[i][j]==" " && isAIfirst==true) {
						board[i][j]=AIsign;
					}
					else if(board[i][j]==" " && isAIfirst==false) {
						board[i][j]=USERsign;
					}
					else {
						continue;
					}
						int value=minimax(!isMaximizingPlayer,depth+1,alpha,beta);
						best=Math.max(best,value);
						board[i][j]=" ";
						alpha=Math.max(alpha, best);
						
						if(beta<=alpha) {
							break;
						}
					}
				
				}
			return best;
			}
		else {
			int best=Integer.MAX_VALUE;
			
			for(int i=0;i<3;i++) {
				for (int j=0;j<3;j++) {
					if(board[i][j]==" " && isAIfirst==true) {
						board[i][j]=USERsign;
					}
					else if(board[i][j]==" " && isAIfirst==false) {
						board[i][j]=AIsign;
						
					}
					else {
						continue;
					}
					int value=minimax(!isMaximizingPlayer,depth+1,alpha,beta);
					best=Math.min(best,value);
					board[i][j]=" ";
					beta=Math.min(beta, best);
					
					if(beta<=alpha) {
						break;
					}
				}	
			}
			return best;
		}
	}

	//user move function
	void USERmove() {
		System.out.print("Enter row column(e.g 0 0):");
		@SuppressWarnings("resource")
		Scanner sc=new Scanner(System.in);
		String move=sc.nextLine();
		String[] splited = move.split("\\s+");
		int row=Integer.parseInt(splited[0]);
		int column=Integer.parseInt(splited[1]);
		
		if (isMovePossible(row,column)==false){
			while(isMovePossible(row,column)==false) {
			System.out.println("Enter possible move");
			System.out.print("Enter row column(e.g 0 0):");
			move=sc.nextLine();
			splited = move.split("\\s+");
			row=Integer.parseInt(splited[0]);
			column=Integer.parseInt(splited[1]);
			}
		}
		board[row][column]=USERsign;
		print();
	}
	
	//the move function for the AI
	void AImove() {
			if (emptyBoard()==true) {
			Random rnd=new Random();
			int row=rnd.nextInt(3);
			int column=rnd.nextInt(3);
			board[row][column]=AIsign;
			print();
			return;
		}
		int bestValue;
		if (isAIfirst==true) {
			bestValue=Integer.MIN_VALUE;
		}
		else {
			bestValue=Integer.MAX_VALUE;
		}
		int bestRow=Integer.MIN_VALUE;
		int bestColumn=Integer.MIN_VALUE;
		int moveValue;
		for (int i=0;i<3;i++) {
			for (int j=0;j<3;j++) {
				if (board[i][j]==" ") {
					board[i][j]=AIsign;
					if (isAIfirst==true) {
						moveValue=minimax(false,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
					}
					else {
						moveValue=minimax(true,0,Integer.MIN_VALUE,Integer.MAX_VALUE);
					}
					board[i][j]=" ";
					if (moveValue<bestValue && isAIfirst==false) {
						bestValue=moveValue;
						bestRow=i;
						bestColumn=j;
					}
					else if(moveValue>bestValue && isAIfirst==true) {
						bestValue=moveValue;
						bestRow=i;
						bestColumn=j;
					}
				}
				
			}
		}
		
		board[bestRow][bestColumn]=AIsign;
		print();
	}
}
