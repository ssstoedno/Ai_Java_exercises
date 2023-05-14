import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Set;



	public class SlidingBlock {
		public static Node startPuzzle;
		static int N;

	
	
	static void puzzleCreator(String input, int NN){
		N=(int)Math.sqrt(NN+1);
		startPuzzle=new Node(N);
		int count=0;
		for (int i=0; i<N;i++) {
			for (int j=0; j<N;j++) {
				startPuzzle.puzzle[i][j]=input.charAt(count)-'0';
				count++;
			}
		}
		int manh=startPuzzle.manhattanDist(N);
		startPuzzle.nodeCreator(null,null,0,manh);
	}
	
	
	static void aStar(Node startState){
		PriorityQueue<Node> q = new PriorityQueue<>();
		Set<int[][]>visitedStates = new HashSet<int[][]>();
		Node neighbor = new Node(N);
		Node finalState=new Node(N);
		
		q.add(startPuzzle);
		
		if (startPuzzle.manhattanDist(N)==0) {
			startPuzzle.printAnswer();
			return;
		}
		
		while(!q.isEmpty()) {
			Node crr=q.poll();
			visitedStates.add(crr.puzzle);
			@SuppressWarnings("unused")
			int fn;
			
			
			if (crr.moveLeftpossibility(visitedStates,N)==true) {
				Node duplicate=new Node(crr);
			    duplicate.moveLeft();
			    int manh=duplicate.manhattanDist(N);
			    if(manh==0) {
			    	finalState=new Node(duplicate);
			    	finalState.nodeCreator(crr,"left",crr.costpath+1,manh);
			    	finalState.printAnswer();
			    	return;
			    }
			    	fn=manh+crr.costpath+1;
			    	neighbor=new Node(duplicate);
					neighbor.nodeCreator(crr,"left",crr.costpath+1,manh);
					q.add(neighbor);
			    
			}
			
			if (crr.moveRightpossibility(visitedStates,N)==true) {
				Node duplicate=new Node(crr);
			    duplicate.moveRight();
			    int manh=duplicate.manhattanDist(N);
			    if(manh==0) {
			    	finalState=new Node(duplicate);
			    	finalState.nodeCreator(crr,"right",crr.costpath+1,manh);
			    	finalState.printAnswer();
			    	return;
			    }
			    	fn=manh+crr.costpath+1;
			    	neighbor=new Node(duplicate);
			    	neighbor.nodeCreator(crr,"right",crr.costpath+1,manh);
			    	q.add(neighbor);
			}
			
			if (crr.moveUppossibility(visitedStates,N)==true) {
				Node duplicate=new Node(crr);
			    duplicate.moveUp();
			    int manh=duplicate.manhattanDist(N);
			    if(manh==0) {
			    	finalState=new Node(duplicate);
			    	finalState.nodeCreator(crr,"up",crr.costpath+1,manh);
			    	finalState.printAnswer();
			    	return;
			    }
			    	fn=manh+crr.costpath+1;
			    	neighbor=new Node(duplicate);
					neighbor.nodeCreator(crr,"up",crr.costpath+1,manh);
					q.add(neighbor);
			}
			
			if (crr.moveDownpossibility(visitedStates,N)==true) {
				Node duplicate=new Node(crr);
			    duplicate.moveDown();
			    int manh=duplicate.manhattanDist(N);
			    if(manh==0) {
			    	finalState=new Node(duplicate);
			    	finalState.nodeCreator(crr,"down",crr.costpath+1,manh);
			    	finalState.printAnswer();
			    	return;
			    }
			    	fn=manh+crr.costpath+1;
			    	neighbor=new Node(duplicate);
			    	neighbor.nodeCreator(crr,"down",crr.costpath+1,manh);
			    	q.add(neighbor);
			    /*}*/
			}
				
		}
	}
	
	
	
	public static void main(String[] args) {
		System.out.println("N is 8. For >8 too much memory.");
		int n = 8;
		
		System.out.print("Enter the puzzle(e.g.123456780): ");
		@SuppressWarnings("resource")
		Scanner in1 = new Scanner(System.in);
		String input = in1.nextLine();
		
		SlidingBlock.puzzleCreator(input,n);
		SlidingBlock.aStar(SlidingBlock.startPuzzle);
	}
	
}
