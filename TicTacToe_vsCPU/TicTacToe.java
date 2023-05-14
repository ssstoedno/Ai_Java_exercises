import java.util.Scanner;

public class TicTacToe {
	static State startState;
	static boolean finished;
	
	//TicTacToe game---userPlayer: the choice of the user to be player1 or player2
	static void TicTacToeGame(int userPlayer) {
		if (userPlayer==1) {			
			startState=new State("x","o");
			startState.print();
		}
		else {
			startState=new State("o","x");
			startState.print();
		}
		
		finished=false;
		
		if (userPlayer==1) {
			while(finished==false) {
				startState.USERmove();
				finished=startState.printWinner();
				startState.AImove();
				finished=startState.printWinner();
			}
		}
		else {
			while (finished==false) {
			startState.AImove();
			finished=startState.printWinner();
			startState.USERmove();
			finished=startState.printWinner();
			}
		}
	}
	
	
	
    
    public static void main(String[] args) {
   System.out.print("What move do you want? "
    		+ "The first or the second(enter 1 or 2): ");
    @SuppressWarnings("resource")
	Scanner in=new Scanner(System.in);	
    int pl=in.nextInt();
    
    //start game
    TicTacToeGame(pl);
    
    }
}
