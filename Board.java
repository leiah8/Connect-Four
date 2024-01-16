public class Board {

	private final int NUM_OF_COLUMNS = 7;
	private final int NUM_OF_ROW = 6;
	
	private char[][] arr; 
	
	private char nullChar = ' ';
	private int lineLength = 4;
	
	
	/* 
	 * The board object must contain the board state in some manner.
	 * You must decide how you will do this.
	 * 
	 * You may add addition private/public methods to this class is you wish.
	 * However, you should use best OO practices. That is, you should not expose
	 * how the board is being implemented to other classes. Specifically, the
	 * Player classes.
	 * 
	 * You may add private and public methods if you wish. In fact, to achieve
	 * what the assignment is asking, you'll have to
	 * 
	 */
	
	public Board() {
		arr = new char [NUM_OF_ROW][NUM_OF_COLUMNS];
		reset();
	}
	
	public void printBoard() {
		for(int i = 0; i < NUM_OF_ROW; i++) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				System.out.print('|');
				if (i >= NUM_OF_ROW-1 && arr[i][j] == nullChar) {
					System.out.print('_');
				}
				else
					System.out.print(arr[i][j]);
			}
			System.out.print("|\n");
		}
		
	}
	
	public boolean containsWin() {
		//check rows
		
		
		int count;
		for(int i = 0; i < NUM_OF_ROW; i++) {
			count = 1;
			for(int j = 0; j < NUM_OF_COLUMNS-1; j++) {
				if (arr[i][j] == arr[i][j+1] && arr[i][j] != nullChar)
					count++;
				else
					count = 1;
				
				if (count == lineLength) 
					return true;
				
			}
		}
		
		//check columns
		for(int j = 0; j < NUM_OF_COLUMNS; j++) {
			count = 1;
			for(int i = 0; i < NUM_OF_ROW-1; i++) {
				if (arr[i][j] == arr[i+1][j] && arr[i][j] != nullChar)
					count++;
				else
					count = 1;
				
				if (count == lineLength) 
					return true;
				
			}
		}
				
		//check diagonals
		int count2;
		
		int length = lineLength;
		for (int l = NUM_OF_ROW - lineLength; l >= 0; l--) {
			count = 1;
			count2 = 1;
			for(int n = 0; n < length-1; n++) {
				//System.out.println("("+(NUM_OF_ROW - (l+n)-1)+","+n+") COMPARED TO ("+(NUM_OF_ROW - (l+n)-2)+","+(n+1)+")");
				if (arr[l+n][n] == arr[l+n+1][n+1] && arr[l+n][n] != nullChar) 
					count++;
				
				else 
					count = 1;
				
				
				if (arr[NUM_OF_ROW - (l+n)-1][n] == arr[NUM_OF_ROW - (l+n)-2][n+1] &&
						arr[NUM_OF_ROW - (l+n)-1][n] != nullChar)
					count2++;
				
				else 
					count2 = 1;
				
				if(count == lineLength || count2 == lineLength) 
					return true;
			}
			length++;
		}
		
		length = lineLength;
		for (int l = NUM_OF_COLUMNS - lineLength; l > 0; l--) {
			count = 1;
			count2 = 1;
			for(int n = 0; n < length-1; n++) {
				if(arr[n][l+n] == arr[n+1][l+n+1] && arr[n][l+n] != nullChar) 
					count++;
				
				else 
					count = 1;
				
				if(arr[NUM_OF_COLUMNS - n -2][l+n] == arr[NUM_OF_COLUMNS - n -3][l+n+1] &&
						arr[NUM_OF_COLUMNS - n -2][l+n] != nullChar)
					count2++;
				else
					count2 = 1;
				
				if (count == lineLength || count2 == lineLength) 
					return true;
			}
			length++;
		}
		
		return false;
	}
	
	public boolean isTie() {
		//board is full and no wins
		//if (containsWin()) 
			//return false;
		
		for(int i = 0; i < NUM_OF_COLUMNS;i++) {
			if (arr[0][i] == nullChar)
				return false;
		}
		
		return true;
	}
	
	public void reset() {
		for(int i = 0; i < NUM_OF_ROW; i++) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				arr[i][j] = nullChar;
			}
		}
	}
	
	public boolean checkMove(int move) {
		if (move >= 1 && move <= 7) {
			for(int i = 0; i < NUM_OF_ROW; i++) {
				if (arr[i][move-1] == nullChar)
					return true; 
			}
		}
		return false;
	}
	
	//should checkMove before
	public void addMove(int move, char player) {
		for(int i = NUM_OF_ROW - 1; i >= 0; i--) {
			if (arr[i][move-1] == nullChar) {
				arr[i][move-1] = player;
				return;
			}
		}
	}
	
	private char[][] cpyArr() {
		char[][]cp = new char [NUM_OF_ROW][NUM_OF_COLUMNS];
		
		for(int i = 0; i < NUM_OF_ROW; i++) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				cp[i][j] = arr[i][j];
			}
		}
		
		return cp;
	}
	
	public boolean checkPotentialWin(int move, char player) {
		//find position
		if (!(checkMove(move)))
			return false;
		
		int row = -1;
		int col = -1;
		
		char[][] cp = cpyArr();
		
		for(int i = NUM_OF_ROW - 1; i >= 0; i--) {
			if (arr[i][move-1] == nullChar) {
				row = i;
				col = move-1;
				break;
			}
		}
		
		cp[row][col] = player;
		int count = 1;

		
		//check row
		for(int j = 0; j < NUM_OF_COLUMNS-1; j++) {
			if(cp[row][j] == cp[row][j+1] && cp[row][j] == player) {
				count++;
			}
			else count = 1;
			if (count == lineLength) return true;
		}
		
		//check column going down
		count = 1;
		for(int i = 0; i < NUM_OF_ROW -1; i++) {
			if(cp[i][col] == cp[i+1][col] && cp[i][col] == player) {
				count++;
			}
			else count = 1;
			
			if (count == lineLength) return true;
		}
		int startRow = row;
		int startCol = col;
		//check TL-BR diagonal
		while(startRow > 0 && startCol > 0) {
			startRow--;
			startCol--;
		}
		
		count = 1;
		while(startRow < NUM_OF_ROW-1 && startCol < NUM_OF_COLUMNS-1) {
			if(cp[startRow][startCol] == cp[startRow+1][startCol+1] &&
					cp[startRow][startCol] == player)
				count++;
			else
				count = 1;
			
			if (count == 4) return true;
			
			startRow++;
			startCol++;
		}
		
		
		startRow = row;
		startCol = col;
		//check BL-TR diagonal
		while(startRow < NUM_OF_ROW-1 && startCol > 0) {
			startRow++;
			startCol--;
		}
		
		count = 1;
		while(startRow > 0 && startCol < NUM_OF_COLUMNS-1) {
			if(cp[startRow][startCol] == cp[startRow-1][startCol+1] &&
					cp[startRow][startCol] == player)
				count++;
			else
				count = 1;
			
			if (count == 4) return true;
			
			startRow--;
			startCol++;
		}
		return false;
		
	}
	
	public int getColumnNum() {
		return NUM_OF_COLUMNS;
	}
	
	public char getNullChar() {
		return nullChar;
	}
	
	public char findOppSymbol(char symbol) {
		for(int i = NUM_OF_ROW - 1; i >= 0; i--) {
			for(int j = 0; j < NUM_OF_COLUMNS; j++) {
				if (arr[i][j] != symbol && arr[i][j] != nullChar)
					return arr[i][j];
			}
		}
		return nullChar;
	}
	
}
