	public class Cell {
		int row;
		int column;
		int level;
		Cell parent;

	Cell(int r, int c,int lev ,Cell par){
		row=r;
		column=c;
		parent=par;
		level=lev;
    }

		static boolean available(int row,int column,boolean[][]visited){
			 if((row >= 0 && row < Matrix.matrix.length) && (column >= 0 && column < Matrix.matrix[0].length) 
					 && (visited[row][column] == false) && Matrix.matrix[row][column]=="1") {
		            return true;
		        }
		        return false;
		}
		
		
	};