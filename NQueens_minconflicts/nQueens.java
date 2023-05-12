import java.util.Random;
import java.util.Scanner;
import java.util.Vector;


public class nQueens {
         static int []queens;
         
         //generate random board with queens         
         static void generateBoard(int n){
        	 queens=new int[n];
        	 for (int i=0;i<n;i++) {
        		  queens[i]=i;
        	  }
        	 
        	 //Fisher-Yates shuffle
        	 Random random =new Random();
        	    for (int i = n - 1; i > 0; i--)
        	    {
        	      int index = random.nextInt(i + 1);
        	      // Simple swap
        	      int crr = queens[index];
        	      queens[index] = queens[i];
        	      queens[i] = crr;
        	    }
          }
         
         //print
         static void printAnswer() {
             for(int row=0;row<queens.length;row++) {
            	 for (int column=0;column<queens.length;column++) {
            		 if(queens[column]==row) {
            		 System.out.print("*");
            		 }
            		 else {
            		 System.out.print("_");
            		 }
            	 }
            	 System.out.println();
             }
         }
         
         
         //+1 number of conflicts if on same row or diagonal
         private static int nmConflicts(int row, int column) {
         	int n=queens.length; 
        	int count=0;
        	
        	for (int i=0;i<n;i++) {
        		
        		//the same column, so skip	
        		if(i==column) {
        			continue;
        		}
        		//on the same row
        		if (queens[i]==row) {
        			count++;
        		}
        		//same diagonal
        		if (row+column==queens[i]+i || row-column==queens[i]-i) {
        			count++;
        		}
        	}
        
        		return count;
         }
         
         static void nQueensSolver() {
        	 int nmCon;
        	 int n=queens.length;
        	 Random random=new Random();
        			 
        	Vector<Integer>worstQueensColumns=new Vector<Integer>();
        	Vector<Integer>rowsWithMinConflicts=new Vector<Integer>();
        	 
        	 while(true) {
        		 //the queen with most conflicts
        		 int worstQueenConflicts=Integer.MIN_VALUE;
        		 for (int column=0;column<n;column++) {
        			 nmCon=nmConflicts(queens[column],column);
        			 if (nmCon==worstQueenConflicts && nmCon!=0) {
	        			 worstQueensColumns.add(column);
	        		 }
	        		 else if(nmCon>worstQueenConflicts && nmCon!=0) {
	        			 worstQueenConflicts=nmCon;
	        			 worstQueensColumns.clear();
	        			 worstQueensColumns.add(column);
	        		 }
	        	 }
        		 //no conflicts so result
	        	 if (worstQueensColumns.isEmpty()) {
	        		 printAnswer();
	        		 return;
	        	 }
	        	 //random queen from the worst queens
	        	 int worstQueenColumn=worstQueensColumns.
	        			 get(random.nextInt(worstQueensColumns.size()));
	        	 
	       	 
	        	 //along the column to find cell with least conflicts
	        	 int rowMinConflicts=Integer.MAX_VALUE;
	        	 for (int row=0;row<n;row++) {
	        		 nmCon=nmConflicts(row,worstQueenColumn);
	        		 if (nmCon==rowMinConflicts) {
	        			 rowsWithMinConflicts.add(row);
	        		 }
	        		 else if(nmCon<rowMinConflicts) {
	        			 rowMinConflicts=nmCon;
	        			 rowsWithMinConflicts.clear();
	        			 rowsWithMinConflicts.add(row);
	        		 }
	        	 }
	        	 
	        	 //move the queen
	        	 if (!rowsWithMinConflicts.isEmpty()) {
	        		 queens[worstQueenColumn]=rowsWithMinConflicts.
	        				 get(random.nextInt(rowsWithMinConflicts.size()));
	        	 }
	        	 
	        	
                rowsWithMinConflicts.clear();
	        	worstQueensColumns.clear();
        	 }
	     }
         
         
         public static void main(String[] args) {
        	 System.out.print("Enter n: ");
        	 @SuppressWarnings("resource")
			Scanner in=new Scanner(System.in);
        	 int n=in.nextInt();
             nQueens.generateBoard(n);
             nQueens.nQueensSolver();
         }
}	
