import java.util.Arrays;
import java.util.Set;
import java.util.Stack;

	public class Node implements Comparable<Node> {
		int [][] puzzle;
		Node parent;
		String move;
		int costpath;
		int manhDist;
		int fN;
		int rowOf0;
		int columnOf0;
    
	    Node(int N){
	    	puzzle=new int[N][N];
	    	parent=null;
	    	move=null;
	    }
	    
	    Node(Node another){
	    	puzzle=new int[another.puzzle.length][another.puzzle.length];
	    	for(int i=0;i<another.puzzle.length;i++) {
	    		for(int j=0;j<another.puzzle.length;j++) {
	    			puzzle[i][j]=another.puzzle[i][j];
	    		}
	    	}
	    	parent=another.parent;
	    	move=another.move;
	    	costpath=another.costpath;
	    	manhDist=another.manhDist;
	    	fN=another.fN;
	    	rowOf0=another.rowOf0;
	    	columnOf0=another.columnOf0;
	    }
	    
	    void nodeCreator(Node par,String m,int cost,int manh){	
		parent=par;
		move=m;
		costpath=cost;
		manhDist=manh;
		fN=costpath+manhDist;
		if (puzzle!=null) {
		for (int i=0;i<puzzle.length;i++) {
			for(int j=0;j<puzzle.length;j++) {
				if (puzzle[i][j]==0) {
					rowOf0=i;
					columnOf0=j;
					return;
				}
			}
		}
		}
	}
	   
	    
	    
	    public int compareTo(Node otherNode) {
	    	if(this.fN==otherNode.fN) {
	    		return 0;
	    	}
	    	else if (this.fN>otherNode.fN) {
	    		return 1;
	    	}
	    	else {
	    		return -1;
	    	}
	    }
	 
	    
	    void moveLeft(){
	    	puzzle[rowOf0][columnOf0]=puzzle[rowOf0][columnOf0+1];
	    	puzzle[rowOf0][columnOf0+1]=0;
	    	columnOf0=columnOf0+1;
	    }
	 
		boolean moveLeftpossibility(Set<int[][]>visitedStates, int N){
			Node puzz=new Node(this);
			if (columnOf0+1 < this.puzzle.length) {
				puzz.moveLeft();
				for(int[][]s:visitedStates) {
					if (Arrays.deepEquals(s,puzz.puzzle)==true) {
						return false;
					
					}
				}
				return true;	
			}
			return false;
		}
		
		
		void moveRight(){
			puzzle[rowOf0][columnOf0]=puzzle[rowOf0][columnOf0-1];
			puzzle[rowOf0][columnOf0-1]=0;
			columnOf0=columnOf0-1;
		}
		
		boolean moveRightpossibility(Set<int[][]>visitedStates, int N){
			Node puzz=new Node(this);	
			if (columnOf0-1 >= 0) {
				puzz.moveRight();
				for(int[][]s:visitedStates) {
					if (Arrays.deepEquals(s,puzz.puzzle)==true) {
						return false;
					}
				}
				return true;
			}
			return false;
	
		}
		
	
		void moveUp(){
			puzzle[rowOf0][columnOf0]=puzzle[rowOf0+1][columnOf0];
			puzzle[rowOf0+1][columnOf0]=0;
			rowOf0=rowOf0+1;
		}
		
		boolean moveUppossibility(Set<int[][]>visitedStates, int N){
			Node puzz=new Node(this);	
			if (rowOf0+1 < this.puzzle.length) {
				puzz.moveUp();
				for(int[][]s:visitedStates) {
					if (Arrays.deepEquals(s,puzz.puzzle)==true) {
						return false;
					}
				}
				return true;
			}
			return false;
	
		}
		
		
		void moveDown(){
			puzzle[rowOf0][columnOf0]=puzzle[rowOf0-1][columnOf0];
			puzzle[rowOf0-1][columnOf0]=0;
			rowOf0=rowOf0-1;
		}
		
		boolean moveDownpossibility(Set<int[][]>visitedStates, int N){
			Node puzz=new Node(this);	
			if (rowOf0-1 >= 0) {
				puzz.moveDown();
				for(int[][] s:visitedStates) {
					if (Arrays.deepEquals(s,puzz.puzzle)==true) {
						return false;
					}
				}
				return true;
			}
			return false;
	
		}
		
		
		int manhattanDist(int N) {
			int manhattanDistance=0;
			for (int row=0; row<N;row++) {
				for (int column=0;column<N;column++) {
					int curr=puzzle[row][column];
					if (curr!=0) {
						int finalstateRow=Math.floorDiv(curr-1, N);
						int finalstateCol=(curr-1)%N;
						manhattanDistance += Math.abs(row-finalstateRow)
								+Math.abs(column-finalstateCol);
					}
				}
			}
			return manhattanDistance;
		}
		
		void printAnswer() {
			System.out.println(this.costpath);
			Node crr=this.parent;
			Stack<String>path=new Stack<String>();
			
			if (crr==null) {
				return;
			}
			
			path.push(this.move);
			while(crr.move!=null) {
				path.push(crr.move);
				crr=crr.parent;
			}
		   while(!path.isEmpty()) {
			   System.out.println(path.pop());
		   }
		}
	

}
