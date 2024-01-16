
import java.util.Scanner; //Needed for user input

public class HumanPlayer extends Player {

	public HumanPlayer(char symbol, Board board, String name) {
		super(symbol, board, name);
	}

	@Override
	public void makeMove(Board board) {
		Scanner sc = new Scanner(System.in);
		int move = 0;
		boolean cont = true;
		
		while (cont) {
			System.out.print(name + ", please input your move: ");
			try {
				move = sc.nextInt();
			} catch(Exception e) {
				System.out.println("Please input an integer.");
			}
			
			if (board.checkMove(move)) 
				cont = false;
			else 
				System.out.println("Invalid Move. Try again.");
		}
		
		//sc.close();
		
		board.addMove(move, symbol);
			
			
	}

}
