public class GameBoard {

	//game size
	private int gameSize = 3;
	//an array of arrays to present the game board
	private int[][] gameBoard;
	//represents winning game configuration
	private int[] winningConfig;

	//location of the empty space, represented by a 0.
	private int xloc = 0;
	private int yloc = 0;

	//gets x location of empty space
	public int getXLoc(){
		return xloc;
	}
	
	//returns y location of empty space
	public int getYlock(){
		return yloc;
	}

	//creates board with size determined by a max integer tile value.
	//diameters of game board is chosen to nearest sqrt value...
	//the board is then filled with numbers, where 0 is the empty space.
	//in this my representation of the gameboard, the winning configuration is stored as an array.
	//and the empty space is represented by a x and y location.
	public GameBoard(int nPuzzle){
		
		//sets game size to square root of number of items + 1.
		this.gameSize = (int) (Math.sqrt(nPuzzle+1));
		
		//generates gameboard
		gameBoard = new int[gameSize][];
		
		//sets winning config
		winningConfig = new int[gameSize*gameSize];
		
		//populate wining config
		for(int i = 0; i < winningConfig.length;i++){
			winningConfig[i] = i;
		}
		
		//creates double array for gameboard
		for(int i = 0; i < gameBoard.length; i++){
			gameBoard[i] = new int[gameBoard.length];
		}

		//tracks progress for input array
		int index = 0;
		
		//populate game board, stores location of x,y
		for(int y = 0; y < gameBoard.length; y++){
			for(int x = 0; x < gameBoard.length; x++){
				gameBoard[x][y] = winningConfig[index];
				if(winningConfig[index]==0){
					this.xloc = x;
					this.yloc = y;
				}
				index++;
			}
		}
	}

	//creates a gameboard based on an array of ints. will populate board
	//based on next lowest perfect root integer.
	public GameBoard(int[] inputArray){
		
		//sets size to number of items - 1
		this(inputArray.length - 1);
		
		//tracks progress for input array
		int index = 0;

		//populates game board based on locations
		for(int y = 0; y < gameBoard.length; y++){
			for(int x = 0; x < gameBoard.length; x++){
				gameBoard[x][y] = inputArray[index];
				if(inputArray[index]==0){
					this.xloc = x;
					this.yloc = y;
				}
				index++;
			}
		}
	}
	
	//uses first constructor, then it randomizes
	public GameBoard(int[] inputArray, int moves){
		this(inputArray);
		this.randomize(moves);
	}
	
	//creates standard board.
	public GameBoard(){
		this(8);
	}
	
	//creates a randomized board based on number of moves specified
	//with constraints of previous boards.
	public GameBoard(int gameSize, int moves){
		this(gameSize);
		this.randomize(moves);	
	}
	
	//creates duplicate board
	public GameBoard(GameBoard inputBoard){
		
		//creates size based on old size
		gameSize = inputBoard.gameSize;
		
		//creates double array
		gameBoard = new int[gameSize][];
		
		//creating winning config
		winningConfig = new int[gameSize*gameSize];
		
		//generates double array
		for(int i = 0; i < gameBoard.length; i++){
			gameBoard[i] = new int[gameBoard.length];
		}
		
		//copies over gameBoard.
		for(int y = 0; y < gameBoard.length; y++){
			for(int x = 0; x < gameBoard.length; x++){
				gameBoard[x][y] = inputBoard.gameBoard[x][y];
			}
		}
		
		//copies over winning config
		for(int i = 0; i < winningConfig.length; i++){
			winningConfig[i] = inputBoard.winningConfig[i];
		}

		//copies location of blank
		this.xloc = inputBoard.xloc;
		this.yloc = inputBoard.yloc;
	}
	
	

	//prints board to console
	//adds dahses to top and bottom.
	public void printBoard(){
		System.out.println("-------");
		
		//iterates through and builds a string with each line
		for(int y = 0; y < gameBoard.length; y++){
			StringBuilder b = new StringBuilder();
			for(int x = 0; x < gameBoard.length; x++){
				b.append(gameBoard[x][y]);
			}
			System.out.println(b.toString());
		}
		System.out.println("-------");
	}

	//checks if current game state equals winning game state
	public boolean isWinningMove(){
		int index = 0;
		//iterates through each part of the board.
		for(int y = 0; y < gameBoard.length; y++){
			for(int x = 0; x < gameBoard.length; x++){
				//if there is a mismatch in numbers, automatically return false
				if(!(gameBoard[x][y] == winningConfig[index]))
					return false;
				else
					index++;
			}
		}
		return true;
	}
	
	//checks if it matches an input board
	public boolean checkMatch(GameBoard comparisonBoard){
		for(int y = 0; y < gameBoard.length; y++){
			for(int x = 0; x < gameBoard.length; x++){
				//returns false if there is a mismatch
				if(gameBoard[x][y] != comparisonBoard.gameBoard[x][y])
					return false;
			}
		}
		return true;
	}

	//shifts the blank space up.
	public void shiftUp() throws ArrayIndexOutOfBoundsException{
		gameBoard[xloc][yloc] = gameBoard[xloc][yloc - 1];
		gameBoard[xloc][yloc - 1] = 0;
		yloc = yloc - 1;
	}

	//shifts the blank space down
	public void shiftDown() throws ArrayIndexOutOfBoundsException{
		gameBoard[xloc][yloc] = gameBoard[xloc][yloc + 1];
		gameBoard[xloc][yloc + 1] = 0;
		yloc = yloc + 1;
	}

	//shifts the blank space right
	public void shiftRight() throws ArrayIndexOutOfBoundsException{
		gameBoard[xloc][yloc] = gameBoard[xloc + 1][yloc];
		gameBoard[xloc + 1][yloc] = 0;
		xloc = xloc + 1;
	}

	//shifts the blank space left
	public void shiftLeft() throws ArrayIndexOutOfBoundsException{
		gameBoard[xloc][yloc] = gameBoard[xloc - 1][yloc];
		gameBoard[xloc - 1][yloc]  = 0;
		xloc = xloc - 1;
	}

	//random shifts left, right, up, down. Does this numMoves amount of times.
	public void randomize(int numMoves){
		for(int i = 0; i < numMoves; i++){
			boolean notMoved = true;
			int move = 0;
			while(notMoved){
				move = (int) (Math.random() * 4);
				try{
					if(move == 1){						//shift left
						shiftLeft();
					} else if(move == 2){				//shift right
						shiftRight();
					} else if(move == 3){				//shift up
						shiftUp();
					} else {							//shift down
						shiftDown();
					}
					notMoved = false;
				} catch(Exception e){

				}
			}
		}
	}
}
