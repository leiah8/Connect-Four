import java.util.Random;

public class AIPlayer extends Player {
	char oppSymbol;

	public AIPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
		oppSymbol = board.getNullChar();
	}

	@Override
	public void makeMove(Board board) {
		int cols = board.getColumnNum();
		
		if (oppSymbol == board.getNullChar()) {
			oppSymbol = board.findOppSymbol(symbol);
		}
		else {
			
			//check for win
			for(int i = 1; i <= cols; i++) {
				if (board.checkPotentialWin(i, symbol)) {
					board.addMove(i, symbol);
					return;
				}
			}
			
			//check for opponent win
			for(int i = 1; i <= cols; i++) {
				if (board.checkPotentialWin(i, oppSymbol)) {
					board.addMove(i, symbol);
					return;
				}
			}
		}
		
		//randomly generate a move
		
		//create choices arr
		int[] choices = new int[cols];
		for(int i = 0; i < cols; i++) {
			choices[i] = i+1;
		}
		int size = cols;
		
		//find random number
		Random rn = new Random();
		int move;
		
		for(int i = 0; i < cols;i++) {
			move = choices[rn.nextInt(size)];
			if(board.checkMove(move)) {
				board.addMove(move, symbol);
				return;
			}
			else {
				choices[move] = choices[size-1];
				choices[size-1] = -1;
			}
			size--;
		}
		
	}

}
