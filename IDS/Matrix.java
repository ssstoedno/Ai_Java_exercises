import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Matrix {
	
	public static String[][] matrix;
	static int startRow;
	static int startColumn;
	static int endRow;
	static int endColumn;
	
	
	static void MatrixBuilder(int n) 
	{
		matrix=new String[n][n];
		for (int i=0;i<n;i++) {
   		for (int j=0;j<n;j++) {
   		matrix[i][j]="1";
   		}
   	}
	}
	
	
	private static int randomNumberInRange(int min, int max) 
	{
		Random random = new Random();
		return random.nextInt((max-min)+1)+min;
	}
	
    
    static void matrixRand(int k,int n){
    	int row;
    	int column;
    	for (int i=0;i<k;i++) {
    		row=randomNumberInRange(0,n-1);
    		column=randomNumberInRange(0,n-1);
    		if (matrix[row][column]=="0" || (row==startRow && column==startColumn)
    				                     || (row==endRow && column==endColumn)) {
    			while (matrix[row][column]=="0" || (row==startRow && column==startColumn)
	                                            || (row==endRow && column==endColumn) ) {
    				row=randomNumberInRange(0,n-1);
    	    		column=randomNumberInRange(0,n-1);
    			}
    			matrix[row][column]="0";
    		}
    		else {
    			matrix[row][column]="0";
    		}
    	}
    	
    }
    
    
    static void printMatrix() {
    	for (int i=0;i<matrix.length;i++) {
    		for (int j=0;j<matrix[0].length;j++) {
    			System.out.print(matrix[i][j]);
    		}
    		System.out.println();
    	}
    }
    
    
    static void chooseStartEnd() {		
    		System.out.print("root1 root2//");
    		@SuppressWarnings("resource")
			Scanner scan = new Scanner(System.in);
    		String root1root2 = scan.nextLine();
        	startRow=Character.getNumericValue(root1root2.charAt(0));
        	startColumn=Character.getNumericValue(root1root2.charAt(2));
        	
        	System.out.print("goal1 goal2//");
    		@SuppressWarnings("resource")
			Scanner scan1 = new Scanner(System.in);
    		String goal1goal2 = scan1.nextLine();
        	endRow=Character.getNumericValue(goal1goal2.charAt(0));
        	endColumn=Character.getNumericValue(goal1goal2.charAt(2));
        	
    }
    
    static boolean IDSHelp(int level,Cell startCell) {
    	boolean[][]visited=new boolean[matrix.length][matrix[0].length];
    	Stack<Cell>st=new Stack<Cell>();
    	Cell point;
    	st.add(startCell);
    	visited[startCell.row][startCell.column]=true;
    	while (!st.isEmpty())
    	{
    		point=st.pop();
    		if (point.row==Matrix.endRow && point.column==Matrix.endColumn)
    		{
    			Cell par=point.parent;
    			Matrix.matrix[endRow][endColumn]="*";
    			Matrix.matrix[startRow][startColumn]="*";
    			while (par!=null) 
    			{
    				Matrix.matrix[par.row][par.column]="*";
    				par=par.parent;
    			}
    			return true;
    		}
    		
    		if (point.level>=level) {
    			continue;
    		}
    		
    		if(Cell.available(point.row+1,point.column,visited)) {
    			visited[point.row+1][point.column] = true;
   				Cell nextP = new Cell(point.row+1,point.column,point.level+1 ,point);
   				st.add(nextP);
   			}
    			
   			 if(Cell.available(point.row,point.column+1,visited)) {
                visited[point.row][point.column+1] = true;
   	             Cell nextP = new Cell(point.row,point.column+1,point.level+1 ,point);
   	             st.add(nextP);
    			 }

   	         if(Cell.available(point.row-1,point.column,visited)) {
                 visited[point.row-1][point.column] = true;
                 Cell nextP = new Cell(point.row-1,point.column,point.level+1,point);
   	             st.add(nextP);
    	         }
    	
    	
              if(Cell.available(point.row,point.column-1,visited)) {
                 visited[point.row][point.column-1] = true;
                 Cell nextP = new Cell(point.row,point.column-1,point.level+1 ,point);
                 st.add(nextP);
    	         }		
    	}
    	return false;
    }
    
	static void IDS() {
		Cell startCell=new Cell(Matrix.startRow, Matrix.startColumn,0,null);
		boolean reachedGoal=false;
		int level=0;
		while (reachedGoal!=true)
		{
			reachedGoal=IDSHelp(level,startCell);
			level++;
		}
			
	    	
	    }
	
	public static void main(String[] args) {
		System.out.print("N//");
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		int n = in.nextInt();
		
		System.out.print("k//");
		@SuppressWarnings("resource")
		Scanner in1 = new Scanner(System.in);
		int k = in1.nextInt();
		
		Matrix.chooseStartEnd();
		Matrix.MatrixBuilder(n);
	    Matrix.matrixRand(k, n);
	    Matrix.printMatrix();
		System.out.println("--------------------------");
	    
		Matrix.IDS();
		
		Matrix.printMatrix();
	}
    
}



